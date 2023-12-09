package kopo.poly.repository;


import kopo.poly.repository.entity.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<ChatEntity, Long> {

    // 채팅방 생성 조회
    ChatEntity findBySendIdAndReceiveIdOrReceiveIdAndSendId(String sendId, String receiveId,String sendId2,String receiveId2 );

    // 채팅방 생성 후 조회 (파라미터값 정해져있음)
    ChatEntity findBySendIdAndReceiveId(String sendId, String receiveId);


    List<ChatEntity> findBySendId(String userId);

    List<ChatEntity> findByReceiveId(String userId);

}
