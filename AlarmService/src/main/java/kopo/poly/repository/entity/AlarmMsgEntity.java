package kopo.poly.repository.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ALARM_MSG")
@DynamicInsert
@DynamicUpdate
@Builder
@Cacheable
@Entity
public class AlarmMsgEntity {

    @Id // Alarm Pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MSG_seq")
    private Long msgSeq;


    @NonNull  //회원 아이디
    @Column(name = "USER_ID", nullable = false)
    private String userId;

    @NonNull // 이동 URL
    @Column(name = "URL", nullable = false)
    private String url;

    @NonNull // 제목
    @Column(name = "TITLE", nullable = false)
    private String title;

    @NonNull // 내용
    @Column(name = "CONTENT", nullable = false)
    private String content;

    @NonNull // Token 최근 사용 날짜
    @Column(name = "MSG_TIME", nullable = false)
    private String msgTime;


}
