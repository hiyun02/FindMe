package kopo.poly.controller;

import jakarta.servlet.http.HttpServletRequest;
import kopo.poly.dto.ChatDTO;
import kopo.poly.dto.TokenDTO;
import kopo.poly.service.IChatService;
import kopo.poly.service.fegin.ITokenAPIService;
import kopo.poly.util.CmmUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {

    private final IChatService chatService;

    private final ITokenAPIService tokenAPIService;

    private final String HEADER_PREFIX = "Bearer ";


    /**
     *
     *
     * @param request 채팅방 대상 id == receiveId
     * @param token 사용자 토큰 즉 사용자 iD
     * @return 채팅방 사용자가 존재하면 roomkey 값을 반환하고 ,
     *         채팅방 생성 시 성공하면 1, 실패하면 0을 반환
     * @throws Exception
     */
    @PostMapping(value = "chatAddProc")
    public String chaAddProc (HttpServletRequest request
            ,  @CookieValue(value = "${jwt.token.access.name}") String token)throws Exception {

        log.info(getClass().getName()+"채팅방 생성 시작");

        // 결과값 전역 변수 선언

        String roomKey = "";
        try {
            TokenDTO tDTO = tokenAPIService.getTokenInfo(HEADER_PREFIX +token);
            log.info("TokenDTO : " + tDTO);

            String userId = CmmUtil.nvl(tDTO.userId());//JWT Access 토큰으로부터 회원아이디 가져오기
            String receiveId = CmmUtil.nvl(request.getParameter("receiveId"));

            log.info("채팅 사용자 id 1 : " + userId);
            log.info("채팅방 사용자 id2 : " + receiveId);

            ChatDTO rDTO = ChatDTO.builder()
                    .sendId(userId)
                    .receiveId(receiveId)
                    .build();

            // 채팅방이 존재하는지 확인 아무것도 없으면 0 , 있으면 채팅방키를 반환함
           roomKey = chatService.findChatSeq(rDTO);


            if (roomKey.equals("0")) {

                int res = chatService.createChatRoom(rDTO);


                if (res == 1) {
                    log.info("채팅방생성 완료");
                    roomKey = "1";
                } else {
                    log.info("채팅방 생성 오류 확인 바람");
                    roomKey = "0";

                }



                log.info("채팅방 생성 성공 여부 ");
            } else {
                log.info("존재하는 채팅방");
                log.info("채팅방 key :" + roomKey);
            }



        }catch (Exception e) {
            log.info(e.toString());
            e.printStackTrace();
        }




        log.info(getClass().getName()+"채팅방 생성 종료");

        return roomKey;

    }
}
