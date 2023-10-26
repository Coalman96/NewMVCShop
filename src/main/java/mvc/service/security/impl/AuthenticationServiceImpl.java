package mvc.service.security.impl;

import lombok.RequiredArgsConstructor;
import mvc.service.domain.UserDTO;
import mvc.service.entity.UserEntity;
import mvc.service.repository.UserRepository;
import mvc.service.security.AuthenticationService;
import mvc.service.user.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

  private final UserRepository userRepository;

  private final UserService userService;

  private final PasswordEncoder passwordEncoder;

  public UserDTO signUp(UserDTO userDTO){

    userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
    UserEntity userEntity = userService.dtoToEntity(userDTO);

    return userService.entityToDTO(userRepository.save(userEntity));
  }
}
