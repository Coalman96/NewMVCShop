package com.spring.mvc;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import java.util.Optional;
import java.util.stream.IntStream;
import mvc.MvCshopApplication;
import mvc.service.domain.PageRequestDTO;
import mvc.service.domain.PageResultDTO;
import mvc.service.domain.UserDTO;
import mvc.service.entity.QUserEntity;
import mvc.service.entity.UserEntity;
import mvc.service.repository.UserRepository;
import mvc.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


@SpringBootTest(classes = MvCshopApplication.class)
public class UserServiceTest {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserService userService;

  //Insert 테스트
  //@Test
  public void testInsert() {

    IntStream.rangeClosed(1, 100).forEach(i -> {
      UserEntity user = UserEntity.builder().userId("user" + i).build();
      userRepository.save(user);


    });
  }

  //SELECT 테스트
  //@Test
  public void testFindById() {
    String userId = "user22";
    Optional<UserEntity> result = userRepository.findById(userId);
    if (result.isPresent()) {

      UserEntity user = result.get();
      System.out.println(user);

    }
  }

  //Update 테스트
  //@Test
  public void testUpdate() {

    UserEntity user = UserEntity.builder().userId("user22").userName("정상수").build();
    System.out.println(userRepository.save(user));
  }

  //DELETE 테스트
  //@Test
  public void testDelete() {

    String userId = "user22";
    userRepository.deleteById(userId);
  }

  //page 테스트
  //@Test
  public void testPage() {

    //1페이지 10개
    Pageable pageable = PageRequest.of(0, 10, Sort.by("userId").ascending());

    Page<UserEntity> result = userRepository.findAll(pageable);

    System.out.println(result);
    System.out.println(result.getTotalPages());
    System.out.println(result.getTotalElements());
    for (UserEntity user : result.getContent()) {

      System.out.println(user);

    }
  }

  //Query테스트
  //@Test
  public void testQuery1() {

    Pageable pageable = PageRequest.of(0, 10, Sort.by("userId").descending());

    String userNum = "22";

    QUserEntity qUserEntity = QUserEntity.userEntity;

    BooleanBuilder builder = new BooleanBuilder();

    BooleanExpression expression = qUserEntity.userId.contains(userNum);

    builder.and(expression);

    Page<UserEntity> result = userRepository.findAll(builder, pageable);

    result.forEach(userEntity -> {

      System.out.println(userEntity);

    });
  }

  //List 테스트
  @Test
  public void testList() {

    PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
        .pageSize(10)
        .pageUnit(1)
				.searchKeyword("user94")
				.searchType("0")
        .build();

    PageResultDTO<UserDTO, UserEntity> resultDTO = userService.getUserList(pageRequestDTO);

    System.out.println("PREV" + resultDTO.isPrev());
    System.out.println("NEXT" + resultDTO.isNext());
    System.out.println("TOTAL" + resultDTO.getTotalPage());

    for (UserDTO userDTO : resultDTO.getDtoList()) {

      System.out.println(userDTO);

    }
  }
}
