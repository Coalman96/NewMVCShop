package mvc.service.security;

import mvc.service.domain.UserDTO;

public interface AuthenticationService {

  public UserDTO signUp(UserDTO userDTO);

}
