package kopo.poly.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import kopo.poly.dto.MsgDTO;
import kopo.poly.dto.NoticeDTO;
import kopo.poly.dto.TokenDTO;
import kopo.poly.service.INoticeService;
import kopo.poly.service.ITokenAPIService;
import kopo.poly.util.CmmUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:13000", "http://localhost:14000"}, allowedHeaders = {"POST, GET"}, allowCredentials = "true")
@Tag(name = "공지사항 서비스", description = "공지사항 구현을 위한 API")
@Slf4j
@RequestMapping(value = "/notice")
@RequiredArgsConstructor
@RestController
public class NoticeController {


    private final INoticeService noticeService;

    private final ITokenAPIService tokenAPIService;

    private final String HEADER_PREFIX = "Bearer ";

    /**
     * 공지사항 리스트 조회
     */

    @Operation(summary = "공지사항 리스트 API", description = "공지사항 리스트 정보 제공하는 API"
            , responses = {@ApiResponse(responseCode = "200", description = "OK")
            , @ApiResponse(responseCode = "404", description = "Page Not Found!"),})
    @PostMapping(value = "noticeList")
    public List<NoticeDTO> noticeList() {

        log.info(this.getClass().getName() + ".noticeList Start!");

        List<NoticeDTO> rList = Optional.ofNullable(noticeService.getNoticeList()).orElseGet(ArrayList::new);

        log.info(this.getClass().getName() + ".noticeList End!");

        return rList;
    }


    /**
     * 공지사항 상세보기
     */
    @Operation(summary = "공지사항 상세보기 결과제공 API", description = "공지사항 상세보기 결과 및 조회수 증가 API",
            parameters = {@Parameter(name = "nSeq", description = "공지사항 글번호"),
                    @Parameter(name = "readCntYn", description = "조회수 증가여부")},
            responses = {@ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "Page Not Found!"),})
    @PostMapping(value = "noticeInfo")
    public NoticeDTO noticeInfo(HttpServletRequest request) throws Exception {

        log.info(this.getClass().getName() + ".noticeInfo Start!");

        String nSeq = CmmUtil.nvl(request.getParameter("nSeq"));
        String readCntYn = CmmUtil.nvl(request.getParameter("readCntYn"));
        boolean readCnt = readCntYn.equals("Y"); // 공지사항 증가여부를 boolean 값으로 변경

        log.info("nSeq : " + nSeq);
        log.info("readCntYn : " + readCntYn);
        log.info("readCnt : " + readCnt);

        NoticeDTO pDTO = NoticeDTO.builder().noticeSeq(Long.parseLong(nSeq)).build();

        NoticeDTO rDTO = Optional.ofNullable(noticeService.getNoticeInfo(pDTO, readCnt))
                .orElseGet(() -> NoticeDTO.builder().build());

        log.info(this.getClass().getName() + ".noticeInfo Start!");

        return rDTO;
    }


    /**
     * 공지사항 등록
     * @param request 공지사항 관련 DTO
     * @param token 사용자 정보
     * @return 성공여부 DTO
     */
    @Operation(summary = "공지사항 등록 API", description = "공지사항 등록 및 등록결과를 제공하는 API",
            responses = {@ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "Page Not Found!"),})
    @PostMapping(value = "noticeInsert")
    public MsgDTO noticeInsert(HttpServletRequest request,
                               @CookieValue(value = "${jwt.token.access.name}") String token) {
        log.info(this.getClass().getName() + ".noticeInsert Start!");

        String msg = ""; // 메시지 내용
        int res = 0; // 성공 여부
        MsgDTO dto = null; // 결과 메시지 구조

        try {
            TokenDTO tDTO = tokenAPIService.getTokenInfo(HEADER_PREFIX +token);
            log.info("TokenDTO : " + tDTO);

            String userId = CmmUtil.nvl(tDTO.userId());//JWT Access 토큰으로부터 회원아이디 가져오기
            String title = CmmUtil.nvl(request.getParameter("title")); // 제목
            String noticeYn = CmmUtil.nvl(request.getParameter("noticeYn")); // 공지글 여부
            String contents = CmmUtil.nvl(request.getParameter("contents")); // 내용

            log.info("userId : " + userId);
            log.info("title : " + title);
            log.info("noticeYn : " + noticeYn);
            log.info("contents : " + contents);

            NoticeDTO pDTO = NoticeDTO.builder().userId(userId).title(title)
                    .noticeYn(noticeYn).contents(contents).build();


            noticeService.insertNoticeInfo(pDTO);

            msg = "등록되었습니다.";
            res = 1;
        } catch (Exception e) {
            msg = "실패하였습니다. : " + e.getMessage();
            log.info(e.toString());
            e.printStackTrace();
        }finally {
            dto = MsgDTO.builder().result(res).msg(msg).build();

            log.info(this.getClass().getName() + ".noticeInsert End!");
        }

        return dto;
    }






    /**
     * 공지사항 수정
     *
     * @param request 공지사항 관련
     * @param token 유저정보
     * @return 결과 정보(성공, 실패)
     */
    @Operation(summary = "공지사항 수정 API", description = "공지사항 수정 및 수정결과를 제공하는 API",
            responses = {@ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "Page Not Found!"),})
    @PostMapping(value = "noticeUpdate")
    public MsgDTO noticeUpdate(HttpServletRequest request,
                               @CookieValue(value = "${jwt.token.access.name}") String token) {

        log.info(this.getClass().getName() + ".noticeUpdate Start!");

        String msg = ""; // 메시지 내용
        int res = 0; // 성공 여부
        MsgDTO dto = null; // 결과 메시지 구조

        try {
            TokenDTO tDTO = tokenAPIService.getTokenInfo(HEADER_PREFIX + token);
            log.info("TokenDTO : " + tDTO); // Token 값 출력하기

            String userId = CmmUtil.nvl(tDTO.userId()); // token 에서 추출한 Id값
            String nSeq = CmmUtil.nvl(request.getParameter("nSeq")); // 글번호(PK)
            String title = CmmUtil.nvl(request.getParameter("title")); // 제목
            String noticeYn = CmmUtil.nvl(request.getParameter("noticeYn")); // 공지글 여부
            String contents = CmmUtil.nvl(request.getParameter("contents")); // 내용

            log.info("userId : " + userId);
            log.info("nSeq : " + nSeq);
            log.info("title : " + title);
            log.info("noticeYn : " + noticeYn);
            log.info("contents : " + contents);

            NoticeDTO pDTO = NoticeDTO.builder().userId(userId).noticeSeq(Long.parseLong(nSeq))
                    .title(title).noticeYn(noticeYn).contents(contents).build();

            noticeService.updateNoticeInfo(pDTO);

            msg = "수정되었습니다.";
            res = 0;

        } catch (Exception e) {
            msg = "실패하였습니다. : " + e.getMessage();
            log.info(e.toString());
            e.printStackTrace();
        } finally {

            // 결과 메시지 전달하기
            dto = MsgDTO.builder().result(res).msg(msg).build();

            log.info(this.getClass().getName() + ".noticeUpdate End!");
        }

        return dto;

    }


    /***
     *  공지사항 삭제
     * @param request 공지사항 삭제 Seq
     * @return 삭제 성공 여부
     */
    @Operation(summary = "공지사항 삭제 API", description = "공지사항 삭제 및 삭제 결과를 제공하는 API",
            responses = {@ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "Page Not Found!"),})
    @PostMapping(value = "noticeDelete")
    public MsgDTO noticeDelete(HttpServletRequest request) {
        log.info(this.getClass().getName() + ".noticeDelete Start!");

        String msg = ""; // 메시지 내용
        int res = 0; // 성공 여부
        MsgDTO dto = null; // 결과 메시지 구조
        try {
            String nSeq = CmmUtil.nvl(request.getParameter("nSeq")); // 글번호(PK)

            log.info("nSeq : " + nSeq);

            NoticeDTO pDTO = NoticeDTO.builder().noticeSeq(Long.parseLong(nSeq)).build();

            noticeService.deleteNoticeInfo(pDTO);

            msg = "삭제되었습니다.";
            res = 1;
        } catch (Exception e) {
            msg = "실패하였습니다. : " + e.getMessage();
            log.info(e.toString());
            e.printStackTrace();

        } finally {
            dto = MsgDTO.builder().result(res).msg(msg).build();

            log.info(this.getClass().getName() + ".noticeDelete End!");
        }
        return dto;
    }



}
