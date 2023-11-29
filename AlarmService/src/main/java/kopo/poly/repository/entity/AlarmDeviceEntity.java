package kopo.poly.repository.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ALARM_DEVICE")
@DynamicInsert
@DynamicUpdate
@Builder
@Cacheable
@Entity
public class AlarmDeviceEntity {

    @Id //회원 아이디
    @Column(name = "USER_ID")
    private String userId;

    @NonNull //기기 알림 Token
    @Column(name = "PUSH_TOKEN", nullable = false)
    private String pushToken;

    @NonNull // Token 최근 사용 날짜
    @Column(name = "DEVICE_DATE", nullable = false)
    private String deviceDate;

}
