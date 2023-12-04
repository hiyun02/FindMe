package kopo.poly.service.impl;

import kopo.poly.dto.ChatDTO;
import kopo.poly.repository.ChatRepository;
import kopo.poly.repository.entity.ChatEntity;
import kopo.poly.service.IChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService implements IChatService {



    private final ChatRepository chatRepository;


    // 채팅방이 존재하는지 확인 아무것도 없으면 0 , 있으면 채팅방키 를 반환함
    @Override
    public String findChatSeq(ChatDTO rDTO) throws Exception {

       ChatEntity chatEntity = chatRepository.findBySendIdAndReceiveIdOrReceiveIdAndSendId
               (rDTO.sendId(),rDTO.receiveId(),rDTO.sendId(),rDTO.receiveId());

        if (chatEntity==null) {

            log.info("정보없음");

            return "0";

        } else {
            return chatEntity.getRoomKey().toString();
        }


    }

    @Override
    public int createChatRoom(ChatDTO rDTO) throws Exception {
        log.info(getClass().getName()+ "채팅방 생성 시작 ");

        int res = 0;

        ChatEntity chatEntity = ChatEntity.builder()
                .sendId(rDTO.sendId())
                .receiveId(rDTO.receiveId())
                .build();

        chatRepository.save(chatEntity);

        res = 1;

        log.info(getClass().getName()+ "채팅방 생성 종료 ");

        return res;
    }


}
