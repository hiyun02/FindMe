package kopo.poly.service;

import kopo.poly.dto.ChatDTO;

public interface IChatService {

    /**
     * 채팅방 찾기
     * @param rDTO 채팅방 생성자 id , 채팅방 생성 대상자 id
     * @return 채팅방 생성이 있으면 roomKey, 채팅방이 존재하지 않으면 0을 반환함
     * @throws Exception
     */
    String findChatSeq(ChatDTO rDTO) throws Exception;

    /**
     * 채팅방 생성
     * @param rDTO 채팅방 생성자 id , 채팅방 생성 대상자 id
     * @return 채팅방 생성이 완료되면 1, 아니면 0
     * @throws Exception
     */
    int createChatRoom(ChatDTO rDTO)throws Exception;
}
