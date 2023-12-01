package kopo.poly.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kopo.poly.dto.FaceDTO;
import kopo.poly.service.IGroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:13000", "http://localhost:14000"}, allowedHeaders = {"POST, GET"}, allowCredentials = "true")
@Tag(name = "NUGU-Group 서비스", description = "사람 얼굴 정보 그룹 API")
@Slf4j
@RequestMapping(value = "/face/nugu/group")
@RequiredArgsConstructor
@RestController
public class GroupController {

    private final IGroupService groupService;

    /**
     * 그룹 조회하기 (메인화면에 띄울 것)
     *
     * @return 그룹 리스트
     * @throws Exception
     */
    @Operation(summary = "Group 조회하기", description = "존재하는 그룹을 전부 조회함"
            , responses = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "404", description = "Page Not Found"),
    })
    @GetMapping(value = "getAll")
    public List<FaceDTO> getGroupList() throws Exception {

        log.info(this.getClass().getName() + ".getGroupList Start! ");

        List<FaceDTO> rList = groupService.getGroupList();
        log.info("조회된 Group 개수 : " + rList.size());

        log.info(this.getClass().getName() + ".getGroupList End! ");
        return rList;
    }

    /**
     * 그룹 생성하기 (메인화면에 띄울 것)
     *
     * @return 그룹 생성 결과
     * @throws Exception
     */
    @Operation(summary = "Group 생성하기", description = "이름을 지정하여 그룹을 생성함"
            , responses = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "404", description = "Page Not Found"),
    })
    @PostMapping(value = "create")
    public FaceDTO createGroup(@RequestBody FaceDTO faceDTO) throws Exception {

        log.info(this.getClass().getName() + ".createGroup Start! ");

        log.info("group_name : " + faceDTO.group_name());
        FaceDTO pDTO = groupService.createGroup(faceDTO);

        log.info(this.getClass().getName() + ".createGroup End! ");

        return pDTO;
    }

    /**
     * 그룹 삭제하기
     *
     * @return 그룹 삭제 결과
     * @throws Exception
     */
    @Operation(summary = "Group 삭제하기", description = "이름을 지정하여 그룹을 삭제함"
            , responses = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "404", description = "Page Not Found"),
    })
    @PostMapping(value = "delete")
    public int deleteGroup(@RequestBody FaceDTO faceDTO) throws Exception {

        log.info(this.getClass().getName() + ".deleteGroup Start! ");

        int res = 0;
        log.info("group_id : " + faceDTO.group_id());
        groupService.deleteGroup(faceDTO);

        res = 1; //성공 시 1 반환

        log.info(this.getClass().getName() + ".deleteGroup End! ");

        return res;
    }


}