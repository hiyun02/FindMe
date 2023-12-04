package kopo.poly.repository.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CHAT")
@DynamicInsert
@DynamicUpdate
@Builder
@Cacheable
@Entity
public class ChatEntity {

    @Id // roomKey (PK) == 채팅방 Pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROOM_NAME")
    private Long roomName;

    @NonNull // 보내는 Id
    @Column(name = "SEND_ID",  nullable = false)
    private String sendId;

    @NonNull // 받는 Id
    @Column(name = "RECEIVE_ID",  nullable = false)
    private String receiveId;


}
