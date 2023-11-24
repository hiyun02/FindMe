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
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;


/**
 *  인증 : JWT 토큰에 값이 없는 경우에 에러 처리
 *
 *  추후 만든 Security config 파일에 에러 처리 정의
 **/
@Slf4j
@Component
public class AccessDeniedHandler implements ServerAccessDeniedHandler {

    /**
     * 프리핸들러 : 이벤트가 발생 전에 실행
     * 핸들러 : 이벤트가 발생하면 실행
     * 포스터 핸들러 : 이벤트가 발생 후에 실행
     *
     * 에러메세지를 JSON 구조로 변환하여 반환
     * @param exchange
     * @param denied
     * @return
     */
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException denied) {

        // 에러메세지가 포함되있는 response 를 가져옴
        ServerHttpResponse response = exchange.getResponse();

        //에러메세지 삽입
        response.setStatusCode(HttpStatus.FORBIDDEN); // 금지된 접근
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE); // 보내는 형식 json

        // 에러 메세지 구조 상세
        MsgDTO pDTO = MsgDTO.builder().result(600).msg(ErrorMsg.ERR600.getValue()).build();

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
