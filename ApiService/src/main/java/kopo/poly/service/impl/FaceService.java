package kopo.poly.service.impl;

import kopo.poly.dto.FaceDTO;
import kopo.poly.service.IFaceService;
import kopo.poly.service.ISubjectService;
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

    private final ISubjectAPIService subjectAPIService;

    @Override
    public List<FaceDTO> getSubjectList(FaceDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".getSubjectList Start!");

        List<FaceDTO> SubjectList = Optional.ofNullable(subjectAPIService
                .getSubjectList(pDTO.group_id())).orElse(new ArrayList<>());

        log.info(this.getClass().getName() + ".getSubjectList Start!");

        return SubjectList;
    }

    @Override
    public FaceDTO getSubject(FaceDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".getSubjectList Start!");

        FaceDTO subjectDTO = Optional.ofNullable(subjectAPIService
                .getSubject(pDTO.group_id(), pDTO.subject_name())).orElse(FaceDTO.builder().build());

        log.info(this.getClass().getName() + ".getSubjectList Start!");

        return subjectDTO;
    }

    @Override
    public FaceDTO createSubject(FaceDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".createSubject Start!");

        FaceDTO faceDTO = Optional.ofNullable(subjectAPIService
                .createSubject(pDTO.group_id(), pDTO.subject_name())).orElse(FaceDTO.builder().build());

        log.info(this.getClass().getName() + ".createSubject Start!");

        return faceDTO;
    }

    @Override
    public void deleteSubject(FaceDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".deleteSubject Start!");

        subjectAPIService.deleteSubject(pDTO.group_id(), pDTO.subject_id());

        log.info(this.getClass().getName() + ".deleteSubject End!");
    }

}
