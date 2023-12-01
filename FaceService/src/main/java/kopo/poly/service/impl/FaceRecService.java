package kopo.poly.service.impl;

import kopo.poly.dto.FaceDTO;
import kopo.poly.service.IFaceRecService;
import kopo.poly.service.feign.IFaceRecAPIService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class FaceRecService implements IFaceRecService {

    private final IFaceRecAPIService recAPIService;

    @Override
    public List<FaceDTO> getRecognizedList(FaceDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".getRecognizeList Start!");

        List<FaceDTO> recognizedFaceList = Optional.ofNullable(recAPIService
                .getRecognizedList(pDTO.group_id(), 0.32, 0, pDTO.image()))
                .orElse(new ArrayList<>());

        log.info(this.getClass().getName() + ".getRecognizeList Start!");

        return recognizedFaceList;
    }
}
