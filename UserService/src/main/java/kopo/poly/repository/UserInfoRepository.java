package kopo.poly.repository;

import kopo.poly.repository.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfoEntity, String> {

    // 회원 존재 여부 체크
    // 쿼리 예 : SELECT * FROM USER_INFO WHERE USER_ID = 'hglee67'
    Optional<UserInfoEntity> findByUserId(String userId);

    //이메일 중복 체크
    Optional<UserInfoEntity> findByEmail(String email);

    // 계정 삭제
    int deleteByUserId(String userId);
}
