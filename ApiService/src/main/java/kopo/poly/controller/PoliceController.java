package kopo.poly.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import kopo.poly.dto.PoliceDTO;
import kopo.poly.service.IPoliceService;
import kopo.poly.util.CmmUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:13000", "http://localhost:14000"}, allowedHeaders = {"POST, GET"}, allowCredentials = "true")
@Tag(name = "API 서비스", description = "경찰청 실종 정보를 위한 API")
@Slf4j
@RequestMapping(value = "/police")
@RequiredArgsConstructor
@RestController
public class PoliceController {

    private final IPoliceService policeService;

    /**
     * DB에 저장된 실종 정보 전체 조회
     *
     * @return 실종 경보  DTO List
     * @throws Exception
     */
    @Operation(summary = "경찰청 실종자 정보 전체 조회", description = "Police DB에 저장된 정보 전체 조회"
            , responses = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "404", description = "Page Not Found"),
    })
    @GetMapping(value = "policeList")
    public List<PoliceDTO> policeList(HttpServletRequest request) throws Exception {
        log.info(this.getClass().getName() + ".실종 경보 API 가져오기 시작 ");

        String order = CmmUtil.nvl(request.getParameter("order"));
        log.info("order : " + order);

        List<PoliceDTO> rList = Optional.ofNullable(policeService.getPoliceInfoList(order)).orElseGet(() -> new ArrayList<PoliceDTO>());

        log.info(this.getClass().getName() + ".실종 경보 API 가져오기 종료");
        return rList;
    }

    /**
     * DB에 저장된 실종 정보 전체 조회
     * policeSeq값 헤더 전송 필요 ex) : ?policeSeq=1
     * @return 실종 경보  DTO
     * @throws Exception
     */
    @Operation(summary = "경찰청 실종자 정보 상세 조회", description = "Police DB에 저장된 정보 상세 조회"
            , responses = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "404", description = "Page Not Found"),
    })
    @GetMapping(value = "policeInfo")
    public PoliceDTO policeInfo(PoliceDTO policeDTO) throws Exception {
        log.info(this.getClass().getName() + ".실종 경보 API 가져오기 시작 ");

        PoliceDTO rDTO = Optional.ofNullable(policeService.getPoliceInfo(policeDTO)).orElseGet(() -> PoliceDTO.builder().build());

        log.info(this.getClass().getName() + ".실종 경보 API 가져오기 종료");
        return rDTO;
    }

    /**
     * DB에 저장된 실종 정보 삭제
     * policeSeq값 헤더 전송 필요 ex) : ?policeSeq=1
     */
    @Operation(summary = "경찰청 실종자 정보 삭제", description = "Police DB에 저장된 정보 삭제"
            , responses = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "404", description = "Page Not Found"),
    })
    @GetMapping(value = "policeDelete")
    public int policeDelete(PoliceDTO policeDTO) throws Exception {
        log.info(this.getClass().getName() + ".실종 경보 API 삭제하기 시작 ");

        int res = policeService.policeDelete(policeDTO);

        log.info(this.getClass().getName() + ".실종 경보 API 삭제하기 종료");
        return res;
    }

    /**
     * 실종 경보 DB에 저장
     *
     * @return 성공 여부 (성공 1, 실패 0)
     * @throws Exception
     */
    @Operation(summary = "실종 경보 DB에 저장", description = "실종 경보 DB에 저장"
            , responses = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "404", description = "Page Not Found"),
    })
    @GetMapping(value = "insertPoliceApi")
    public int insertPoliceApi() throws Exception {
        log.info("실종 경보 API DB 저장 시작 ");
        int res = policeService.insertPoliceApi();
        if (res == 1) {
            log.info(" DB 저장 성공");
        } else {
            log.info("DB 저장 실패");
        }
        log.info("실종 경보 API DB 저장 종료 ");
        return res;
    }


    /**
     * 실종 경보 1건만 가져오기 (테스트용)
     * @return 실종 경보 1건 DTO
     * @throws Exception
     */
    @Operation(summary = "실종 경보 1건만 가져오기 API", description = "실종 경보 1건만 가져오기"
            , responses = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "404", description = "Page Not Found"),
    })
    @GetMapping(value = "policeApiOne")
    public PoliceDTO policeApiOne() throws Exception {
        log.info(this.getClass().getName() + ".실종 경보 API 1건만 가져오기 시작 ");
        PoliceDTO pDTO = Optional.ofNullable(policeService.getPoliceAPIOne()).orElseGet(() -> PoliceDTO.builder().build());
        log.info(this.getClass().getName() + ".실종 경보 API 1건만 가져오기 종료 ");
        return pDTO;
    }
//    /**
//     *  실종 검색 DB에 저장
//     * @return 성공 여부 (성공 1, 실패0)
//     * @throws Exception
//     */
//    @Operation(summary = "실종 검색 DB에 저장 API", description = "실종 검색 DB에 저장"
//            , responses = {
//            @ApiResponse(responseCode = "200", description = "Ok"),
//            @ApiResponse(responseCode = "404", description = "Page Not Found"),
//    })
//    @GetMapping(value = "insertFind")
//    public int insertFind()throws Exception {
//        log.info(getClass().getName() + "실종 검색 DB에 저장 시작 ");
//
//        int res = policeService.insertFind();
//
//        if (res == 1) {
//            log.info(" DB 저장 성공");
//
//        } else {
//            log.info("DB 저장 실패");
//        }
//
//        log.info(getClass().getName()+ "실종 검색 DB에 저장 종료 ");
//
//        return res;
//    }
}