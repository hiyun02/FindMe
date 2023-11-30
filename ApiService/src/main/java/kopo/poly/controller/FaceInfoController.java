package kopo.poly.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kopo.poly.dto.FaceDTO;
import kopo.poly.service.IFaceInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:13000", "http://localhost:14000"}, allowedHeaders = {"POST"}, allowCredentials = "true")
@Tag(name = "NUGU-Face 서비스", description = "얼굴 이미지 Face API")
@Slf4j
@RequestMapping(value = "/facecan/faceInfo")
@RequiredArgsConstructor
@RestController
public class FaceInfoController {

    private final IFaceInfoService faceInfoService;

    /**
     * 얼굴정보 조회하기
     * @return 얼굴정보 리스트
     * @throws Exception
     */
    @Operation(summary = "Face 전체 조회하기", description = "지정 Group 속 Subject로 관리되는 Face 정보를 조회함"
            , responses = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "404", description = "Page Not Found"),
    })
    @PostMapping(value = "getFaceList")
    public List<FaceDTO> getFaceList(@RequestBody FaceDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".getFaceList Start! ");

        log.info("조회할 Face 정보의 소속 Group 아이디 : " + pDTO.group_id());
        log.info("조회할 Face 정보의 소속 Subject 아이디 : " + pDTO.subject_id());
        List<FaceDTO> rList = faceInfoService.getFaceList(pDTO);
        log.info("조회된 Face 개수 : " + rList.size());

        log.info(this.getClass().getName() + ".getFaceList End! ");

        return rList;
    }

    /**
     * 얼굴정보 등록하기
     * @return 얼굴정보 등록 결과
     * @throws Exception
     */
    @Operation(summary = "Face 생성하기", description = "Group과 Subject를 지정하여 Face 정보를 등록함"
            , responses = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "404", description = "Page Not Found"),
    })
    @PostMapping(value = "create")
    public FaceDTO createFace(@RequestBody FaceDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".createFace Start! ");

        log.info("등록할 Face의 Group 아이디 : " + pDTO.group_id());
        log.info("등록할 Face의 Subejct 아이디 : " + pDTO.subject_id());
        log.info("등록할 Face 이름 : " + pDTO.face_name());
        FaceDTO faceDTO = faceInfoService.createFace(pDTO);

        log.info(this.getClass().getName() + ".createFace End! ");

        return faceDTO;
    }

    /**
     * 그룹 삭제하기
     * @return 그룹 삭제 결과
     * @throws Exception
     */
    @Operation(summary = "Face 삭제하기", description = "Group과 Subject, Face를 지정하여 등록된 Face 정보를 삭제함"
            , responses = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "404", description = "Page Not Found"),
    })
    @PostMapping(value = "delete")
    public int deleteFace(@RequestBody FaceDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".deleteFace Start! ");

        int res = 0;
        log.info("삭제할 Face의 소속 Group 아이디 : " + pDTO.group_id());
        log.info("삭제할 Face의 소속 Subject 아이디 : " + pDTO.subject_id());
        log.info("삭제할 Face 아이디 : " + pDTO.face_id());
        faceInfoService.deleteFace(pDTO);
        res = 1; //성공 시 1 반환

        log.info(this.getClass().getName() + ".deleteFace End! ");

        return res;
    }

}