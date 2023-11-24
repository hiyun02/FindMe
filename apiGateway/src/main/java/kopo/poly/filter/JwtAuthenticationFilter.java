package kopo.poly.filter;

import kopo.poly.dto.TokenDTO;
import kopo.poly.jwt.JwtStatus;
import kopo.poly.jwt.JwtTokenProvider;
import kopo.poly.jwt.JwtTokenType;
import kopo.poly.util.CmmUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Optional;


@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements WebFilter {

    @Value("${jwt.token.access.valid.time}")
    private long accessTokenValidTime;

    @Value("${jwt.token.access.name}")
    private String accessTokenName;

    @Value("${server.domain}")
    private String domain;


    // JWT Token 객체
    private final JwtTokenProvider jwtTokenProvider;

    /**
     *  쿠키에 저장된 JWT 토큰 삭제
     * @param tokenName 토큰 이름
     * @return 비어있는 쿠키
     *
     */

    private ResponseCookie deleteTokenCookie(String tokenName) {
        log.info(getClass().getName() + "token 삭제 시작");

        log.info("삭제할 token 이름 : " + tokenName);

        ResponseCookie cookie = ResponseCookie.from(tokenName, "") // 토큰 값을 빈값
                .maxAge(0) // token 만료
                .build();

        log.info(getClass().getName() + "token 삭제 종료");

        return cookie;
    }

    /**
     * 쿠키에
     * @param tokenName
     * @param tokenValidTime
     * @param token
     * @return
     */

    private ResponseCookie createTokenCookie(String tokenName, long tokenValidTime, String token) {
        log.info(getClass().getName() + "token 생성 구조 정의 시작");
        log.info("토큰 이름 : " + tokenName);
        log.info("토큰 : " + token);

        ResponseCookie cookie = ResponseCookie.from(tokenName, token)
                .domain(domain)
                .path("/")
                .maxAge(tokenValidTime) // JWT Refresh Token 만료시간 설정 5분
                .httpOnly(true)
                .build();

        log.info(getClass().getName() + "token 생성 구조 정의 종료");

        return cookie;
    }


    /**
     *  모든 API 요청에 대해 처리할 Filter 내용 정의
     *
     *  Mono : 정적인 페이지 , 결과값에 체인값을 계속 걸거기 떄문에 Mono를 사용
     *  WebFlux : 정적인 페이지
     *
     * @param exchange
     *  비동기 처리방식에서 사용되며 , ServerHttpRequest의 부모 객체로 스트림의 형태의 데이터를 respons 형태로 바꿔줌
     * @param chain 스프링 웹 필터에서 여러 필터를 순차적으로 실행하기 위한 인터페이스
     * @return
     *
     */

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        // 스트림 형태의 데이터를 HTTP 형태로 바꿔줌
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        log.info(this.getClass().getName() + ".filter Start!");

        log.info("request :" + request);
        log.info("request :" + request.getPath());

        // accessToken 가져오기
        String accessToken = CmmUtil.nvl(jwtTokenProvider.resolveToken(request, JwtTokenType.ACCESS_TOKEN));

        log.info("accessToken : " + accessToken);

        // access Token 유효기간 검증
        JwtStatus accessTokenStatus = jwtTokenProvider.validateToken(accessToken);

        log.info("access Token 상태 : " + accessTokenStatus);

        // 토큰이 유효하면
        if (accessTokenStatus == JwtStatus.ACCESS) {
            // 토큰이 유효하면 Security 에 저장
            Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
            return chain.filter(exchange)
                    .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication));
        }
        // 만료된 토큰일 경우
        else if (accessTokenStatus == JwtStatus.EXPIRED || accessTokenStatus == JwtStatus.DENIED) {
            // Access Token 이 만료되면 Refresh Token 유효한지 체크

            //Refresh token 가져오기
            String refreshToken = CmmUtil.nvl(jwtTokenProvider.resolveToken(request, JwtTokenType.REFRESH_TOKEN));

            //Refresh token 유효기간 체크
            JwtStatus refreshTokenStatus = jwtTokenProvider.validateToken(refreshToken);

            log.info("refreshToken 상태 : " + refreshTokenStatus);

            if (refreshTokenStatus == JwtStatus.ACCESS) {

                //Refresh Token 이 유효하므로, AccessToken 재발급
                TokenDTO rDTO =
                        // token 에서 정보를 Optional 객체로 감쌈
                        Optional.of(jwtTokenProvider.getTokenInfo(refreshToken))
                        // Optional 객체에 값이 존재하지 않을 경우 DTO 를 생성
                        .orElseGet(() -> TokenDTO.builder().build());

                String userId = CmmUtil.nvl(rDTO.userId()); // 회원 아이디
                String userRoles = CmmUtil.nvl(rDTO.role()); // 회원 권한

                log.info("refreshToken userId : " + userId);
                log.info("refreshToken userRoles : " + userRoles);

                //Access Token 재 발급
                String reAccessToken = jwtTokenProvider.createToken(userId, userRoles);

                // 만약 기존 존재하는 Access Token 있다면, 삭제
                response.addCookie(this.deleteTokenCookie(accessTokenName));

                // 재발급된 Access Token 을 쿠키에 저장함
                response.addCookie(this.createTokenCookie(accessTokenName, accessTokenValidTime, reAccessToken));

                // 토큰이 유효하면 유저 정보를 받아오기
                Authentication authentication = jwtTokenProvider.getAuthentication(reAccessToken);

                return chain.filter(exchange)
                        .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication));
            }else if (refreshTokenStatus == JwtStatus.EXPIRED) {
                log.info("Refresh Token 만료 - 스프링 시큐리티가 로그인 페이지로 이동 시킴");

            } else {
                log.info("Refresh Token 오류 - 스프링 시큐리티가 로그인 페이지로 이동 시킴");

            }
        }

        return chain.filter(exchange);
    }


}
