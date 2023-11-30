package kopo.poly.service.impl;

import kopo.poly.dto.FaceAgingDTO;
import kopo.poly.dto.FaceDTO;
import kopo.poly.service.IFaceAgingService;
import kopo.poly.service.feign.IFaceAgingAPIService;
import kopo.poly.util.CmmUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class FaceAgingService implements IFaceAgingService {

    private final IFaceAgingAPIService faceAgingAPIService;

    private final String contentType = "application/json";
    private final String authorization = "Token r8_UVUkgDcvLjLpeUdO7i0cGKUC77V8BrK3FsK5D";
    private final String version = "9222a21c181b707209ef12b5e0d7e94c994b58f01c7b2fec075d2e892362f13c";

    @Override
    public FaceAgingDTO getAgedFaceImage(FaceAgingDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".getAgedFaceImage Start!");

        String image = CmmUtil.nvl(pDTO.image());
        String targetAge = CmmUtil.nvl(pDTO.target_age());
        log.info("Aging 대상 이미지 경로 : " + image);
        log.info("Aging 대상 나이 : " + targetAge);

        Map<String, String> input = new HashMap<>();
        input.put("image", image);
        input.put("target_age", targetAge);

        FaceAgingDTO agedDTO = faceAgingAPIService.getAgedFaceImage(
                contentType, authorization, version, input
        );

        log.info(this.getClass().getName() + ".getAgedFaceImage End!");

        return agedDTO;
    }
}
