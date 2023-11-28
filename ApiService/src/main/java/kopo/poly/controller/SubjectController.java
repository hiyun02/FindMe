package kopo.poly.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kopo.poly.dto.FaceDTO;
import kopo.poly.service.IGroupService;
import kopo.poly.service.ISubjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:13000", "http://localhost:14000"}, allowedHeaders = {"POST, GET, DELETE"}, allowCredentials = "true")
@Tag(name = "NUGU-Subject 서비스", description = "얼굴 정보의 주인 구분을 위한 Subject API")
@Slf4j
@RequestMapping(value = "/facecan/subject")
@RequiredArgsConstructor
@RestController
public class SubjectController {

    private final ISubjectService subjectService;

    /**
     * 서브젝트 조회하기
     *
     * @return 서브젝트 리스트
     * @throws Exception
     */
    @Operation(summary = "Subject 전체 조회하기", description = "지정 Group에 존재하는 Subject 전체 조회함"
            , responses = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "404", description = "Page Not Found"),
    })
    @GetMapping(value = "getAll")
    public List<FaceDTO> getSubjectList(@ModelAttribute FaceDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".getSubjectList Start! ");

        log.info("조회할 Subject의 Group 아이디 : ", pDTO.group_id());
        List<FaceDTO> rList = subjectService.getSubjectList(pDTO);
        log.info("조회된 Subject 개수 : ", rList.size());

        log.info(this.getClass().getName() + ".getSubjectList End! ");

        return rList;
    }

    /**
     * 서브젝트 조회하기
     *
     * @return 서브젝트
     * @throws Exception
     */
    @Operation(summary = "Subject 조회하기", description = "서브젝트를 지정하여 조회함"
            , responses = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "404", description = "Page Not Found"),
    })
    @GetMapping(value = "getOne")
    public FaceDTO getSubject(FaceDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".getSubjectList Start! ");

        log.info("조회할 Subject의 Group 아이디 : ", pDTO.group_id());
        log.info("조회할 subject 이름 : ", pDTO.subject_name());
        FaceDTO faceDTO = subjectService.getSubject(pDTO);

        log.info(this.getClass().getName() + ".getSubjectList End! ");

        return faceDTO;
    }

    /**
     * 서브젝트 생성하기 (메인화면에 띄울 것)
     *
     * @return 서브젝트 생성 결과
     * @throws Exception
     */
    @Operation(summary = "Subject 생성하기", description = "이름을 지정하여 서브젝트를 생성함"
            , responses = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "404", description = "Page Not Found"),
    })
    @PostMapping(value = "create")
    public FaceDTO createSubject(@ModelAttribute FaceDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".createSubject Start! ");

        log.info("등록할 Subject의 Group 아이디 : ", pDTO.group_id());
        log.info("등록할 Subejct 명 : ", pDTO.face_name());
        FaceDTO faceDTO = subjectService.createSubject(pDTO);

        log.info(this.getClass().getName() + ".createSubject End! ");

        return faceDTO;
    }

    /**
     * 서브젝트 삭제하기
     *
     * @return 서브젝트 삭제 결과
     * @throws Exception
     */
    @Operation(summary = "Subject 삭제하기", description = "이름을 지정하여 그룹을 삭제함"
            , responses = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "404", description = "Page Not Found"),
    })
    @GetMapping(value = "delete")
    public int deleteSubject(@ModelAttribute FaceDTO faceDTO) throws Exception {

        log.info(this.getClass().getName() + ".deleteSubject Start! ");
        log.info(this.getClass().getName() + ".deleteSubject Start! ");

        int res = 0;

        subjectService.deleteSubject(faceDTO);

        res = 1; //성공 시 1 반환

        log.info(this.getClass().getName() + ".deleteSubject End! ");

        return res;
    }



}