package kopo.poly.persistance;

import kopo.poly.dto.ChatDTO;

import java.util.List;

public interface IRedisMapper {
    /* 채팅방 생성*/
    int createChatRoom(String roomName) throws Exception;

    List<String> getListChatRoom() throws Exception;
    /* 채팅정보 입력 */
    int InsertChatMessage(ChatDTO pDTO) throws Exception;

    /* 채팅정보 가져오기 */
    List<ChatDTO> getChatInfo(String roomName) throws Exception;

    /* 채팅방 여부 확인 */
    int getRoomName(String roomName) throws Exception;

    /* 채팅방 삭제 */
    int deleteChatRoom(String roomName) throws Exception;
}
