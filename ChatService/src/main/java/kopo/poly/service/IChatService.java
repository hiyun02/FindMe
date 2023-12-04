package kopo.poly.service;

import kopo.poly.dto.ChatDTO;

import java.util.List;

public interface IChatService {

    /**
     * 채팅방 찾기
     * @param rDTO 채팅방 생성자 id , 채팅방 생성 대상자 id
     * @return 채팅방 생성이 있으면 roomKey, 채팅방이 존재하지 않으면 0을 반환함
     * @throws Exception
     */
    String findChatRoomName(ChatDTO rDTO) throws Exception;

    /**
     * 채팅방 생성
     * @param rDTO 채팅방 생성자 id , 채팅방 생성 대상자 id
     * @return 채팅방 생성이 완료되면 roomName == roomKey 전달
     * @throws Exception
     */
    String chatCreateRDB(ChatDTO rDTO)throws Exception;


    // 채팅방이름으로 Redis에 채팅방 내용을 저장할 구조 생성
    int chatCreateNsql(String chatroomName)throws Exception;

    List<ChatDTO> getChatList(String userId)throws Exception;
}
