package kopo.poly.repository;

import kopo.poly.repository.entity.AlarmMsgEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlarmMsgRepository extends JpaRepository<AlarmMsgEntity, Long> {

    List<AlarmMsgEntity> findAllByUserId(String userId);

    void deleteAllByUserId(String userId);
}
