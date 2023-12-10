package kopo.poly.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import kopo.poly.auth.AuthInfo;
import kopo.poly.dto.TokenDTO;
import kopo.poly.dto.UserInfoDTO;
import kopo.poly.repository.UserInfoRepository;
import kopo.poly.repository.entity.UserInfoEntity;
import kopo.poly.service.IUserInfoService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.DateUtil;
import kopo.poly.util.EncryptUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserInfoService implements IUserInfoService {

    private final UserInfoRepository userInfoRepository;




    /**
     * 로그인 처리를 하기 위해 실행하는 함수로 인증 기능을 사용하기 위해서 필수적으로 생성해야됨
     * Controller 에서 호출되지 않으며 Security 가 바로 호출
     * Security가 직접 판단하며 ID와 PW가 일치하지 않으면 Exception을 발생시킴
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        log.info(getClass().getName() + "UserDetails start");

        UserInfoEntity rEntity = userInfoRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException(userId + "Not Found User"));

        UserInfoDTO rDTO = new ObjectMapper().convertValue(rEntity, UserInfoDTO.class);

        log.info("rDTO : " + rDTO);

        return new AuthInfo(rDTO);
    }

    @Override
    @Transactional
    public int insertUserInfo(UserInfoDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + ".insertUserInfo Start!");

        int res = 0; // 회원가입 성공 : 1, 아이디 중복으로인한 가입 취소 : 2, 기타 에러 발생 : 0

        String userId = CmmUtil.nvl(pDTO.userId()); // 회원아이디
        String password = CmmUtil.nvl(pDTO.password()); // 비밀번호
        String email = CmmUtil.nvl(pDTO.email()); // 이메일
        String userName = CmmUtil.nvl(pDTO.userName()); // 이름
        String addr1 = CmmUtil.nvl(pDTO.addr1()); // 주소
        String addr2 = CmmUtil.nvl(pDTO.addr2()); // 상세주소
//        String userAlarm = CmmUtil.nvl(pDTO.userAlarm()); //알림 여부
        String roles = CmmUtil.nvl(pDTO.roles()); // 권한

        log.info("회원아이디 : " + userId);
        log.info("비밀번호 : " + password);
        log.info("이메일 : " + email);
        log.info("이름 : " + userName);
        log.info("주소 : " + addr1);
        log.info("상세주소 : " + addr2);
//        log.info("알림 여부 : " + userAlarm);
        log.info("roles : " + roles);

        // 회원 가입 중복 방지를 위해 DB에서 데이터 조회
        Optional<UserInfoEntity> rEntity = userInfoRepository.findByUserId(userId);

        // 값이 존재한다면... (이미 회원가입된 아이디)
        if (rEntity.isPresent()) {
            res = 2;

        } else {

            // 회원가입을 위한 Entity 생성
            UserInfoEntity pEntity = UserInfoEntity.builder()
                    .userId(userId)
                    .password(password)
                    .email(email)
                    .userName(userName)
                    .userDate(DateUtil.getDateTime("yyyy-MM-dd hh:mm"))
                    .addr1(addr1)
                    .addr2(addr2)
//                    .userAlarm(userAlarm)
                    .roles(roles)
                    .build();

            // 회원정보 DB에 저장
            userInfoRepository.save(pEntity);

            rEntity = userInfoRepository.findByUserId(userId);

            if (rEntity.isPresent()) { // 값이 존재한다면... (회원가입 성공)
                res = 1;

            }
        }

        log.info(this.getClass().getName() + ".insertUserInfo End!");

        return res;
    }


    @Override
    @Transactional
    public UserInfoDTO getUserInfo(UserInfoDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".getUserInfo Start!");

        String user_id = CmmUtil.nvl(pDTO.userId());

        log.info("user_id : " + user_id);

        UserInfoDTO rDTO = null;

        Optional<UserInfoEntity> rEntity = userInfoRepository.findByUserId(user_id);

        if (rEntity.isPresent()) {

            // Entity -> DTO 로 변경
            // DB 저장된 암호화된 Email 값을 복호화해서 DTO에 저장하기 위해 ObjectMapper 사용 안함
            rDTO = UserInfoDTO.builder()
                    .userId(CmmUtil.nvl(rEntity.get().getUserId()))
                    .userName(CmmUtil.nvl(rEntity.get().getUserName()))

                    // 이메일 주소를 복호화해서 Record 저장하기
                    .email(EncryptUtil.decAES128CBC(CmmUtil.nvl(rEntity.get().getEmail())))
                    .addr1(CmmUtil.nvl(rEntity.get().getAddr1()))
                    .addr2(CmmUtil.nvl(rEntity.get().getAddr2()))
                    .roles(rEntity.get().getRoles())
                    .build();

        }

        log.info(this.getClass().getName() + ".getUserInfo End!");

        return rDTO;
    }


    /**
     * 유저 아이디 중복 체크
     *
     * @param userId 유저 아이디
     * @return 결과값 (Id가 존재하면 1 아니면 0)
     * @throws Exception
     */
    @Override
    @Transactional
    public int idCheck(String userId) throws Exception {
        log.info(getClass().getName() + "idCheck Start ");

        int res = 0;

        Optional<UserInfoEntity> rEntity = userInfoRepository.findById(userId);


        if (rEntity.isPresent()) res = 1;


        log.info(getClass().getName() + "idCheck end ");

        return res;
    }

    @Override
    @Transactional
    public String idFind(String email) throws Exception {
        log.info(getClass().getName() + "아이디 찾기 시작");
        String userId = "";
        Optional<UserInfoEntity> rEntity = userInfoRepository.findByEmail(email);

        if (rEntity.isPresent()) {
            userId = rEntity.get().getUserId();
        }

        log.info("email 로 조회한 userId : " + userId);

        log.info(getClass().getName() + "아이디 찾기 종료");

        return userId;
    }

    @Override
    @Transactional
    public int userUpdate(UserInfoDTO pDTO) throws Exception {
        log.info(getClass().getName() + "userUpdate  start");

        int res = 0;

        log.info("담겨오는 DTO 내용 : " + pDTO.toString());

        // 가입일을 가져오기 위해서 DB 조회
        Optional<UserInfoEntity> Entity = userInfoRepository.findByUserId(pDTO.userId());
        UserInfoEntity rEntity = Entity.get();

        // userId 값만 필수고 나머지느 값이 null이거나 "" 이면 DB에 있는 정보 그대로 다시 저장
        UserInfoEntity pEntity = UserInfoEntity.builder()
                .userId(pDTO.userId())
                .password(Optional.ofNullable(pDTO.password()).filter(n -> !n.isEmpty()).orElse(rEntity.getPassword()))
                .email(Optional.ofNullable(pDTO.email()).filter(n -> !n.isEmpty()).orElse(rEntity.getEmail()))
                .userName(Optional.ofNullable(pDTO.userName()).filter(n -> !n.isEmpty()).orElse(rEntity.getUserName()))
                .userDate(rEntity.getUserDate())
                .addr1(Optional.ofNullable(pDTO.addr1()).filter(n -> !n.isEmpty()).orElse(rEntity.getAddr1()))
                .addr2(Optional.ofNullable(pDTO.addr2()).filter(n -> !n.isEmpty()).orElse(rEntity.getAddr2()))
//                .userAlarm(Optional.ofNullable(pDTO.userAlarm()).filter(n -> !n.isEmpty()).orElse(rEntity.getUserAlarm()))
                .roles(Optional.ofNullable(pDTO.roles()).filter(n -> !n.isEmpty()).orElse(rEntity.getRoles()))
                .build();

        //DB 수정
        userInfoRepository.save(pEntity);

        res = 1;

        log.info(getClass().getName() + " userUpdate end");

        return res;
    }

    @Override
    @Transactional
    public int userDelete(String userId) throws Exception {
        log.info(getClass().getName() + "userDelete 시작");

        int res = 0;

        userInfoRepository.deleteByUserId(userId);
        /**
         * 다른 서비스의 DELETE 쿼리 API 화 시켜서 추가하기
         */

        res = 1;

        log.info(getClass().getName() + "userDelete 종료");

        return res;
    }

    @Override
    public int userEmailCheck(UserInfoDTO dto) throws Exception {

        Optional<UserInfoEntity> rEntity = userInfoRepository.findByUserId(dto.userId());


        String email = EncryptUtil.encAES128CBC(dto.email());

        log.info("eamil : " + email);
        log.info("eamil : " + rEntity.get().getEmail());

        int res = 0;
        if (email.equals(rEntity.get().getEmail())) {
            log.info("성공");
            res = 2;
        }

        return res;
    }

    @Override
    public int changePwd(UserInfoDTO pDTO) throws Exception {


        int res = 0;

        Optional<UserInfoEntity> rEntity = userInfoRepository.findByUserId(pDTO.userId());

        UserInfoEntity nEntity = UserInfoEntity.builder()
                .userId(pDTO.userId())
                .password(pDTO.password())
                .email(rEntity.get().getEmail())
                .userName(rEntity.get().getUserName())
                .userDate(rEntity.get().getUserDate())
                .addr1(rEntity.get().getAddr1())
                .addr2(rEntity.get().getAddr2())
                .roles(rEntity.get().getRoles())
                .build();

        userInfoRepository.save(nEntity);

        res = 1;
        return res;
    }


}
