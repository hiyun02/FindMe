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
        List<String> imageUrlList, //실종자 이미지 url
        List<MultipartFile> multipartFiles, //

        String subject_id, // 해당 실종자의 이미지 정보를 조회할 subject_id
        String subject_name, //bucket의 파일명. 이미지가 여러 개인 경우 subject_name 뒤에 번호를 붙힘
        String face_url,

        // 게시글 등록
        String title,
        String readCnt, // 조회수
        String regId, // 등록자 아이디
        String regDt, // 등록일
        String chgId, // 수정자 아이디
        String chgDt // 수정일
) {

}