package mvc.service.user.impl;

import com.querydsl.core.BooleanBuilder;
import java.util.Optional;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import mvc.service.domain.PageRequestDTO;
import mvc.service.domain.PageResultDTO;
import mvc.service.domain.UserDTO;
import mvc.service.entity.QUserEntity;
import mvc.service.entity.UserEntity;
import mvc.service.repository.UserRepository;
import mvc.service.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("userServiceImpl")
@ToString
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

  ///Field
  private final UserRepository userRepository;

  ///Method
  public void addUser(UserDTO userDTO) throws Exception {

    UserEntity userEntity = UserEntity.builder()
        .userId(userDTO.getUserId())
        .password(userDTO.getPassword())
        .phone1(userDTO.getPhone1())
        .phone2(userDTO.getPhone2())
        .phone3(userDTO.getPhone3())
        .ssn(userDTO.getSsn())
        .addr(userDTO.getAddr())
        .email(userDTO.getEmail())
        .phone(userDTO.getPhone())
        .userName(userDTO.getUserName())
        .build();

    userRepository.save(userEntity);
  }

  public UserDTO getUser(String userId) throws Exception {
    Optional<UserEntity> userEntityOptional = userRepository.findById(userId);

    // UserEntity를 UserDTO로 변환
    return userEntityOptional.map(this::entityToDTO).orElse(null);
  }

  @Override
  @Transactional
  public UserDTO updateUser(UserDTO userDTO) throws Exception {
    log.info("받은 값: {}", userDTO);

    // DTO를 엔티티로 변환
    UserEntity userEntity = dtoToEntity(userDTO);

    // 유저 업데이트
    userEntity = userRepository.save(userEntity);

    // 엔티티를 DTO로 다시 변환
    return entityToDTO(userEntity);
  }

  public boolean checkDuplication(String userId) throws Exception {
    log.info("받은 값: {}", userId);
    // 주어진 userId로 데이터베이스 조회
    Optional<UserEntity> userOptional = userRepository.findById(userId);

    // 데이터베이스에서 사용자를 찾았으면 이미 존재한다고 판단하여 true 반환
    return userOptional.isPresent();
  }

  @Override
  public PageResultDTO<UserDTO, UserEntity> getUserList(PageRequestDTO requestDTO) {

    //DTO를 받아서 정렬
    Pageable pageable = requestDTO.getPageable(Sort.by("userId"));

    //검색조건 설정
    BooleanBuilder booleanBuilder = getSearch(requestDTO);

    //JPA 결과 값을 result에 저장
    //Querydsl추가(검색조건)
    Page<UserEntity> result = userRepository.findAll(booleanBuilder,pageable);

    //result를 화면에 뿌리기위해 DTO로 변환
    Function<UserEntity, UserDTO> fn = (this::entityToDTO);

    return new PageResultDTO<>(result, fn);
  }

  private BooleanBuilder getSearch(PageRequestDTO requestDTO) {

    // PageRequestDTO에서 검색 유형 및 키워드 가져오기
    String searchType = requestDTO.getSearchType();
    String searchKeyword = requestDTO.getSearchKeyword();

    // 초기화된 BooleanBuilder 객체 생성 -> WHERE 절
    BooleanBuilder booleanBuilder = new BooleanBuilder();

    // 사용할 엔터티(QUserEntity) 설정
    //Qentity가 userEntity를 참조
    QUserEntity qUserEntity = QUserEntity.userEntity;

    // 검색 키워드가 null이면 빈 BooleanBuilder 반환
    if(searchKeyword == null){
      return booleanBuilder;
    }

    // 실제 검색 조건을 담는 새로운 BooleanBuilder 생성
    BooleanBuilder conditionBuilder = new BooleanBuilder();

    // 검색 유형에 따라 검색 조건 설정
    if(searchType.contains("0")){
      conditionBuilder.or(qUserEntity.userId.contains(searchKeyword));
    } else if(searchType.contains("1")){
      conditionBuilder.or(qUserEntity.userName.contains(searchKeyword));
    }

    // 기본 BooleanBuilder에 조건을 추가하여 최종 검색 조건 구성
    booleanBuilder.and(conditionBuilder);

    // 최종 검색 조건이 추가된 BooleanBuilder 반환
    return booleanBuilder;
  }
}
