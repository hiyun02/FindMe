package kopo.poly.repository;

import kopo.poly.repository.entity.NoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<NoticeEntity, Long> {

    // 카테고리별 전체 조회 : 해외입양자 / 국내 실종자
    List<NoticeEntity> findAllByWritngTrgetDscdOrderByNoticeSeqDesc(NoticeEntity pEntity);

    // pk로 상세조회
    NoticeEntity findByNoticeSeq(Long noticeSeq);

    // pk로 수정
    @Modifying(clearAutomatically = true)
    @Query(value =
            "UPDATE NOTICE A SET A.READ_CNT = IFNULL(A.READ_CNT, 0) + 1 " +
                    "WHERE A.NOTICE_SEQ = :noticeSeq",
            nativeQuery=true)
    int updateReadCnt(@Param("noticeSeq") Long noticeSeq);

}