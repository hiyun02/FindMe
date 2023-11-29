package kopo.poly.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class RoutConfig {

    /**
     * Gateway로 접근되는 모든 요청에 대해 URL 분리하기
     */
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes().route(r -> r.path("/notice/**") // 공지사항
                .uri("lb://NOTICE-SERVICE:12000") // 연결될 서버 주소
        ).route(r -> r.path("/user/**") // 회원정보 확인
                .uri("lb://USER-SERVICE:11000") // 연결될 서버 주소

        ).route(r -> r.path("/login/**") // 로그인 => 로그인이 필요하지 않는 서비스를 별로 URL로 분리
                .uri("lb://USER-SERVICE:11000") // 연결될 서버 주소

        ).route(r -> r.path("/reg/**") // 회원가입 => 로그인이 필요하지 않는 서비스를 별로 URL로 분리
                .uri("lb://USER-SERVICE:11000") // 연결될 서버 주소

        ).route(r -> r.path("/api/**") // Api 가져오는 서비스
                .uri("lb://API-SERVICE:16000") // 연결될 서버 주소


                /**
                 * 배포 시 설정 필요
                  */

        ).route(r -> r.path("/facecan/**") // skOpenApi 얼굴 인식 서버
                .uri("lb://FACE-SERVICE:17000") // 연결될 서버 주소


        ).route(r -> r.path("/SAM/**") // skOpenApi 얼굴 인식 서버
                .uri("lb://FACE-SERVICE:17000") // 연결될 서버 주소


        ).route(r -> r.path("/alarm/**") // ALARM 가져오는 서비스
                .uri("lb://ALARM-SERVICE:18000") // 연결될 서버 주소


        ).build();
    }
}
