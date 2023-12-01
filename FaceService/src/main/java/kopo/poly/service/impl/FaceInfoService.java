package kopo.poly.service.impl;

import kopo.poly.dto.FaceDTO;
import kopo.poly.service.IFaceInfoService;
import kopo.poly.service.feign.IFaceInfoAPIService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class FaceInfoService implements IFaceInfoService {

    private final IFaceInfoAPIService faceInfoAPIService;

    @Override
    public List<FaceDTO> getFaceList(FaceDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".getFaceList Start!");

        List<FaceDTO> faceDTOList = Optional.ofNullable(faceInfoAPIService
                .getFaceList(pDTO.group_id(), pDTO.subject_id())).orElse(new ArrayList<>());

        log.info(this.getClass().getName() + ".getFaceList Start!");

        return faceDTOList;
    }

    @Override
    public FaceDTO createFace(FaceDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".createSubject Start!");

        FaceDTO faceDTO = Optional.ofNullable(faceInfoAPIService
                .createFace(pDTO.group_id(), pDTO.subject_id(), pDTO.face_name(), pDTO.image()))
                .orElse(FaceDTO.builder().build());

        log.info(this.getClass().getName() + ".createSubject Start!");

        return faceDTO;
    }

    @Override
    public void deleteFace(FaceDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".deleteSubject Start!");

        faceInfoAPIService.deleteFace(pDTO.group_id(), pDTO.subject_id(), pDTO.face_id());

        log.info(this.getClass().getName() + ".deleteSubject End!");
    }

}
