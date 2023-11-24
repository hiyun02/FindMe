package kopo.poly.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;


@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public record WarnDTO(
        String warnSeq, // Pk Seq
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
        String msspsnIdntfccd// 인물 사진



) {
}
