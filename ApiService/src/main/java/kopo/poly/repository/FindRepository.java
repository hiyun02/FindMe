package kopo.poly.repository;

import kopo.poly.repository.entity.FindApiEntity;
import kopo.poly.repository.entity.WarnApiEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FindRepository extends JpaRepository<FindApiEntity, Long> {
}
