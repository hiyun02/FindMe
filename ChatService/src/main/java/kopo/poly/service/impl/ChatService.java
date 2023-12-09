package kopo.poly.service.impl;

import kopo.poly.dto.ChatDTO;
import kopo.poly.persistance.IRedisMapper;
import kopo.poly.repository.ChatRepository;
import kopo.poly.repository.entity.ChatEntity;
import kopo.poly.service.IChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService implements IChatService {



    private final ChatRepository chatRepository;

    private final IRedisMapper redisMapper;



    // 채팅방이 존재하는지 확인 아무것도 없으면 0 , 있으면 채팅방키 를 반환함
    @Override
    public String findChatRoomName(ChatDTO rDTO) throws Exception {

       ChatEntity chatEntity = chatRepository.findBySendIdAndReceiveIdOrReceiveIdAndSendId
               (rDTO.sendId(),rDTO.receiveId(),rDTO.sendId(),rDTO.receiveId());

        if (chatEntity==null) {

            log.info("정보없음");

            return "0";

        } else {
            return chatEntity.getRoomName().toString();
        }


    }

    @Override
    public String chatCreateRDB(ChatDTO rDTO) throws Exception {
        log.info(getClass().getName()+ "RDBS 채팅방 생성 시작 ");

        int res = 0;

        ChatEntity chatEntity = ChatEntity.builder()
                .sendId(rDTO.sendId())
                .receiveId(rDTO.receiveId())
                .build();

        chatRepository.save(chatEntity);


        ChatEntity nEntity = chatRepository.findBySendIdAndReceiveId(rDTO.sendId(), rDTO.receiveId());


        String roomName = nEntity.getRoomName().toString();

        log.info(getClass().getName()+ "RDB 채팅방 생성 종료 ");

        return roomName;
    }

    @Override
    public int chatCreateNsql(String chatroomName)throws Exception {

        log.info(getClass().getName()+ "redis 채팅방 생성 시작 ");

        int res = redisMapper.createChatRoom(chatroomName);

        log.info(getClass().getName()+ "redis 채팅방 생성 종료 ");
        return res;
    }

    @Override
    public List<ChatDTO> getChatList(String userId) throws Exception {

        log.info(getClass().getName() + "getChatList start");

        List<ChatEntity> rList = chatRepository.findBySendId(userId);

        List<ChatDTO> resultList = new ArrayList<>();
        for (ChatEntity rEntity : rList) {
            ChatDTO rDTO = ChatDTO.builder()
                    .receiveId(rEntity.getReceiveId())
                    .roomName(rEntity.getRoomName().toString())
                    .build();

            resultList.add(rDTO);
        }
        List<ChatEntity> nList = chatRepository.findByReceiveId(userId);
        for (ChatEntity rEntity : nList) {
            ChatDTO rDTO = ChatDTO.builder()
                    .receiveId(rEntity.getSendId())
                    .roomName(rEntity.getRoomName().toString())
                    .build();
            resultList.add(rDTO);
        }

        for (ChatDTO rDTO : resultList) {
            log.info("채팅방 키 :" + rDTO.roomName());
            log.info("채팅방 받는사람 :" + rDTO.receiveId());
        }
        log.info(getClass().getName() + "getChatList end");
        return resultList;
    }


}
