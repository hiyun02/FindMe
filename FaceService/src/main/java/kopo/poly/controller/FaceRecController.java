package kopo.poly.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kopo.poly.dto.FaceDTO;
import kopo.poly.service.IFaceRecService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:13000", "http://localhost:14000"}, allowedHeaders = {"POST"}, allowCredentials = "true")
@Tag(name = "NUGU-FaceRecognize 서비스", description = "입력된 이미지를 관리 중인 Face 정보와 비교함")
@Slf4j
@RequestMapping(value = "/face/nugu/faceRec")
@RequiredArgsConstructor
@RestController
public class FaceRecController {

    private final IFaceRecService faceRecService;

    /**
     * 얼굴 유사도 분석하기
     * @return 분석 결과 리스트
     * @throws Exception
     */
    @Operation(summary = "Group 내 Face 유사도 분석", description = "지정 Group 속 Face 정보들과 입력된 이미지의 유사도를 추출하여 반환함"
            , responses = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "404", description = "Page Not Found"),
    })
    @PostMapping(value = "",
            consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public List<FaceDTO> getRecognizedList(@RequestPart FaceDTO faceDTO,
                                           @RequestPart MultipartFile multipartFile) throws Exception {

        log.info(this.getClass().getName() + ".getRecognizedList Start! ");

        log.info("비교를 진행할 대상 Group 아이디 : " + faceDTO.group_id());
        log.info("유사도 분석 기준 Face 이미지 : " + multipartFile);

        FaceDTO pDTO = FaceDTO.builder()
                .group_id(faceDTO.group_id())
                .image(multipartFile)
                .build();

        List<FaceDTO> rList = faceRecService.getRecognizedList(pDTO);
        log.info("유사도 비교 분석 결과 Face 개수 : " + rList.size());

        log.info(this.getClass().getName() + ".getRecognizedList End! ");

        return rList;
    }

}