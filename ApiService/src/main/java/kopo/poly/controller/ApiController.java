package kopo.poly.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kopo.poly.dto.WarnDTO;
import kopo.poly.service.IApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
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











}
