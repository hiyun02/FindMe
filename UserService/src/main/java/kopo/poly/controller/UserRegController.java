package kopo.poly.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import kopo.poly.auth.UserRole;
import kopo.poly.dto.MailCodeDTO;
import kopo.poly.dto.MsgDTO;
import kopo.poly.dto.UserInfoDTO;
import kopo.poly.service.IMailService;
import kopo.poly.service.IUserInfoService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.EncryptUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@CrossOrigin(origins = {"http://localhost:13000", "http://localhost:14000"},
        allowedHeaders = {"POST, GET"},
        allowCredentials = "true")
@Tag(name = "회원가입을 위한 API", description = "회원가입을 위한 API 설명입니다.")
@Slf4j
@RequestMapping(value = "/reg")
@RequiredArgsConstructor
@RestController
public class UserRegController {

    private final IUserInfoService userInfoSsService;

    // Spring Security 에서 제공하는 비밀번호 암호화 객체(해시 함수)
    private final PasswordEncoder bCryptPasswordEncoder;

    private final IMailService mailService;

    @Operation(summary = "회원가입  API", description = "회원가입 API",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "Page Not Found!"),
            }
    )
    @PostMapping(value = "insertUserInfo")
    public MsgDTO insertUserInfo(HttpServletRequest request) {

        log.info(this.getClass().getName() + ".insertUserInfo start!");

        int res = 0; // 회원가입 결과
        String msg = ""; //회원가입 결과에 대한 메시지를 전달할 변수
        MsgDTO dto; // 결과 메시지 구조

        //웹(회원정보 입력화면)에서 받는 정보를 저장할 변수
        UserInfoDTO pDTO;

        try {

            String userId = CmmUtil.nvl(request.getParameter("userId")); //아이디
            String password = CmmUtil.nvl(request.getParameter("password")); //비밀번호
            String email = CmmUtil.nvl(request.getParameter("email")); //이메일
            String userName = CmmUtil.nvl(request.getParameter("userName")); //이름
            String addr1 = CmmUtil.nvl(request.getParameter("addr1")); //주소
            String addr2 = CmmUtil.nvl(request.getParameter("addr2")); //상세주소
            String userAlarm = CmmUtil.nvl(request.getParameter("userAlarm")); // 알람 여부

            log.info("회원아이디 : " + userId);
            log.info("비밀번호 : " + password);
            log.info("이메일 : " + email);
            log.info("이름 : " + userName);
            log.info("주소 : " + addr1);
            log.info("상세주소 : " + addr2);
            log.info("알림 여부 : " + userAlarm);




            //웹(회원정보 입력화면)에서 받는 정보를 저장할 변수를 메모리에 올리기
            pDTO = UserInfoDTO.builder().userId(userId)
                    .password(bCryptPasswordEncoder.encode(password))
                    .email(EncryptUtil.encAES128CBC(email))
                    .userName(userName)
                    .addr1(addr1).addr2(addr2)
                    .userAlarm(userAlarm)
                    .roles(UserRole.USER.getValue())
                    .build();

            log.info("회원가입 DTO : " + pDTO);
            /*
             * 회원가입
             * */
            res = userInfoSsService.insertUserInfo(pDTO);

            log.info("회원가입 결과(res) : " + res);

            if (res == 1) {
                msg = "회원가입되었습니다.";

                //추후 회원가입 입력화면에서 ajax를 활용해서 아이디 중복, 이메일 중복을 체크하길 바람
            } else if (res == 2) {
                msg = "이미 가입된 아이디입니다.";

            } else {
                msg = "오류로 인해 회원가입이 실패하였습니다.";

            }

        } catch (Exception e) {
            //저장이 실패되면 사용자에게 보여줄 메시지
            msg = "실패하였습니다. : " + e;
            res = 2;
            log.info(e.toString());
            e.printStackTrace();

        } finally {
            dto = MsgDTO.builder().result(res).msg(msg).build();

            log.info(this.getClass().getName() + ".insertUserInfo End!");

        }

        return dto;
    }

    /**
     *  아이디 중복확인
     * @param request 유저아이디
     * @return 아이디 중복값 존재시 1, 중복값 없으면 0
     * @throws Exception
     */
    @Operation(summary = "회원 아이디 중복체크  API", description = "회원 아이디 중복체크",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "Page Not Found!"),
            }
    )
    @PostMapping(value = "userIdCheck")
    public Map<String, Integer> idCheck(HttpServletRequest request) throws Exception {

        log.info(getClass().getName() + "idCheck start ");

        Map<String, Integer> map = new HashMap<>();

        String userId = CmmUtil.nvl(request.getParameter("userId"));

        // 아이디 중복값 존재시 1, 중복값 없으면 0
        int res = userInfoSsService.idCheck(userId);

        map.put("code", res);

        log.info("중복 결과 값 :" + res);

        log.info(getClass().getName() + "idCheck End ");

        return map;
    }


    /**
     * 회원 가입시 인증 처리 + 아이디 비밀번호 찾기 떄 인증 방식으로 사용
     *
     * @param request (유저 메일, mail 랜덤 코드)
     * @return 이미 가입된 이메일 1, 인증코드 전송 성공 2 , 전송 실패 나머지
     * @throws Exception
     */

    @Operation(summary = "회원 아이디 중복체크  API", description = "회원 아이디 중복체크",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "Page Not Found!"),
            }
    )
    @PostMapping(value = "sendCode")
    public int sendCode(HttpServletRequest request) throws Exception {
        log.info(getClass().getName() + "sendCode start");

        Map<String, Integer> map = new HashMap<>();

        MailCodeDTO mDTO = MailCodeDTO.builder()
                .toMail(CmmUtil.nvl(request.getParameter("email")))
                .mailCode(CmmUtil.nvl(request.getParameter("mailCode")))
                .build();

        log.info(" userId : " + mDTO.toMail());
        log.info("인증 메일 번호 : " + mDTO.mailCode());

        int res = mailService.sendCode(mDTO);

        if (res == 1) {
            log.info(this.getClass().getName() + " 이미 가입된 이메일");
        } else if (res == 2) {
            log.info(this.getClass().getName() + " 인증코드가 전송! ");
        } else {
            log.info(this.getClass().getName() + " 인증코드 전송을 실패");
        }

        log.info(getClass().getName() + "sendCode end");

        return res;
    }

    /**
     *  아이디 찾기
     * @param request email
     * @return (DB에 email 이 존재하면 아이디 반환 , 존재하지 않으면 1 반환)
     * @throws Exception
     */
    @Operation(summary = "아이디 찾기 API", description = "아이디 찾기 API",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "Page Not Found!"),
            }
    )
    @PostMapping("idFind")
    public String idFind(HttpServletRequest request) throws Exception {
        log.info(getClass().getName() + "아이디 찾기 시작");

        String email = CmmUtil.nvl(request.getParameter("email"));

        log.info("email : " + email);
        
        String userId = userInfoSsService.idFind(EncryptUtil.encAES128CBC(email));

        log.info("찾는 아이디 : " + userId);

        log.info(getClass().getName() + "아이디 찾기 종료");

        return userId;
    }

    /**
     *
     * 비밀번호 찾기 임시 비밀번호 전송 코드
     * @param request email
     * @return 성공여부 (성공하면 1 , 실패시 0)
     * @throws Exception
     */
    @Operation(summary = "비밀번호 찾기 API", description = "비밀번호 찾기",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "Page Not Found!"),
            }
    )
    @PostMapping("findPassword")
    public int findPassword(HttpServletRequest request) throws Exception {

        log.info(getClass().getName() + "비밀번호 찾기 이메일 발송 시작");

        String email = CmmUtil.nvl(request.getParameter("email"));


        log.info("email : " + email);

        int res = mailService.findPassword(email);

        if (res == 1) {
            log.info("가입한 이메일로 임시 비밀번호 전송 성공");
        } else {
            log.info("이메일 등록 안됨 다시 한번 확인 바람");
        }

        log.info(getClass().getName() + "비밀번호 찾기 이메일 발송  종료");

        return res;

    }







}