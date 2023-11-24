package kopo.poly.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.cache.annotation.Cacheable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USER_INFO")
@DynamicUpdate
@DynamicInsert
@Builder
@Cacheable
@Entity
public class UserInfoEntity {

    /**
     * @NonNull과 nullable의 차이
     * @NonNull은 개발단계에서 해당 필드에 null 값을 허용하지 않음
     * nullable = false는 데이터베이스에  null값을 허용하지 않음
     *
     * updatable = false
     * 값이 한 번 생성된 후에 변경되지 않음
     *
     */

    @Id //회원아이디
    @Column(name = "USER_ID")
    private String userId;

    @NonNull // 비밀번호
    @Column(name = "PASSWORD", length = 500, nullable = false)
    private String password;

    @NonNull //이메일
    @Column(name = "EMAIL", nullable = false)
    private String email;

    @NonNull //이름
    @Column(name = "USER_NAME", length = 500, nullable = false)
    private String userName;

    @NonNull //가입일
    @Column(name = "USER_DATE", updatable = false)
    private String userDate;

    @NonNull // 주소
    @Column(name = "ADDR1", nullable = false)
    private String addr1;

    @NonNull // 상세주소
    @Column(name = "ADDR2", nullable = false)
    private String addr2;

    @NonNull // 알림 여부
    @Column(name = "USER_ALARM")
    private String userAlarm;

    @Column(name = "roles") //권한 데이터는 ,를 구분자로 여러 개(예 : 관리자, 일반사용자) 정의 가능함
    private String roles;
}
