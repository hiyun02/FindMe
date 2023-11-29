package kopo.poly.repository;

import kopo.poly.repository.entity.AlarmMsgEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlarmMsgRepository extends JpaRepository<AlarmMsgEntity, Long> {
}
