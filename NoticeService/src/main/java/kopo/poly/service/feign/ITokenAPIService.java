package kopo.poly.service.feign;

import kopo.poly.dto.TokenDTO;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@RefreshScope
@FeignClient(name = "ITokenAPIService", url = "${api.gateway}")
public interface ITokenAPIService {


    /**
     *
     * Access Token을 HTTP 인증 헤더에 넣고 호출하여 유저정보를 반환
     * 호출 서버 Port (yml에 적용한 ApiGateway port 사용 )
     * ApiGateway 개발전은 userService port 11000, 개발 후에는 ApiGateway 13000
     *
     * @param bearerToken
     * @return 유저 정보 DTO (회원아이디, 권한)
     */
    @PostMapping(value = "/user/getTokenInfo")
    TokenDTO getTokenInfo(@RequestHeader(HttpHeaders.AUTHORIZATION) String bearerToken);
}
