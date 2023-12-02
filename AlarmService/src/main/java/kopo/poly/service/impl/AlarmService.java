package kopo.poly.service.impl;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kopo.poly.dto.AlarmMsgDTO;
import kopo.poly.dto.DeviceDTO;
import kopo.poly.repository.AlarmDeviceRepository;
import kopo.poly.repository.AlarmMsgRepository;
import kopo.poly.repository.entity.AlarmMsgEntity;
import kopo.poly.repository.entity.DeviceEntity;
import kopo.poly.service.IAlarmService;
import kopo.poly.service.IPushService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AlarmService implements IAlarmService {


    @Value("${push.api.authKey}")
    private String authKey;

    private String type = "application/json";

    private String targetType = "device";

    private final AlarmDeviceRepository alarmDeviceRepository;

    private final AlarmMsgRepository alarmMsgRepository;

    private final IPushService pushService;

    @Override
    @Transactional
    public int saveToken(DeviceDTO pDTO) throws Exception {

        log.info(getClass().getName() + "saveToken start");
        int res = 0;

        Optional<DeviceEntity> nEntity = alarmDeviceRepository.findById(pDTO.pushToken());


        if (!nEntity.isPresent()) {


            DeviceEntity pEntity = DeviceEntity.builder()
                    .pushToken(pDTO.pushToken())
                    .userId(pDTO.userId())
                    .deviceDate(DateUtil.getDateTime("yyyy-MM-dd"))
                    .build();

            alarmDeviceRepository.save(pEntity);

            res = 1;
        } else {
            res = 2;
        }



        log.info(getClass().getName() + "saveToken End");
        return res;
    }

    @Override
    public List<String> getDevice(String userId) throws Exception {
     log.info(getClass().getName() + "사용자의 디바이스 토큰 가져오기 시작");


        List<DeviceEntity> alarmDeviceEntities = alarmDeviceRepository.findAllByUserId(userId);

        List<String> rList = new ArrayList<>();

        for (DeviceEntity deviceEntity :  alarmDeviceEntities) {

            rList.add(CmmUtil.nvl(deviceEntity.getPushToken()));

        }
        log.info(getClass().getName() + "사용자의 디바이스 토큰 가져오기 종료");

        return rList;
    }

    @Override
    @Transactional
    public int sendMessing(AlarmMsgDTO pDTO) throws Exception {

        log.info("사용자의 기기에 알리 메세지 보내기 시작 ");

        int res = 0;

        log.info("pDTO.deviceDTO" + pDTO.deviceDTO().toString());




        // 리스트가 5개 이하면 그대로 출력, 6개 이상이면 5개씩 나눠 출력
        if (pDTO.deviceDTO().size() <= 5) {
            pushService.pushAPI(authKey, type, targetType, pDTO.deviceDTO(), pDTO.title(), pDTO.content(), pDTO.url());

            log.info("Push 알림을 보내는 token 갯수 : " + pDTO.deviceDTO().size() );
        }else {
            List<List<String>> result = new ArrayList<>();
            int fromIndex = 0;
            while (fromIndex < pDTO.deviceDTO().size()) {
                result.add(new ArrayList<String>(
                        pDTO.deviceDTO().subList(fromIndex, Math.min(fromIndex + 5,  pDTO.deviceDTO().size()))));
                fromIndex += 5;
            }
            for (List<String> subList : result) {
                pushService.pushAPI(authKey, type, targetType, subList, pDTO.title(), pDTO.content(), pDTO.url());
                log.info("Push 알림을 보내는 token 갯수 : " + pDTO.deviceDTO().size() );
            }
        }


        // 알람 메세지 저장
        AlarmMsgEntity rEntity = AlarmMsgEntity.builder()
                .title(pDTO.title())
                .content(pDTO.content())
                .url(pDTO.url())
                .userId(pDTO.userId())
                .msgTime(DateUtil.getDateTime("yyyy-MM-dd HH:mm:ss"))
                .build();

        alarmMsgRepository.save(rEntity);

        // 기기 token 최근 사용날짜 최신화
        for (String pushToken : pDTO.deviceDTO()) {
            DeviceEntity nEntity = DeviceEntity.builder()
                    .pushToken(pushToken)
                    .deviceDate(DateUtil.getDateTime("yyyy-MM-dd"))
                    .userId(pDTO.userId())
                    .build();

            alarmDeviceRepository.save(nEntity);
        }

        res = 1;
        log.info("사용자의 기기에 알림 메세지 보내기 종료 ");

        return res;
    }

    @Override
    public List<AlarmMsgDTO> getAlarmList(String userId) throws Exception{

        log.info(getClass().getName() +"알람 내역 가쟈오기 시작");

        List<AlarmMsgEntity> rList = alarmMsgRepository.findAllByUserId(userId);

        List<AlarmMsgDTO> nList = new ObjectMapper()
                .convertValue(rList, new TypeReference<List<AlarmMsgDTO>>() {});


        log.info(getClass().getName() +"알람 내역 가쟈오기 종료");
        return nList;
    }

    @Override
    @Transactional
    public int deleteAllAlarm(String userId) throws Exception {
        log.info(getClass().getName()+"유저 알람 전체 지우기 시작");

        int res = 0;

        alarmMsgRepository.deleteAllByUserId(userId);
        res = 1;

        log.info(getClass().getName()+"유저 알람 전체 지우기 종료");
        return res;
    }

    @Override
    @Transactional
    public int deleteOneAlarm(String seq) throws Exception {
        log.info(getClass().getName()+"유저 알람 하나만 지우기 시작");

        int res = 0;

        alarmMsgRepository.deleteById(Long.parseLong(seq));
        res = 1;

        log.info(getClass().getName()+"유저 알람 하나만 지우기 종료");
        return res;

    }


}
