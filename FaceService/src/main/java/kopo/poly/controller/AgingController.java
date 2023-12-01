package kopo.poly.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kopo.poly.dto.AgingDTO;
import kopo.poly.service.IAgingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:13000", "http://localhost:14000"}, allowedHeaders = {"POST"}, allowCredentials = "true")
@Tag(name = "SAM-FaceAging 서비스", description = "입력된 이미지와 추정 나이를 기반으로 예측 이미지 생성")
@Slf4j
@RequestMapping(value = "/face/sam/Aging")
@RequiredArgsConstructor
@RestController
public class AgingController {

    private final IAgingService faceAgingService;

    /**
     * 서브젝트 조회하기
     * @return 서브젝트 리스트
     * @throws Exception
     */
    @Operation(summary = "Group 내 Face 유사도 분석", description = "지정 Group 속 Face 정보들과 입력된 이미지의 유사도를 추출하여 반환함"
            , responses = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "404", description = "Page Not Found"),
    })
    @PostMapping(value = "predict")
    public AgingDTO getAgedFaceInfo(@RequestBody AgingDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".getAgedFaceImage Start! ");

        log.info("aging data : "+pDTO.toString());
        AgingDTO rDTO = faceAgingService.getAgedFaceImage(pDTO);

        log.info(this.getClass().getName() + ".getAgedFaceImage End! ");

        return rDTO;
    }

}