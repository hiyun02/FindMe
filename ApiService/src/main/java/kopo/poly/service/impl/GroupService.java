package kopo.poly.service.impl;

import kopo.poly.dto.FaceDTO;
import kopo.poly.service.IGroupService;
import kopo.poly.service.feign.IGroupAPIService;
import kopo.poly.service.feign.IPoliceApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class GroupService implements IGroupService {

    private final IGroupAPIService groupApiService;

    @Override
    public List<FaceDTO> getGroupList() throws Exception {

        log.info(this.getClass().getName() + ".getGroupList Start!");

        List<FaceDTO> groupList = Optional.ofNullable(groupApiService
                .getGroupList()).orElse(new ArrayList<>());

        log.info(this.getClass().getName() + ".getGroupList Start!");

        return groupList;
    }

    @Override
    public FaceDTO createGroup(FaceDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".createGroup Start!");

        FaceDTO faceDTO = Optional.ofNullable(groupApiService
                .createGroup(pDTO.group_name())).orElse(FaceDTO.builder().build());

        log.info(this.getClass().getName() + ".createGroup Start!");

        return faceDTO;
    }

    @Override
    public void deleteGroup(FaceDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".deleteGroup Start!");

        groupApiService.deleteGroup(pDTO.group_id());

        log.info(this.getClass().getName() + ".deleteGroup End!");
    }
}
