package kopo.poly.repository;

import kopo.poly.repository.entity.AlarmDeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlarmDeviceRepository extends JpaRepository<AlarmDeviceEntity, String> {
}
