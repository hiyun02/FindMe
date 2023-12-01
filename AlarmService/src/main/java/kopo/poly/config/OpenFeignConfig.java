package kopo.poly.config;

import feign.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenFeignConfig {





    @Bean
    Logger.Level feignLoggerLevel() {

        /**
         *  OpenFeign을 활용한 로그 찍기
         *
         *  None : 로깅 x (기본값)
         *  BASIC : 요청 메소드와 URL와 응답 상태 실행시간 로깅
         *  HEADERS : 요청과 응답 헤더와 함께 기본 정보들을 남김
         *  FULL : 요청과 응답에 대한 헤더와 바디, 메타 데이터를 남김
         */
        return Logger.Level.FULL;
    }


}
