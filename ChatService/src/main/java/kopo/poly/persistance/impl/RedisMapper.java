package kopo.poly.persistance.impl;

import kopo.poly.dto.ChatDTO;
import kopo.poly.persistance.IRedisMapper;
import kopo.poly.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component("RedisMapper")
@RequiredArgsConstructor
public class RedisMapper implements IRedisMapper {
    private final RedisTemplate<String, Object> redisDB;

//    public RedisMapper(RedisTemplate<String, Object> redisDB) {
//        this.redisDB = redisDB;
//    }


    /* 채팅방 생성 */
    @Override
    public int createChatRoom(String roomName) throws Exception {
        log.info(this.getClass().getName() + "채팅방 생성 시작!");

        //채팅방 생성여부 확인
        int res = 0;

        redisDB.setKeySerializer(new StringRedisSerializer());
        redisDB.setValueSerializer(new Jackson2JsonRedisSerializer<>(ChatDTO.class));

        ChatDTO chatDTO = ChatDTO.builder()

                .roomName(roomName)
                .sendId("WZIP")
                .sendTime(DateUtil.getDateTime("yyyy-MM-dd"))
                .chatContent("채팅방이 생성되었습니다 :) <br> 실종 정보를 <br> 공유를 진행하세요!")
                .build();

        if (redisDB.hasKey(roomName)) {
            // 증북된 이름으로 채팅방 존재(생성 불가)
            res = 2;
            return res;
        }else {
            res = 1;
            redisDB.opsForList().leftPush(roomName, chatDTO);
            redisDB.expire(roomName, 3, TimeUnit.DAYS);
        }

        log.info(this.getClass().getName() + "채팅방 생성 종료!");
        return res;
    }

    @Override
    public List<String> getListChatRoom() throws Exception {
        log.info(this.getClass().getName()+"채팅방 리스트 가져오기 시작!");

        Set<String> keySet = redisDB.keys("*");

        List<String> keyList = new ArrayList<>(keySet);
        log.info("매퍼 채팅방 조회 결과 : " + keyList.size());

        log.info(this.getClass().getName()+"채팅방 리스트 가져오기 종료!");
        return keyList;
    }

    @Override
    public int InsertChatMessage(ChatDTO chatDTO) throws Exception {

        log.info(this.getClass().getName()+"채팅정보 저장 시작!");
        int res = 0;

        redisDB.setKeySerializer(new StringRedisSerializer());
        redisDB.setValueSerializer(new Jackson2JsonRedisSerializer<>(ChatDTO.class));

        if(!redisDB.hasKey(chatDTO.roomName())){
            redisDB.opsForList().rightPush(chatDTO.roomName(),chatDTO);
            redisDB.expire(chatDTO.roomName(), 3, TimeUnit.DAYS);
        }else{
            redisDB.opsForList().rightPush(chatDTO.roomName(),chatDTO);
            redisDB.expire(chatDTO.roomName(), 3, TimeUnit.DAYS);
        }

        log.info(this.getClass().getName()+"redis 채팅정보 저장 종료!");
        res = 1;
        return res;
    }

    @Override
    public List<ChatDTO> getChatInfo(String roomName) throws Exception {
        log.info(this.getClass().getName() + "채팅정보 가져오기 시작!");

        // Redis에서 가져온 결과 저장할 객체
        List<ChatDTO> chatDTOList = null;
        log.info("매퍼에 도착한 방 이름은 ? " + roomName);

        redisDB.setKeySerializer(new StringRedisSerializer());
        redisDB.setValueSerializer(new Jackson2JsonRedisSerializer<>(ChatDTO.class));

        if (redisDB.hasKey(roomName)) {
            // 저장된 전체 레코드 가져오기
            chatDTOList = (List) redisDB.opsForList().range(roomName, 0, -1);
        }

        if (chatDTOList == null) {
            chatDTOList = new ArrayList<>();
        }

        log.info(this.getClass().getName() + "채팅정보 가져오기 종료!"+chatDTOList.size());

        return chatDTOList;
    }
    @Override
    public int getRoomName(String roomName) throws Exception {
        int res =0;

        if(redisDB.hasKey(roomName)){
            res = 1;
            return res;
        }else {
            return res;
        }
    }

    @Override
    public int deleteChatRoom(String roomName) throws Exception {
        log.info(this.getClass().getName()+"채팅방 삭제 시작!");
        int res = 0;
        log.info("매퍼에 도착한 채팅방 삭제 이름은 ? " +  roomName);

        if (redisDB.hasKey(roomName)) {
            redisDB.delete(roomName);
            res = 1;
        }
        log.info(this.getClass().getName()+"채팅방 삭제 종료 결과는 : " + res);
        return res;
    }
}
