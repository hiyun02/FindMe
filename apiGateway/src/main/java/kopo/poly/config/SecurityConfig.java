package kopo.poly.config;

import kopo.poly.filter.JwtAuthenticationFilter;
import kopo.poly.handler.AccessDeniedHandler;
import kopo.poly.handler.LoginServerAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableWebFluxSecurity
public class SecurityConfig {

    private final AccessDeniedHandler accessDeniedHandler; // 인증 에러 처리

    private final LoginServerAuthenticationEntryPoint loginServerAuthenticationEntryPoint; // 인가 에러 처리

    // JWT 검증을 위한 필터
    // 초기 Spring Filter를 Spring에 제어가 불가능했지만, 현재 제어 가능함
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http) {

        log.info(this.getClass().getName() + ".filterChain Start!");


        //CSRF : 인증된 사용자의 권한을 이용하여 악의적인 요청을 실행하는 공격
        //CORS : 추가적인 HTTP 헤더를 사용하여 한 출처에서 실행 중인 웹 애프리케이션이 다른 URL이 선택한 리소스에 접근할 수 있는 권한부여
        http.csrf(ServerHttpSecurity.CsrfSpec::disable); // POST 방식 전송을 위해 csrf 막기
        http.cors(ServerHttpSecurity.CorsSpec::disable); // CORS 사용하지 않음
        http.formLogin(ServerHttpSecurity.FormLoginSpec::disable); // ApiGateway 에서 로그인 기능 사용하지 않음

        http.exceptionHandling(exceptionHandlingSpec ->
                exceptionHandlingSpec.accessDeniedHandler(accessDeniedHandler)); // 인증 에러 처리

        http.exceptionHandling(exceptionHandlingSpec ->
                exceptionHandlingSpec.authenticationEntryPoint(loginServerAuthenticationEntryPoint)); // 인가 에러 처리

        // stateless 방식의 애플리케이션이 되도록 설정 즉 세션 사용안함
        http.securityContextRepository(NoOpServerSecurityContextRepository.getInstance());

        http.authorizeExchange(authz -> authz // 페이지 접속 권한 설정
                        // USER 권한
                        .pathMatchers("/notice/**").hasAnyAuthority("ROLE_USER")
//                        .pathMatchers("/notice/**").permitAll()

                        // USER 권한
                        .pathMatchers("/user/**").hasAnyAuthority("ROLE_USER")

                        .pathMatchers("/login/**").permitAll()

                        .pathMatchers("/reg/**").permitAll()

                        // Api Service
                        .pathMatchers("/api/**").permitAll()


                        /**
                         * 추후 토큰 없으면 인가 안되게 막아야됨!
                         */
                        .pathMatchers("/facecan/**").permitAll()
                        .pathMatchers("/SAM/**").permitAll()
                        .pathMatchers("/alarm/**").permitAll()
                        .pathMatchers("/chat/**").permitAll()




//                        .anyExchange().authenticated() // 그외 나머지 url 요청은 인증된 사용자만 가능 즉 권한이 없더라도 로그인은 되어있어야함
                        .anyExchange().permitAll() // 그 외 나머지 url 요청은 인증 받지 않아도 접속 가능함
        );

        // Spring Security 필터들이 실행되기 전에 JWT 필터 실행
        http.addFilterAt(jwtAuthenticationFilter, SecurityWebFiltersOrder.HTTP_BASIC);

        log.info(this.getClass().getName() + ".filterChain End!");

        return http.build();
    }
}