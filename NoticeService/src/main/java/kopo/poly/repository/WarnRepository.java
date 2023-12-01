package kopo.poly.repository;

import kopo.poly.repository.entity.WarnApiEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarnRepository extends JpaRepository<WarnApiEntity, Long> {
}
