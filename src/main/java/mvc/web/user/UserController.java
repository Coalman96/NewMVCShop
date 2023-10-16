package mvc.web.user;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mvc.service.domain.UserDTO;
import mvc.service.user.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


//==> 회원관리 Controller
@Controller
@RequestMapping("/user/*")
@RequiredArgsConstructor
@Slf4j
public class UserController {

  ///Field
  private final UserService userService;

  @Value("${pageUnit}")
  int pageUnit;

  @Value("${pageSize}")
  int pageSize;

  //REST x
  @GetMapping("login")
  public String login() throws Exception {

    log.info("/user/logon : GET");

    return "redirect:/";
  }

  @GetMapping("logout")
  public String logout(HttpSession session) throws Exception {

    log.info("/user/logout : POST");

    // 세션에서 유저 정보 가져오기
    UserDTO user = (UserDTO) session.getAttribute("user");

    if (user != null) {
      // 유저의 active 상태를 false로 설정
      //user.setActive(false);

      // 유저 정보 업데이트
      //userService.updateUser(user);

      // 서버 세션 제거
      session.invalidate();
    }

    return "redirect:/";
  }

  @GetMapping("addUser")
  public String addUser() throws Exception {

    log.info("/user/addUser : GET");

    return "/";
  }

  @GetMapping("listUser")
  public String listUser() throws Exception{
    log.info("/user/listUser : GET");

    return"/user/listUser";
  }
}