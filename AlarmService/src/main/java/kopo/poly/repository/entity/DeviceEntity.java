package kopo.poly.repository.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "DEVICE")
@DynamicInsert
@DynamicUpdate
@Builder
@Cacheable
@Entity
public class DeviceEntity {

    @Id //기기 알림 Token
    @Column(name = "PUSH_TOKEN")
    private String pushToken;

    @NonNull //회원 아이디
    @Column(name = "USER_ID", nullable = false)
    private String userId;

    @NonNull // Token 최근 사용 날짜
    @Column(name = "DEVICE_DATE", nullable = false)
    private String deviceDate;

}
