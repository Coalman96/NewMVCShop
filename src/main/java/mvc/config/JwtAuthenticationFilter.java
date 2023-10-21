package mvc.config;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import mvc.service.user.JWTService;
import mvc.service.user.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JWTService jwtService;

  private final UserService userService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    //1. request에서 인증 헤더를 가져온다
    final String authHeader = request.getHeader("Authorization");

    //2. 인증을위해 JWT를 저장한다.
    final String jwt;

    //3. 사용자 이메일을 저장한다
    final String userEmail;

    //4. 인증헤더가 비어있는지 체크
    if (StringUtils.isEmpty(authHeader) || !org.apache.commons.lang3.StringUtils.startsWith(
        authHeader, "Bearer ")){

      filterChain.doFilter(request, response);
      return;
    }
    //5. JWT토큰을 가져와서 변수에 저장
    // - 7번쨰부터하는이유는 Bearer 타입 다음에 오기떄문임
    jwt = authHeader.substring(7);

    //6. JWT토큰에서 유저이메일을 추출
    userEmail = jwtService.extractUserName(jwt);

    //7. 유저이메일 비어있는지 체크와 보안적용확인
    // -현재 사용자의 인증(Authentication) 정보가 비어 있거나(즉, 사용자가 인증되지 않았을 때) null인지를 확인
    if (StringUtils.isEmpty(userEmail)
        //현재 사용자의 인증 정보를 가져올 수 있음
        && SecurityContextHolder.getContext().getAuthentication() == null) {
      //8. userService로 이동해서 userDetailsService를 생성한다.
      UserDetails userDetails = userService.userDetailsService().loadUserByUsername(userEmail);

      //9. 토큰 유효성을 확인하기위해 JWTServiceImpl에서 메소드를 생성하고온다.
      // - isTokenValid메소드를 통해 토큰의 유효성확인
      if(jwtService.isTokenValid(jwt, userDetails)){
        // - 토큰이 유효하다면 현재 사용자의 인증 정보를 저장하는데 사용되는 SecurityContext 생성
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

        // -  사용자 정보, 암호화된 비밀번호(여기서는 null), 및 권한(authorities)을 설정
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
            userDetails, null, userDetails.getAuthorities()
        );

        // - 웹 요청과 관련된 정보를 포함시킴.
        // - 이 정보는 사용자의 인증 요청과 관련하여 로그인 시도에 대한 자세한 정보를 기록하는 데 사용
        token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        // - securityContext에 앞에서 생성한 토큰을 설정
        securityContext.setAuthentication(token);

        // - Spring Security의 SecurityContextHolder에 저장하여 현재 사용자의 인증 정보를 업데이트
        SecurityContextHolder.setContext(securityContext);
      }

    }
    //필터를 연결하고 다음 시나리오로 이동
    //보안 구성 및 관련저장소를 추가할것임
    //SecurityConfig로 이동해보자
    filterChain.doFilter(request,response);


  }



}
