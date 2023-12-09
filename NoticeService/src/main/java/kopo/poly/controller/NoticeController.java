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
import kopo.poly.service.feign.ITokenAPIService;
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
     * 실종정보 게시판 등록
     *
     * @param noticeDTO 실종 정보
     * @param token      사용자 정보
     * @return
     */
    @Operation(summary = "실종정보 등록 API", description = "실종정보 등록 및 등록결과를 제공하는 API",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "Page Not Found!")})
    @PostMapping(value = "insertNoticeInfo")
    public MsgDTO insertNoticeInfo(@RequestBody NoticeDTO noticeDTO,
                                    @CookieValue(value = "${jwt.token.access.name}") String token) {

        log.info(this.getClass().getName() + ".insertNoticeInfo Start!");

        String msg = ""; // 메시지 내용
        int res = 0; // 성공 여부
        MsgDTO dto = null; // 결과 메시지 구조

        try {
            log.info("NoticeService로 넘어온 실종자 정보 : " + noticeDTO.toString());

            TokenDTO tDTO = tokenAPIService.getTokenInfo(HEADER_PREFIX + token);
            log.info("TokenDTO : " + tDTO);
            String userId = tDTO.userId();


            msg = "등록되었습니다.";
            res = 1;

        } catch (Exception e) {
            msg = "실패하였습니다. : " + e.getMessage();
            log.info(e.toString());
            e.printStackTrace();
        } finally {
            dto = MsgDTO.builder().result(res).msg(msg).build();

            log.info(this.getClass().getName() + ".insertNoticeInfo End!");
        }

        return dto;
    }

    /**
     * 실종자 정보 리스트 조회
     */

    @Operation(summary = "실종자 정보 리스트 API", description = "카테고리 별 실종자 리스트 제공하는 API"
            , responses = {@ApiResponse(responseCode = "200", description = "OK")
            , @ApiResponse(responseCode = "404", description = "Page Not Found!"),})
    @PostMapping(value = "noticeList")
    public List<NoticeDTO> noticeList(HttpServletRequest request) throws Exception {

        log.info(this.getClass().getName() + ".noticeList Start!");

        String category = CmmUtil.nvl(request.getParameter("category"));
        log.info("noticeList category : {}", category);

        List<NoticeDTO> rList = Optional.ofNullable(noticeService.getNoticeList(category)).orElseGet(ArrayList::new);

        log.info(this.getClass().getName() + ".noticeList End!");

        return rList;
    }

    /**
     * 실종자 정보 상세보기
     */
    @Operation(summary = "실종자 정보 상세보기 결과제공 API", description = "실종자 정보 상세보기 결과 및 조회수 증가 API",
            parameters = {@Parameter(name = "noticeSeq", description = "실종자 정보 글번호")},
            responses = {@ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "Page Not Found!"),})
    @PostMapping(value = "noticeInfo")
    public NoticeDTO noticeInfo(HttpServletRequest request) throws Exception {

        log.info(this.getClass().getName() + ".noticeInfo Start!");

        String noticeSeq = CmmUtil.nvl(request.getParameter("noticeSeq"));
        log.info("noticeSeq : " + noticeSeq);

        NoticeDTO pDTO = NoticeDTO.builder().noticeSeq(Long.parseLong(noticeSeq)).build();

        NoticeDTO rDTO = Optional.ofNullable(noticeService.getNoticeInfo(pDTO, true))
                .orElseGet(() -> NoticeDTO.builder().build());

        log.info(this.getClass().getName() + ".noticeInfo Start!");

        return rDTO;
    }


    /**
     * 실종자 정보 수정
     *
     * @param request 실종자 정보 관련
     * @param token   유저정보
     * @return 결과 정보(성공, 실패)
     */
    @Operation(summary = "실종자 정보 수정 API", description = "실종자 정보 수정 및 수정결과를 제공하는 API",
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
            String noticeSeq = CmmUtil.nvl(request.getParameter("noticeSeq")); // 글번호(PK)
            String title = CmmUtil.nvl(request.getParameter("title")); // 제목
            String noticeYn = CmmUtil.nvl(request.getParameter("noticeYn")); // 공지글 여부
            String contents = CmmUtil.nvl(request.getParameter("contents")); // 내용

            log.info("userId : " + userId);
            log.info("noticeSeq : " + noticeSeq);
            log.info("title : " + title);
            log.info("noticeYn : " + noticeYn);
            log.info("contents : " + contents);

            NoticeDTO pDTO = NoticeDTO.builder().regId(userId).noticeSeq(Long.parseLong(noticeSeq))
                    .title(title).build();

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
     *  실종자 정보 삭제
     * @param request 실종자 정보 삭제 Seq
     * @return 삭제 성공 여부
     */
    @Operation(summary = "실종자 정보 삭제 API", description = "실종자 정보 삭제 및 삭제 결과를 제공하는 API",
            responses = {@ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "Page Not Found!"),})
    @PostMapping(value = "noticeDelete")
    public MsgDTO noticeDelete(HttpServletRequest request) {
        log.info(this.getClass().getName() + ".noticeDelete Start!");

        String msg = ""; // 메시지 내용
        int res = 0; // 성공 여부
        MsgDTO dto = null; // 결과 메시지 구조
        try {
            String noticeSeq = CmmUtil.nvl(request.getParameter("noticeSeq")); // 글번호(PK)

            log.info("noticeSeq : " + noticeSeq);

            NoticeDTO pDTO = NoticeDTO.builder().noticeSeq(Long.parseLong(noticeSeq)).build();

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
