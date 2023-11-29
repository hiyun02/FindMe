package kopo.poly.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kopo.poly.dto.WarnDTO;
import kopo.poly.service.IApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:13000", "http://localhost:14000"}, allowedHeaders = {"POST, GET"}, allowCredentials = "true")
@Tag(name = "API 서비스", description = "경찰청 실종 정보를 위한 API")
@Slf4j
@RequestMapping(value = "/api")
@RequiredArgsConstructor
@RestController
public class ApiController {

    private final IApiService apiService;


    /**
     *  실종 경보 1건만 가져오기 (메인화면에 띄울 것)
     * @return 실종 경보 1건 DTO
     * @throws Exception
     */
    @Operation(summary = "실종 경보 1건만 가져오기 API", description = "실종 경보 1건만 가져오기"
            , responses = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "404", description = "Page Not Found"),
    })
    @GetMapping(value = "warnApi")
    public WarnDTO getWarnApi()throws Exception {
        log.info("실종 경보 API 가져오기 시작 ");

        WarnDTO pDTO = Optional.ofNullable(apiService.getWarnApi()).orElseGet(()-> WarnDTO.builder().build());

        log.info("실종 경보 API 가져오기 종료 ");

        return pDTO;
    }



    /**
     *  실종 경보 DB에 저장
     * @return 성공 여부 (성공 1, 실패0)
     * @throws Exception
     */
    @Operation(summary = "실종 경보 DB에 저장", description = "실종 경보 DB에 저장"
            , responses = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "404", description = "Page Not Found"),
    })
    @GetMapping(value = "insetWarnApi")
    public int insertWarnApi()throws Exception {
        log.info("실종 경보 API DB 저장 시작 ");

        int res = apiService.insertWarnApi();

        if (res == 1) {
            log.info(" DB 저장 성공");

        } else {
            log.info("DB 저장 실패");
        }

        log.info("실종 경보 API DB 저장 종료 ");


        return res;
    }



    /**
     *  실종 검색 DB에 저장
     * @return 성공 여부 (성공 1, 실패0)
     * @throws Exception
     */
    @Operation(summary = "실종 검색 DB에 저장 API", description = "실종 검색 DB에 저장"
            , responses = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "404", description = "Page Not Found"),
    })
    @GetMapping(value = "insertFind")
    public int insertFind()throws Exception {
        log.info(getClass().getName() + "실종 검색 DB에 저장 시작 ");

        int res = apiService.insertFind();

        if (res == 1) {
            log.info(" DB 저장 성공");

        } else {
            log.info("DB 저장 실패");
        }

        log.info(getClass().getName()+ "실종 검색 DB에 저장 종료 ");

        return res;
    }











}