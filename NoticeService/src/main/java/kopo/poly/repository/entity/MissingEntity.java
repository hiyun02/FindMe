package kopo.poly.repository.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "WARN_API")
@DynamicInsert
@DynamicUpdate
@Builder
@Cacheable
@Entity
public class MissingEntity {

    @Id // API SEQ
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "missing_seq")
    private Long missingSeq;

    @NonNull // 발생일시
    @Column(name = "occrde",  nullable = false)
    private String occrde;

    @NonNull // 착의사항
    @Column(name = "alldressing_dscd",  nullable = false)
    private String alldressingDscd;

    @NonNull //현재나이
    @Column(name = "age_now",  nullable = false)
    private String ageNow;

    @NonNull // 당시나이
    @Column(name = "age",  nullable = false)
    private String age;

    @NonNull //대상구분
    @Column(name = "writngTrgetDscd",  nullable = false)
    private String writngTrgetDscd;

    @NonNull //성별구분
    @Column(name = "sexdstnDscd",  nullable = false)
    private String sexdstnDscd;

    @NonNull // 발생장소
    @Column(name = "occr_adres",  nullable = false)
    private String occrAdres;

    @NonNull //성명
    @Column(name = "nm",  nullable = false)
    private String nm;

    // 키
    @Column(name = "height",  nullable = false)
    private String height;
    //몸무게
    @Column(name = "bdwgh",  nullable = false)
    private String bdwgh;

    @Column(name = "frm_dscd",  nullable = false)//체격
    private String frmDscd;

    @Column(name = "faceshpe_dscd",  nullable = false) //얼굴형
    private String faceshpeDscd;

    @Column(name = "hairshpe_dscd",  nullable = false) //두발형태
    private String hairshpeDscd;

    @Column(name = "haircolr_dscd",  nullable = false) //두발색상
    private String haircolrDscd;

    @NonNull // 인물 사진 URL
    @Column(name = "face_url", nullable = false)
    private String faceUrl; // https://버킷/파일명

    @NonNull // 인물 Face 정보 관리할 Subject 식별자 -> 삭제에 사용
    @Column(name = "subject_id", nullable = false)
    private String subjectId;

    @NonNull // 인물 Face 정보 관리할 Subject 이름 -> 생성, 조회에 사용
    @Column(name = "subject_name", nullable = false)
    private String subjectName; // 파일명
}
