package kopo.poly.service.impl;

import jakarta.mail.internet.MimeMessage;
import kopo.poly.dto.MailCodeDTO;
import kopo.poly.repository.UserInfoRepository;
import kopo.poly.repository.entity.UserInfoEntity;
import kopo.poly.service.IMailService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.EncryptUtil;
import kopo.poly.util.MailCodeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class MailService implements IMailService {


    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromMail;

    private final PasswordEncoder bCryptPasswordEncoder;
    private final UserInfoRepository userRepository;

    /**
     * 인증 메일 보내주기
     */

    @Override
    public int sendCode(MailCodeDTO mDTO) throws Exception {

        log.info(getClass().getName() + "sendCode start");

        int res = 0;


        String email = EncryptUtil.encAES128CBC(CmmUtil.nvl(mDTO.toMail()));

        Optional<UserInfoEntity> rEntity = userRepository.findByEmail(email);

        if (rEntity.isPresent()) {
            res = 1;// 이메일 중복
        } else {


            try {

                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper messageHelper = new MimeMessageHelper(message, "UTF-8");

                message.setSubject("WZIP의 인증 메일번호가 도착했습니다"); //제목
                String msgg = "";
                msgg += "<div style = 'margin:100px;' >";
                msgg += "<h1> 안녕하세요 WZIP 입니다 ! </h1> ";
                msgg += "<br>";
                msgg += "<p> 회원가입 창으로 돌아가 아래 코드를 입력해주세요 </p>";
                msgg += "<br>";
                msgg += "<p> 감사합니다 </p>";
                msgg += "<br>";
                msgg += "<h3 style='color:blue;'> 회원가입 코드입니다. </h3>";
                msgg += "<div style='font-size:130%'>";
                msgg += "CODE : <strong>";
                msgg += mDTO.mailCode() + "</strong> <br> <div><br>";


                message.setText(msgg, "utf-8", "html"); // 정보, 인코딩방식, 타입 저장
                message.setFrom(fromMail);

                messageHelper.setTo(mDTO.toMail()); //받는사람
                messageHelper.setFrom(fromMail); //보내는 사람

                mailSender.send(message);

                res = 2;

            } catch (Exception e) {
                log.info("[ERR0R] " + this.getClass().getName() + " doSendMail : " + e);

            }

        }
        log.info(getClass().getName() + "sendCode end");

        return res;

        }



    /**
     * 이메일 일치 후 임시 비밀번호 발급
     */
    @Override
    public int findPassword(String email) throws Exception {
        log.info(getClass().getName() + "findPassword start ");

        String newPwd = CmmUtil.nvl(MailCodeUtil.createKey());

        log.info(getClass().getName() + "email : " + email);
        log.info("새로 생성될 비밀번호 : " + newPwd);
        log.info(getClass().getName() + "암호화된 email : " + EncryptUtil.encAES128CBC(email));

        int res = 0;

        try {
            Optional<UserInfoEntity> rEntity = userRepository.findByEmail(EncryptUtil.encAES128CBC(email));


            if (rEntity.isPresent()) {
                log.info("임시비밀번호로 업데이트 시작");
                res = 1;
                UserInfoEntity pEntity = UserInfoEntity.builder()
                        .userId(rEntity.get().getUserId())
                        .password(EncryptUtil.encHashSHA256(newPwd))
                        .email(rEntity.get().getEmail())
                        .userName(rEntity.get().getUserName())
                        .userDate(rEntity.get().getUserDate())
                        .addr1(rEntity.get().getAddr1())
                        .addr2(rEntity.get().getAddr2())
                        .userAlarm(rEntity.get().getUserAlarm())
                        .roles(rEntity.get().getRoles())
                        .build();

                userRepository.save(pEntity);
                log.info("임시비밀번호로 업데이트 성공");

                //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper messageHelper = new MimeMessageHelper(message, "UTF-8");
                message.setSubject("WZIP 로부터 임시 비밀번호가 도착했습니다. "); // 제목
                String msgg = "";
                msgg += "<div style = 'margin:100px;' >";
                msgg += "<h1> 안녕하세요 WZIP 입니다 ! </h1> ";
                msgg += "<br>";
                msgg += "<p> 아래 임시비밀번호를 확인하시고 로그인을 시도해주세요. </p>";
                msgg += "<br>";
                msgg += "<p> 로그인 후 반드시 회원수정을 통해 비밀번호를 변경해주세요! </p>";
                msgg += "<p> 감사합니다 </p>";
                msgg += "<br>";
                // msgg+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
                msgg += "<h3 style='color:blue;'> 회원님의 임시비밀번호 입니다. </h3>";
                msgg += "<div style='font-size:130%'>";
                msgg += "임시비밀번호 : <strong>";
                msgg += newPwd + "</strong> <br> <div><br>";
                // msgg+= "</div>";

                message.setText(msgg, "utf-8", "html");
                message.setFrom(fromMail);

                messageHelper.setTo(email);
                messageHelper.setFrom(fromMail);

                mailSender.send(message);

            }else {
                log.info("업데이트 실패");
            }

        }catch (Exception e) {
            res = 0;
            log.info("[ERR0R] " + this.getClass().getName() + " doSendMail : " + e);

        }
        log.info(getClass().getName() + "findPassword end ");

        return res;
    }

    /**
     *
     * @param mDTO
     * @return
     * @throws Exception
     */
    @Override
    public int findCode(MailCodeDTO mDTO) throws Exception {

        int res = 0;

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, "UTF-8");

        message.setSubject("WZIP의 인증 메일번호가 도착했습니다"); //제목
        String msgg = "";
        msgg += "<div style = 'margin:100px;' >";
        msgg += "<h1> 안녕하세요 WZIP 입니다 ! </h1> ";
        msgg += "<br>";
        msgg += "<p> 회원가입 창으로 돌아가 아래 코드를 입력해주세요 </p>";
        msgg += "<br>";
        msgg += "<p> 감사합니다 </p>";
        msgg += "<br>";
        msgg += "<h3 style='color:blue;'> 인증 코드입니다. </h3>";
        msgg += "<div style='font-size:130%'>";
        msgg += "CODE : <strong>";
        msgg += mDTO.mailCode() + "</strong> <br> <div><br>";


        message.setText(msgg, "utf-8", "html"); // 정보, 인코딩방식, 타입 저장
        message.setFrom(fromMail);

        messageHelper.setTo(mDTO.toMail()); //받는사람
        messageHelper.setFrom(fromMail); //보내는 사람

        mailSender.send(message);

        res = 1;


        return res;
    }

}
