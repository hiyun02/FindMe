package kopo.poly.service.impl;


import kopo.poly.dto.AlarmMsgDTO;
import kopo.poly.dto.DeviceDTO;
import kopo.poly.repository.AlarmDeviceRepository;
import kopo.poly.repository.AlarmMsgRepository;
import kopo.poly.repository.entity.DeviceEntity;
import kopo.poly.service.IAlarmService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AlarmService implements IAlarmService {

    private final AlarmDeviceRepository alarmDeviceRepository;

    private final AlarmMsgRepository alarmMsgRepository;

    @Override
    public int saveToken(DeviceDTO pDTO) throws Exception {

        log.info(getClass().getName() + "saveToken start");

        int res = 0;

        DeviceEntity pEntity = DeviceEntity.builder()
                .pushToken(pDTO.pushToken())
                .userId(pDTO.userId())
                .deviceDate(DateUtil.getDateTime("yyyy-MM-dd HH:mm:ss"))
                .build();

        alarmDeviceRepository.save(pEntity);

        res = 1;

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
    public int sendMessing(AlarmMsgDTO pDTO) throws Exception {

        log.info("사용자의 기기에 알리 메세지 보내기 시작 ");
        int res = 0;

        log.info("pDTO.deviceDTO" + pDTO.deviceDTO().toString());


        res = 1;
        log.info("사용자의 기기에 알리 메세지 보내기 종료 ");

        return res;
    }




}
