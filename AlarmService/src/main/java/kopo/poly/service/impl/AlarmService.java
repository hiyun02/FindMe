package kopo.poly.service.impl;

import kopo.poly.dto.AlarmMsgDTO;
import kopo.poly.service.IAlarmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AlarmService implements IAlarmService {
    @Override
    public int sendMessing(AlarmMsgDTO pDTO) throws Exception {
        return 0;
    }

    @Override
    public int saveToken(AlarmMsgDTO pDTO) throws Exception {
        return 0;
    }
}
