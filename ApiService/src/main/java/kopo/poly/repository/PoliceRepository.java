package kopo.poly.repository;

import kopo.poly.repository.entity.PoliceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PoliceRepository extends JpaRepository<PoliceEntity, Long> {

    //최신 순 전체조회
    List<PoliceEntity> findAllByOrderByPoliceSeqDesc() throws Exception;

    //조회수 순 전체조회
    List<PoliceEntity> findAllByOrderByReadCntDesc() throws Exception;

}
