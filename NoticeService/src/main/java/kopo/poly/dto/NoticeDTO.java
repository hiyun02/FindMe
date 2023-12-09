package kopo.poly.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public record NoticeDTO(

        Long noticeSeq, // Pk Seq

        // 텍스트 입력 정보
        String writngTrgetDscd, //대상구분
        String detailTrgetDscd, //상세구분
        String occrde, // 발생일시
        String nm, //실종자명
        String sexdstnDscd, //성별구분
        String faceshpeDscd,//얼굴형
        String birthDt,
        String age, // 실종 나이
        String height,// 키
        String frmDscd,//체격
        String hairshpeDscd,//두발형태
        String haircolrDscd,//두발색상
        String alldressingDscd, // 착의사항
        String occrAdres, // 발생장소

        // 얼굴 이미지를 위한 변수 추가 선언
        List<MultipartFile> multipartFiles, // 업로드된 이미지를 받아올 리스트

        String subject_id, // 해당 실종자의 이미지 정보를 조회할 subject_id
        String subject_name, // bucket의 파일명. 이미지가 여러 개인 경우 subject_name 뒤에 번호를 붙힘

        // 게시글 등록
        String faceImgUrl1, //
        String faceImgUrl2, //
        String title,
        Long readCnt, // 조회수
        String regId, // 등록자 아이디
        String regDt, // 등록일
        String chgId, // 수정자 아이디
        String chgDt // 수정일
) {

}