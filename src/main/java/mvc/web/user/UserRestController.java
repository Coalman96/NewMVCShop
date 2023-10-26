package mvc.web.user;

import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import mvc.service.domain.PageRequestDTO;
import mvc.service.domain.PageResultDTO;
import mvc.service.domain.UserDTO;
import mvc.service.entity.UserEntity;
import mvc.service.user.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping({"/user/*","/api/v1/auth","/api/v1/admin"})
@ToString
@RequiredArgsConstructor
@Slf4j
public class UserRestController {

  ///Field
  private final UserService userService;

  @Value("${pageUnit}")
  int pageUnit;

  @Value("${pageSize}")
  int pageSize;


  @PostMapping("json/addUser")
  public String addUser(@RequestBody UserDTO userDTO) throws Exception {

    log.info("/user/json/addUser : POST");
    log.info("addUser에서 받은 user는 {}",userDTO);

    //Business Logic
    userService.addUser(userDTO);
    return "redirect:/";
  }

  @RequestMapping(value = "json/login", method = RequestMethod.POST)
  public Map<String, Object> login(@RequestBody UserDTO user,
      HttpSession session) throws Exception {

    log.info("/user/json/login : POST");
    //Business Logic
    log.info("PW:" + user.getPassword() + "ID:" + user.getUserId());
    UserDTO dbUser = userService.getUser(user.getUserId());
    log.info("dbUser는 {}", dbUser);
    boolean checkUser = false;
    Map<String, Object> map = new HashMap<String, Object>();
    if (dbUser != null) {
      if (user.getPassword().equals(dbUser.getPassword())) {
        checkUser = true;
        //dbUser.setActive(true);
        //userService.updateUser(dbUser);
        map.put("user", dbUser);
        //서버측 세션 관리
        session.setAttribute("user", dbUser);
      }
    }
    map.put("checkUser", checkUser);
    return map;
  }

  @GetMapping("json/getUser/{userId}")
  public UserDTO getUser(@PathVariable String userId) throws Exception {

    log.info("/user/json/getUser : GET");

    return userService.getUser(userId);
  }

  @PostMapping("json/updateUser")
  public UserDTO updateUser(@RequestBody UserDTO user) throws Exception {

    log.info("/user/json/updateUser : POST");
    log.info("Rest에서 받은 user는 {}", user);

    return userService.updateUser(user);
  }

  @PostMapping("json/checkDuplication/{userId}")
  public Map<String, Object> checkDuplication(@PathVariable String userId) throws Exception {

    log.info("/user/json/checkDuplication : POST");

    //Business Logic
    boolean result = userService.checkDuplication(userId);

    // ModelAndView
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("result", result);
    map.put("userId", userId);

    return map;
  }

  @PostMapping("json/getUserList")
  public Map<String, Object> getUserList(@RequestBody PageRequestDTO requestDTO) throws Exception {

    log.info("/user/json/getUserList : POST");
    log.info("받은 pageRequest는 {}", requestDTO);

    requestDTO.setPageSize(pageSize);

    PageResultDTO<UserDTO, UserEntity> result = userService.getUserList(requestDTO);

    log.info("getUserList의 result 는 {}", result);

    Map<String, Object> responseMap = new HashMap<>();
    responseMap.put("data", result.getDtoList());  // 데이터 리스트를 넣음
    responseMap.put("totalPage", result.getTotalPage());  // 총 페이지 수를 넣음
    responseMap.put("currentPage", result.getCurrentPage());  // 현재 페이지를 넣음

    return responseMap;
  }

}