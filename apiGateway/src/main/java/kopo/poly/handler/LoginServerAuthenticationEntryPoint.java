package kopo.poly.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kopo.poly.dto.MsgDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;


/**
 * 인가 : Spring Security 에 정의된 권한이 없는 경우
 */
@Slf4j
@Component
public class LoginServerAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {

    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException ex) {

        ServerHttpResponse response = exchange.getResponse();

        response.setStatusCode(HttpStatus.UNAUTHORIZED); // 승인되지 않는 접근
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        // 에러 메시지 구조
        MsgDTO pDTO = MsgDTO.builder().result(100).msg(ErrorMsg.ERR100.getValue()).build();

        String json = null;

        try {
            // DTO 를 JSON 구조 문자열로 변환
            json = new ObjectMapper().writeValueAsString(pDTO);

        } catch (JsonProcessingException e) {
            log.info("JSON 파싱 에러!!");
        }

        // 비동기식 처리는 바이트 단위로 처리하기 때문에  json 을 byte 단위로 변경
        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);

        // response d에 byte 를 추가
        DataBuffer buffer = response.bufferFactory().wrap(bytes);


        return response.writeWith(Mono.just(buffer));
    }
}
