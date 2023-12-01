package kopo.poly.repository;

import kopo.poly.repository.entity.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlarmDeviceRepository extends JpaRepository<DeviceEntity, String> {

    List<DeviceEntity> findAllByUserId(String userId);
}
