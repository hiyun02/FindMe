package kopo.poly.service.impl;

import kopo.poly.dto.FaceDTO;
import kopo.poly.service.IFaceService;
import kopo.poly.service.ISubjectService;
import kopo.poly.service.feign.IFaceAPIService;
import kopo.poly.service.feign.ISubjectAPIService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class FaceService implements IFaceService {

    private final IFaceAPIService faceAPIService;

    @Override
    public List<FaceDTO> getFaceList(FaceDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".getFaceList Start!");

        List<FaceDTO> faceDTOList = Optional.ofNullable(faceAPIService
                .getFaceList(pDTO.group_id(), pDTO.subject_id())).orElse(new ArrayList<>());

        log.info(this.getClass().getName() + ".getFaceList Start!");

        return faceDTOList;
    }

    @Override
    public FaceDTO createFace(FaceDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".createSubject Start!");

        FaceDTO faceDTO = Optional.ofNullable(faceAPIService
                .createFace(pDTO.group_id(), pDTO.subject_id(), pDTO.face_name(), "image"))
                .orElse(FaceDTO.builder().build());

        log.info(this.getClass().getName() + ".createSubject Start!");

        return faceDTO;
    }

    @Override
    public void deleteFace(FaceDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".deleteSubject Start!");

        faceAPIService.deleteFace(pDTO.group_id(), pDTO.subject_id(), pDTO.face_id());

        log.info(this.getClass().getName() + ".deleteSubject End!");
    }

}
