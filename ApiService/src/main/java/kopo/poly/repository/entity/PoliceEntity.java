package kopo.poly.repository.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "POLICE")
@DynamicInsert
@DynamicUpdate
@Builder
@Cacheable
@Entity
public class PoliceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "policeSeq")
    private Long policeSeq; // API SEQ

    @NonNull
    @Column(name = "occrde",  nullable = false)
    private String occrde; // 발생일시

    @NonNull
    @Column(name = "alldressing_dscd",  nullable = false)
    private String alldressingDscd; // 착의사항

    @NonNull
    @Column(name = "age_now",  nullable = false)
    private String ageNow; //현재나이

    @NonNull
    @Column(name = "age",  nullable = false)
    private String age; // 당시나이

    @NonNull
    @Column(name = "writngTrgetDscd",  nullable = false)
    private String writngTrgetDscd; //대상구분

    @NonNull
    @Column(name = "sexdstnDscd",  nullable = false)
    private String sexdstnDscd; //성별구분

    @NonNull
    @Column(name = "occr_adres",  nullable = false)
    private String occrAdres; // 발생장소

    @NonNull
    @Column(name = "nm",  nullable = false)
    private String nm; //성명

    @Column(name = "height",  nullable = false)
    private String height; // 키


    @Column(name = "bdwgh",  nullable = false)
    private String bdwgh; //몸무게


    @Column(name = "frm_dscd",  nullable = false)
    private String frmDscd; //체격


    @Column(name = "faceshpe_dscd",  nullable = false)
    private String faceshpeDscd; //얼굴형


    @Column(name = "hairshpe_dscd",  nullable = false)
    private String hairshpeDscd; //두발형태

    @Column(name = "haircolr_dscd",  nullable = false)
    private String haircolrDscd; //두발색상

    @NonNull
    @Column(name = "msspsn_idntfccd", nullable = false)
    private String msspsnIdntfccd; // 인물 사진

    @NonNull
    @Column(name = "read_cnt", nullable = false)
    private Long readCnt; // 인물 사진
}
