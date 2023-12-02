package kopo.poly.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import kopo.poly.dto.AlarmMsgDTO;
import kopo.poly.dto.DeviceDTO;
import kopo.poly.dto.TokenDTO;
import kopo.poly.service.IAlarmService;
import kopo.poly.service.ITokenAPIService;
import kopo.poly.util.CmmUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = {"http://localhost:13000", "http://localhost:14000"},
        allowedHeaders = {"POST, GET"},
        allowCredentials = "true")
@Tag(name = "알람 관련 API", description = "알람 관련 API 설명입니다.") // Swagger 설명
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/alarm")
public class AlarmController {

    private final IAlarmService alarmService;

    private final ITokenAPIService tokenAPIService;

    private final String HEADER_PREFIX = "Bearer ";




    /**
     * 사용자기기 알림 고유 토큰 저장
     * @param request userId, 기기의 token 값
     * @return 성공여부 (성공 : 1, 실패 : 0)
     * @throws Exception
     */
    @Operation(summary = "사용자기기 알림 고유 토큰 저장 API", description = "사용자기기 알림 고유 토큰 저장"
            , responses = {
            @ApiResponse(responseCode = "1", description = "성공"),
            @ApiResponse(responseCode = "0", description = "실패"),
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "404", description = "Page Not Found"),
    })
    @PostMapping("saveToken")
    public int saveToken(HttpServletRequest request, @CookieValue(value = "${jwt.token.access.name}") String token) throws Exception {
        log.info(getClass().getName() + "토큰 저장 시작");


        TokenDTO tDTO = tokenAPIService.getTokenInfo(HEADER_PREFIX +token);
        log.info("TokenDTO : " + tDTO);


        String pushToken = CmmUtil.nvl(request.getParameter("pushToken"));
        String userId = CmmUtil.nvl(tDTO.userId()); // token 에서 추출한 Id값

        log.info("userId : " + userId);
        log.info("pushToken : " + pushToken);


        DeviceDTO pDTO = DeviceDTO.builder()
                .userId(userId)
                .pushToken(pushToken)
                .build();



        int res = alarmService.saveToken(pDTO);

        log.info("res : " + res);
        if (res == 1) {
            log.info("저장 성공");
        } else if (res == 2) {
            log.info("이미 있는 토큰 값");
        } else {
            log.info("저장 실패");
        }

        log.info(getClass().getName() + "토큰 저장 종료");

        return res;
    }



    /**
     * Push 메세지 보내기
     * @param request 메시지(제목, 내용, url)
     * @return 성공 여부
     * @throws Exception
     */
    @Operation(summary = "Push 메세지 보내기 API", description = "Push 메세지 보내기"
            , responses = {
            @ApiResponse(responseCode = "1", description = "성공"),
            @ApiResponse(responseCode = "0", description = "실패"),
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "404", description = "Page Not Found"),
    })
    @PostMapping("sendMessing")
    public int sendMessing(HttpServletRequest request, @CookieValue(value = "${jwt.token.access.name}") String token) throws Exception {
        log.info(getClass().getName() + "메세지 보내기 시작");

        TokenDTO tDTO = tokenAPIService.getTokenInfo(HEADER_PREFIX +token);
        log.info("TokenDTO : " + tDTO);


        String title = CmmUtil.nvl(request.getParameter("title"));
        String content = CmmUtil.nvl(request.getParameter("content"));
        String url = CmmUtil.nvl(request.getParameter("url"));

        log.info("제목 :" +title);
        log.info("내용 :" +content);
        log.info("url :" +url);

        String userId = CmmUtil.nvl(tDTO.userId()); // token 에서 추출한 Id값
        List<String> deviceDTO = Optional.ofNullable(getDevice(userId)).orElseGet(ArrayList::new);

        AlarmMsgDTO pDTO = AlarmMsgDTO.builder()
                .deviceDTO(deviceDTO)
                .userId(userId)
                .title(title)
                .content(content)
                .url(url)
                .build();

        int res = alarmService.sendMessing(pDTO);
        log.info(getClass().getName() + "메세지 보내기 종료");

        return res;
    }


    /**
     *
     * @param userId 쿠키에 있는 회원아이디
     * @return 기기의 토큰값이 있는 List
     * @throws Exception
     */
    private List<String> getDevice(String userId)throws Exception {
        log.info(getClass().getName() + "사용자의 기기 가져오기 시작");

        userId = "1111";
        List<String> rList = Optional.ofNullable(alarmService.getDevice(userId)).orElseGet(ArrayList::new);

        log.info(getClass().getName() + "사용자의 기기 가져오기 종료");

        return rList;
    }


    /**
     *  사용자의 알림 내역 전체 조회
     * @param token 쿠키에 있는 JWT Token
     * @return 과거 알림 내역
     * @throws Exception
     */
    @Operation(summary = "사용자의 알림 내역 전체 조회 API", description = "사용자의 알림 내역 전체 조회"
            , responses = {

            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "404", description = "Page Not Found"),
    })
    @PostMapping(value = "getAlarmList")
    public List<AlarmMsgDTO> getAlarmList(@CookieValue(value = "${jwt.token.access.name}") String token)throws Exception {
        log.info(getClass().getName() + "알람 내역 가져오기 시작");

        TokenDTO tDTO = tokenAPIService.getTokenInfo(HEADER_PREFIX +token);
        log.info("TokenDTO : " + tDTO);

        String userId = CmmUtil.nvl(tDTO.userId()); // token 에서 추출한 Id값
        List<AlarmMsgDTO> rList =alarmService.getAlarmList(userId);

        log.info(getClass().getName() + "알람 내역 가져오기 종료");
        return rList;
    }



    /**
     *  사용자의 알림 전체 삭제
     * @param token 쿠키에 있는 JWT Token
     * @return 성공 여부
     * @throws Exception
     */
    @Operation(summary = "사용자의 알림 전체 삭제 API", description = "사용자의 알림 전체 삭제"
            , responses = {
            @ApiResponse(responseCode = "1", description = "성공"),
            @ApiResponse(responseCode = "0", description = "실패"),
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "404", description = "Page Not Found"),
    })

    @PostMapping("deleteAllAlarm")
    public int deleteAllAlarm(@CookieValue(value = "${jwt.token.access.name}") String token) throws Exception {
        log.info(getClass().getName() + "토큰 저장 시작");

        TokenDTO tDTO = tokenAPIService.getTokenInfo(HEADER_PREFIX +token);
        log.info("TokenDTO : " + tDTO);

        String userId = CmmUtil.nvl(tDTO.userId()); // token 에서 추출한 Id값

        int res = alarmService.deleteAllAlarm(userId);

        log.info(getClass().getName() + "토큰 저장 종료");

        return res;
    }


    /**
     *  사용자의 알림 단일 삭제
     * @param request 알림 pK
     * @return 성공 여부
     * @throws Exception
     */
    @Operation(summary = "사용자의 알림 단일 삭제 API", description = "사용자의 알림 전체 삭제"
            , responses = {
            @ApiResponse(responseCode = "1", description = "성공"),
            @ApiResponse(responseCode = "0", description = "실패"),
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "404", description = "Page Not Found"),
    })

    @PostMapping("deleteOneAlarm")
    public int deleteOneAlarm(HttpServletRequest request) throws Exception {
        log.info(getClass().getName() + "토큰 저장 시작");


        String seq = CmmUtil.nvl(request.getParameter("Seq"));

        int res = alarmService.deleteOneAlarm(seq);

        log.info(getClass().getName() + "토큰 저장 종료");

        return res;
    }




}