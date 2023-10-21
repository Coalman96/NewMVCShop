package mvc.service.user;

import mvc.service.domain.PageRequestDTO;
import mvc.service.domain.PageResultDTO;
import mvc.service.domain.UserDTO;
import mvc.service.entity.UserEntity;
import mvc.service.mapper.CartMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService {
  CartMapper cartMapper = Mappers.getMapper(CartMapper.class);
  //DTO to Entity userList
  default UserEntity dtoToEntity(UserDTO dto){
    

    UserEntity entity = UserEntity.builder()
        .userId(dto.getUserId())
        .addr(dto.getAddr())
        .password(dto.getPassword())
        .ssn(dto.getSsn())
        .phone1(dto.getPhone1())
        .phone2(dto.getPhone2())
        .phone3(dto.getPhone3())
        .email(dto.getEmail())
        .phone(dto.getPhone())
//        .userName(dto.getUserName())
        .role(dto.getRole())
        .cart(cartMapper.cartDtoToEntity(dto.getCart()))
        .build();
    return entity;
  }

  //Entity to DTO
  default UserDTO entityToDTO(UserEntity entity){

    UserDTO dto = UserDTO.builder()
        .userId(entity.getUserId())
        .addr(entity.getAddr())
        .password(entity.getPassword())
        .ssn(entity.getSsn())
        .phone1(entity.getPhone1())
        .phone2(entity.getPhone2())
        .phone3(entity.getPhone3())
        .email(entity.getEmail())
        .phone(entity.getPhone())
//        .userName(entity.getUserName())
        .role(entity.getRole())
//        .regDate(entity.getRegDate())
        .cart(cartMapper.cartEntityToDto(entity.getCart()))
        .build();

    return dto;
  }

  public void addUser(UserDTO user) throws Exception;

  public UserDTO getUser(String userId) throws Exception;

  public UserDTO updateUser(UserDTO user) throws Exception;

  public boolean checkDuplication(String userId) throws Exception;

  public PageResultDTO<UserDTO, UserEntity> getUserList(PageRequestDTO requestDTO);

  //UserDetailsService 구현
  UserDetailsService userDetailsService();

}