package kopo.poly.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kopo.poly.dto.FaceDTO;
import kopo.poly.service.IFaceRecService;
import kopo.poly.service.IFaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:13000", "http://localhost:14000"}, allowedHeaders = {"POST, GET, DELETE"}, allowCredentials = "true")
@Tag(name = "NUGU-FaceRecognize 서비스", description = "입력된 이미지를 관리 중인 Face 정보와 비교함")
@Slf4j
@RequestMapping(value = "/facecan/faceRec")
@RequiredArgsConstructor
@RestController
public class FaceRecController {

    private final IFaceRecService faceRecService;

    /**
     * 서브젝트 조회하기
     * @return 서브젝트 리스트
     * @throws Exception
     */
    @Operation(summary = "Face 전체 조회하기", description = "지정 Group 속 Subject로 관리되는 Face 정보를 조회함"
            , responses = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "404", description = "Page Not Found"),
    })
    @GetMapping(value = "list")
    public List<FaceDTO> getRecognizedList(@ModelAttribute FaceDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".getRecognizedList Start! ");

        log.info("비교를 진행할 대상 Group 아이디 : ", pDTO.group_id());
        List<FaceDTO> rList = faceRecService.getRecognizedList(pDTO);
        log.info("유사도 비교 분석 결과 Face 개수 : ", rList.size());

        log.info(this.getClass().getName() + ".getRecognizedList End! ");

        return rList;
    }

}