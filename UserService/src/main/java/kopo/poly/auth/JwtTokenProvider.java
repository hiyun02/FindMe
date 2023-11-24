package kopo.poly.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import kopo.poly.dto.TokenDTO;
import kopo.poly.util.CmmUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.util.Date;

@Slf4j
@RefreshScope //config 파일의 데이터가 변경되면 서버를 재실행 해주는 기능
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.token.creator}")
    private String creator;

    @Value("${jwt.token.access.valid.time}")
    private long accessTokenValidTime;

    @Value("${jwt.token.access.name}")
    private String accessTokenName;

    @Value("${jwt.token.refresh.valid.time}")
    private long refreshTokenValidTime;

    @Value("${jwt.token.refresh.name}")
    private String refreshTokenName;

    public static final String HEADER_PREFIX = "Bearer "; // Bearer 토큰 사용을 위한 선언 값


    /**
     * JWT 토큰 (Access Token, Refresh Token) 생성
     * <p>
     * userId : 회원 아이디
     * roles : 회원권한
     * tokenType :  toekn 유형
     * return : boolean 형 true false
     */

    public String createToken(String userId, String roles, JwtTokenType tokenType) {
        log.info("createToken START !");

        log.info("userId + " + userId);

        long validTime = 0;


        // Token Type 에 따른 각각 맞는 유효기간 설정
        if (tokenType == JwtTokenType.ACCESS_TOKEN) {
            validTime = (accessTokenValidTime);
        } else if (tokenType == JwtTokenType.REFRESH_TOKEN) {
            validTime = (refreshTokenValidTime);
        }

        Claims claims = Jwts.claims()
                .setIssuer(creator) // JWT 토큰 생성자 기입 (poly로 설정)
                .setSubject(userId); // userId 저장

        claims.put("roles", roles); // JWT Payload 에 정의된 기본 옵션 외 정보를 추가 - 사용자 권한을 추가함

        Date now = new Date();
        log.info("발행 시간 : " + now);

        // 보안 키 문자를 JWT key 형태로 변경
        SecretKey secret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));

        return Jwts.builder()
                .setClaims(claims) // JWT에 들어갈 정보
                .setIssuedAt(now) // 토큰 발행 시간
                .setExpiration(new Date(now.getTime() + (validTime * 10000))) // 만료시간
                .signWith(secret, SignatureAlgorithm.HS256) // 보안 키 문자, 암호화할 알고리즘
                .compact();
    }

    /**
     * JWT 토큰에 저장된 값 가져오기
     * @param token 토큰
     * @return 회원 아이디
     */

    public TokenDTO getTokenInfo(String token) {
        log.info(getClass().getName() + "getTokenInfo start!");

        // 보안 키 문자를 JWT key 형태로 변경
        SecretKey secret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));

        //JWT 토큰 정보
        Claims claims = Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token).getBody();

        String userId = CmmUtil.nvl(claims.getSubject());
        String role = CmmUtil.nvl((String) claims.get("roles")); // LonginService 생성된 토큰의 권한명과 동일

        TokenDTO rDTO = TokenDTO.builder().userId(userId).role(role).build();

        log.info(getClass().getName() + "getTokenInfo end");

        return rDTO;
    }

    /**
     * 쿠키 및 인증헤더(Bearer) 저장된 JWT 토큰 가져오기
     * @param request request 정보
     * @param tokenType token 유형
     * @return 쿠키에 저장된 토큰 값
     */
    public String resolveToken(HttpServletRequest request, JwtTokenType tokenType) {
        log.info(getClass().getName() + "resolveToken start");

        String tokenName = "";

        //Token 유형에 따라서 유형정보 확인
        if (tokenType == JwtTokenType.ACCESS_TOKEN) {
            tokenName = accessTokenName;
        } else if (tokenType == JwtTokenType.REFRESH_TOKEN) {
            tokenName = refreshTokenName;
        }

        String token = "";

        // Cookie에 저장된 데이터 모두 가져오기
        Cookie[] cookies = request.getCookies();


        // 쿠키에 token 을 확인하고 정보 가져오기
        if (cookies != null) {
            for (Cookie key : request.getCookies()) {
                log.info("cookies 이름 :" + key.getName());

                if (key.getName().equals(tokenName)) {
                    token = CmmUtil.nvl(key.getValue());
                    break;
                }
            }
        }

        if (token.length() == 0) {
            String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
            log.info("bearerToken : " + bearerToken);
            if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(HEADER_PREFIX)) {
                token = bearerToken.substring(7);
            }
            log.info("bearerToken token :" + token);
        }

        log.info(getClass().getName() + "resolveToken end");

        return token;
    }
}
