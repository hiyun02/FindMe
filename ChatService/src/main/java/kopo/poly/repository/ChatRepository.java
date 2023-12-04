package kopo.poly.repository;


import kopo.poly.repository.entity.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<ChatEntity, Long> {

    // 채팅방 생성 조회
    ChatEntity findBySendIdAndReceiveIdOrReceiveIdAndSendId(String sendId, String receiveId,String sendId2,String receiveId2 );
}
