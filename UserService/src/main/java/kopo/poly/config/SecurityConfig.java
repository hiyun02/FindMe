package kopo.poly.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
        로그아웃 처리를 위해 토큰 이름을 가져오기
     */
    @Value("${jwt.token.access.name}")
    private String accessTokenName;

    @Value("${jwt.token.refresh.name}")
    private String refreshTokenName;

    /**
     *
     * @return 로그인 및 회원가입에서 사용하는 해시 암호화
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        log.info(this.getClass().getName() + ".PasswordEncoder Start!");
        return new BCryptPasswordEncoder();
    }

    /**
     * Spring Security 에  저장된 인증 정보 가져올 떄 활용
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info(getClass().getName()+"filterChain start");

        http

                //CSRF : 인증된 사용자의 권한을 이용하여 악의적인 요청을 실행하는 공격
                //CORS : 추가적인 HTTP 헤더를 사용하여 한 출처에서 실행 중인 웹 애프리케이션이 다른 URL이 선택한 리소스에 접근할 수 있는 권한부여

                .csrf(AbstractHttpConfigurer::disable) // CSRF 보호기능 비활성화 == POST 허용
                .cors(AbstractHttpConfigurer::disable) // CORS 보호 기능 비활성화 == 다른 URL로 접속하여 데이터 접근 허용
                .formLogin(login -> login // 로그인 페이지 설정
                        .loginPage("/ss/login") // 로그인 페이지 접근 URL
                        .loginProcessingUrl("/login/loginProc") // 로그인 실행 URL
                        .usernameParameter("userId") // 로그인 ID로 사용할 HTML input 객체의 name 값
                        .passwordParameter("password") // 로그인 PW 사용할 HTML input 객체의 name 값

                        .successForwardUrl("/login/loginSuccess") // 로그인 성공 URL
                        .failureForwardUrl("/login/loginFail") // 로그인 실패 URL
                )
                .logout(logout -> logout // 로그 아웃 처리
                        .logoutUrl("/user/logout") // 로그아웃 URL
                        .deleteCookies(accessTokenName, refreshTokenName)
                        .logoutSuccessUrl("/ss/login") // 로그아웃이 성공하면, 로그인 화면으로 이동함
                )
                // 세션을 사용하지 않도록 설정함
                .sessionManagement(ss -> ss.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }





}
