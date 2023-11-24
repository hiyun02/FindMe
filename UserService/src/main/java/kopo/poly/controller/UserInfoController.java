package kopo.poly.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import kopo.poly.auth.JwtTokenProvider;
import kopo.poly.auth.JwtTokenType;
import kopo.poly.dto.TokenDTO;
import kopo.poly.dto.UserInfoDTO;
import kopo.poly.service.IUserInfoService;
import kopo.poly.util.CmmUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.IClassFileProvider;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.PreparedStatement;
import java.util.Optional;


@CrossOrigin(origins = {"http://localhost:12000",
        "http://localhost:13000", "http://localhost:14000"},
        allowedHeaders = {"POST, GET", "FEIGN"},
        allowCredentials = "true")
@Tag(name = "로그인된 사용자들이 접근하는 API", description = "로그인된 사용자들이 접근하는 API 설명입니다.")
@Slf4j
@RequestMapping(value = "/user")
@RequiredArgsConstructor
@RestController
public class UserInfoController {

    // JWT 객체
    private final JwtTokenProvider jwtTokenProvider;

    // 회원 서비스
    private final IUserInfoService userInfoService;

    /**
     * JWT Access Token으로부터 user_id 가져오기
     */
    @Operation(summary = "토큰에 저장된 회원정보 가져오기 API", description = "토큰에 저장된 회원정보 가져오기 API",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "Page Not Found!"),
            }
    )
    @PostMapping(value = "getTokenInfo")
    private TokenDTO getTokenInfo(HttpServletRequest request) {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".getTokenInfo Start!");

        //JWT Access 토큰 가져오기
        String jwtAccessToken = CmmUtil.nvl(jwtTokenProvider.resolveToken(request, JwtTokenType.ACCESS_TOKEN));
        log.info("jwtAccessToken : " + jwtAccessToken);

        TokenDTO dto = Optional.ofNullable(jwtTokenProvider.getTokenInfo(jwtAccessToken))
                .orElseGet(() -> TokenDTO.builder().build());

        log.info("TokenDTO : " + dto);

        log.info(this.getClass().getName() + ".getTokenInfo End!");

        return dto;

    }

    /**
     * 회원정보 상세보기 API
     *
     * @param request
     * @return
     * @throws Exception
     */

    @Operation(summary = "회원정보 상세보기 API", description = "회원정보 상세보기 API",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "Page Not Found!"),
            }
    )
    @PostMapping(value = "userInfo")
    public UserInfoDTO userInfo(HttpServletRequest request) throws Exception {

        log.info(this.getClass().getName() + ".userInfo Start!");

        // Access Token에 저장된 회원아이디 가져오기
        String userId = CmmUtil.nvl(this.getTokenInfo(request).userId());

        UserInfoDTO pDTO = UserInfoDTO.builder().userId(userId).build();

        // 회원정보 조회하기
        UserInfoDTO rDTO = Optional.ofNullable(userInfoService.getUserInfo(pDTO))
                .orElseGet(() -> UserInfoDTO.builder().build());

        log.info(this.getClass().getName() + ".userInfo End!");

        return rDTO;

    }

    /**
     * 유저 정보 수정  API
     *
     * @param request (필수 없음, 변경하는 필드만 가져오면 됨)
     * @return 성공여부 (성공 1, 실패 0)
     * @throws Exception
     */
    @Operation(summary = "유저 정보 수정  API", description = "유저 정보 수정",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "Page Not Found!"),
            }
    )
    @PostMapping(value = "userUpdate")
    public int userUpdate(HttpServletRequest request) throws Exception {
        log.info("마이페이지 유저 정보 수정 시작");

        int res = 0;
        // Access Token에 저장된 회원아이디 가져오기
        String userId = CmmUtil.nvl(this.getTokenInfo(request).userId());

        // Page 에서 request 받아오기
        String password = CmmUtil.nvl(request.getParameter("password")); //비밀번호
        String email = CmmUtil.nvl(request.getParameter("email")); //이메일
        String userName = CmmUtil.nvl(request.getParameter("userName")); //이름
        String addr1 = CmmUtil.nvl(request.getParameter("addr1")); //주소
        String addr2 = CmmUtil.nvl(request.getParameter("addr2")); //상세주소
        String userAlarm = CmmUtil.nvl(request.getParameter("userAlarm")); // 알람 여부

        // pDTO에 정보 담기
        UserInfoDTO pDTO = UserInfoDTO.builder()
                .userId(userId)
                .password(password)
                .email(email)
                .userName(userName)
                .addr1(addr1)
                .addr2(addr2)
                .userAlarm(userAlarm)
                .build();

        res = userInfoService.userUpdate(pDTO);

        if (res == 1) {
            log.info("회원 정보 수정 성공");
        } else {
            log.info("회원 정보 수정 안됨 에러 확인 요망");
        }

        log.info("마이페이지 유저 정보 수정 시작");

        return res;
    }

    @PostMapping(value = "userDelete")
    public int userDelete(HttpServletRequest request)throws Exception {
        log.info(getClass().getName()+"userDelete 시작");

        int res = 0;
        // Access Token에 저장된 회원아이디 가져오기
        String userId = CmmUtil.nvl(this.getTokenInfo(request).userId());

        res = userInfoService.userDelete(userId);

        log.info(getClass().getName()+"userDelete 종료");

        return res;
    }
}