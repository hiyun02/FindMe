package kopo.poly.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import kopo.poly.auth.AuthInfo;
import kopo.poly.auth.JwtTokenProvider;
import kopo.poly.auth.JwtTokenType;
import kopo.poly.dto.MsgDTO;
import kopo.poly.dto.UserInfoDTO;
import kopo.poly.util.CmmUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:13000", "http://localhost:14000"},
        allowedHeaders = {"POST, GET"},
        allowCredentials = "true")
@Tag(name = "로그인 관련 API", description = "로그인 관련 API 설명입니다.") // Swagger 설명
@Slf4j
@RequestMapping(value = "/login")
@RequiredArgsConstructor
@RestController
public class LoginController {


    @Value("${jwt.token.access.valid.time}")
    private long accessTokenValidTime;

    @Value("${jwt.token.access.name}")
    private String accessTokenName;

    @Value("${jwt.token.refresh.valid.time}")
    private long refreshTokenValidTime;

    @Value("${jwt.token.refresh.name}")
    private String refreshTokenName;

    @Value("${server.domain}")
    private String domain;


    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "로그인 성공처리 API", description = "로그인 성공처리 API"
            , responses = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "404", description = "Page Not Found"),
    })
    @PostMapping(value = "loginSuccess")
    public MsgDTO loginSuccess(@AuthenticationPrincipal AuthInfo authInfo,
                               // 인증된 사용자의 세부 정보를 담고 있는 객체를 전달받음
                               HttpServletResponse response) {
        // 클라이언트로부터 요청을 받은 후, 서버 response 객체를 사용해 응답을 생성하여 클라이언트에게 전송

        log.info(this.getClass().getName() + ".loginSuccess Start!");

        // Spring Security에 저장된 정보 가져오기
        UserInfoDTO rDTO = Optional.ofNullable(authInfo.userInfoDTO())
                .orElseGet(() -> UserInfoDTO.builder().build());

        String userId = CmmUtil.nvl(rDTO.userId());
        String userName = CmmUtil.nvl(rDTO.userName());
        String userRoles = CmmUtil.nvl(rDTO.roles());

        log.info("userId : " + userId);
        log.info("userName : " + userName);
        log.info("userRoles : " + userRoles);

        // Access Token 생성
        String accessToken = jwtTokenProvider.createToken(userId, userRoles, JwtTokenType.ACCESS_TOKEN);
        log.info("accessToken : " + accessToken);


        ResponseCookie cookie = ResponseCookie.from(accessTokenName, accessToken)
                .domain(domain)
                .path("/")
//                .secure(true)
//                .sameSite("None")
                .maxAge(accessTokenValidTime) // JWT Refresh Token 만료시간 설정
                .httpOnly(true)
                .build();

        // 기존 쿠기 모두 삭제하고, Cookie에 Access Token 저장하기
        response.setHeader("Set-Cookie", cookie.toString());

        cookie = null;

       /*
            Refresh Token 생성
            Refresh Token 은 보안 상 노출되면, 위험하기에 DB에 저장하고 DB 조회용으로만 사용
            Refresh Token 은 Access Token에 비해 만료시간을 길게 설정함
         */

        // Refresh Token 생성
        String refreshToken = jwtTokenProvider.createToken(userId, userRoles, JwtTokenType.REFRESH_TOKEN);

        log.info("refreshToken : " + refreshToken);

        cookie = ResponseCookie.from(refreshTokenName, refreshToken)
                .domain(domain)
                .path("/")
//                .secure(true)
//                .sameSite("None")
                .maxAge(refreshTokenValidTime) // JWT Refresh Token 만료시간 설정
                .httpOnly(true)
                .build();

//         기존 쿠기에 Refresh Token 저장하기
        response.addHeader("Set-Cookie", cookie.toString());

        // 결과 메시지 전달하기
        MsgDTO dto = MsgDTO.builder().result(1).msg(userName + "님 로그인이 성공하였습니다.").build();

        log.info(this.getClass().getName() + ".loginSuccess End!");

        return dto;

    }




    @Operation(summary = "로그인 실패처리  API", description = "로그인 실패처리 API",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "Page Not Found!"),
            }
    )
    @PostMapping(value = "loginFail")
    public MsgDTO loginFail() {

        log.info(this.getClass().getName() + ".loginFail Start!");

        // 결과 메시지 전달하기
        MsgDTO dto = MsgDTO.builder().result(0).msg("로그인이 실패하였습니다.").build();

        log.info(this.getClass().getName() + ".loginFail End!");

        return dto;

    }


}