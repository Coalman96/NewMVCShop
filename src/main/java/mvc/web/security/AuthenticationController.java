package mvc.web.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mvc.service.domain.UserDTO;
import mvc.service.security.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  @PostMapping("json/signup")
  public UserDTO addUser(@RequestBody UserDTO userDTO) throws Exception {

    log.info("/api/v1/auth/json/addUser : POST");
    log.info("addUser에서 받은 user는 {}",userDTO);

    return authenticationService.signUp(userDTO);
  }

}
