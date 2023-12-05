package kopo.poly.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public record MissingDTO(

        String missingSeq, // Pk Seq
        String occrde, // 발생일시
        String alldressingDscd, // 착의사항
        String ageNow, //현재나이
        String age, // 당시나이
        String writngTrgetDscd, //대상구분
        String sexdstnDscd, //성별구분
        String occrAdres, // 발생장소
        String nm, //성명
        String height,// 키
        String bdwgh,//몸무게
        String frmDscd,//체격
        String faceshpeDscd,//얼굴형
        String hairshpeDscd,//두발형태
        String haircolrDscd,//두발색상
        String msspsnIdntfccd,// 인물 사진
        // 얼굴 이미지를 위한 변수 추가 선언
        List<String> imageUrlList, //bucket url
        List<MultipartFile> multipartFiles, //
        String subject_id,
        String subject_name, //bucket의 파일명

        String readCnt, // 조회수
        String regId, // 등록자 아이디
        String regDt, // 등록일
        String chgId, // 수정자 아이디
        String chgDt // 수정일
) {
}
