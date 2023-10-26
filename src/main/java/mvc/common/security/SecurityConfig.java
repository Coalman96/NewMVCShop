package mvc.common.security;

import lombok.RequiredArgsConstructor;
import mvc.config.JwtAuthenticationFilter;
import mvc.service.entity.Role;
import mvc.service.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

  //1. JWT인증필터를 가져옴
  private final JwtAuthenticationFilter jwtAuthenticationFilter;

  //2. 유저서비스를 가져옴
  private final UserService userService;

  //3. SecurityFilterChain을 반환하는 메소드 생성
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http
        //CSRF(Cross-Site Request Forgery) 공격을 비활성화하여 보안문제를 방지하고 웹 애플리케이션의 안전성을 강화
        .csrf(AbstractHttpConfigurer::disable) // csrf를 비활성화하기위해 AbstractHttpConfigurer를 disable
        // HTTP 요청에 대한 접근 권한을 설정
        .authorizeHttpRequests(request -> request
            .requestMatchers(new AntPathRequestMatcher("/**")).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/api/v1/admin/**")).hasAnyAuthority(Role.ADMIN.name())
            .requestMatchers(new AntPathRequestMatcher("/api/v1/user/**")).hasAnyAuthority(Role.USER.name())
            .anyRequest().authenticated())
        //세션관리 비활성화,상태를 저장하지 않는 세션을 사용하며
        //모든 요청은 세션에 의존하지 않는다. 이는 주로 토큰 기반의 인증을 사용할 때 사용
        .sessionManagement(
            maneger -> maneger.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        //사용자 인증을 처리
        //사용자의 인증을 검증하고 사용자 정보를 가져오는 역할
        //사용자의 JWT 토큰을 검증하고, 사용자를 인증
        .authenticationProvider(authenticationProvider()).addFilterBefore(
            jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class
        );
    //비밀번호 인증 필터 클래스 제공
    return http.build();

  }
  //사용자의 인증을 처리하는 역할
  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    //사용자 정보를 가져오는 데 사용될 UserDetailsService를 설정
    authenticationProvider.setUserDetailsService(userService.userDetailsService());
    //비밀번호 암호화를 처리하는 데 사용될 PasswordEncoder를 설정
    authenticationProvider.setPasswordEncoder(passwordEncoder());
    return authenticationProvider;
  }

  //비밀번호를 안전하게 저장하기 위해 비밀번호를 해시(암호화)하는 데 사용
  @Bean
  public PasswordEncoder passwordEncoder() {
    //BCrypt는  강력한 해시알고리즘 중 하나로, 사용자 비밀번호를 안전하게 저장하는 데 사용
    return new BCryptPasswordEncoder();
  }

  //Spring Security에서 사용자의 인증을 처리하고 인증된 사용자를 관리하는 데 사용
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
    return config.getAuthenticationManager();
  }
  //4. RestController로 이동하여 단일인입점생성
}


//  @Bean
//  protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    /*태현쿤 시큐리티 코드 해설
     // HTTP 요청에 대한 권한 설정
    http
        .authorizeHttpRequests(authorize -> authorize
//            .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()  // FORWARD 요청은 모두 허용->필요할경우 사용
            .requestMatchers(
                new AntPathRequestMatcher("/"),
                new AntPathRequestMatcher("/static/**"),
                new AntPathRequestMatcher("/user/addUser"),
                new AntPathRequestMatcher("/images/**"),
                new AntPathRequestMatcher("/css/**"),
                new AntPathRequestMatcher("/javascript/**")).permitAll()  // 특정 경로에 대한 요청은 모두 허용
            .anyRequest().authenticated())  // 그 외의 요청은 인증이 필요

        //CSRF(Cross-Site Request Forgery)
        //OAuth 토큰을 사용하여 보안을 유지하기때문에 disable설정
        .csrf(AbstractHttpConfigurer::disable)
        .formLogin(login -> login
            .loginPage("/user/login")  // 로그인 페이지 설정
            .loginProcessingUrl("/user/login")  // 로그인 처리 URL 설정
            .usernameParameter("userId")  // 사용자 아이디 파라미터명 설정
            .passwordParameter("password")  // 비밀번호 파라미터명 설정
            .defaultSuccessUrl("/", true)  // 로그인 성공 시 기본 이동 경로
            .permitAll()  // 로그인 페이지 접근 허용
        )
        .logout(Customizer.withDefaults())  // 로그아웃 설정
        .oauth2Login(oauth2Configurer -> oauth2Configurer
            .loginPage("/login")  // OAuth2 로그인 페이지 설정
            .defaultSuccessUrl("/user/oauth", true)  // OAuth2 로그인 성공 시 기본 이동 경로

        );

    return http.build();  // SecurityFilterChain 반환
  }
  //*/

//  }