package kopo.poly.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import kopo.poly.dto.TokenDTO;
import kopo.poly.util.CmmUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Slf4j
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

    @Value("${jwt.token.refresh.name}")
    private String refreshTokenName;

    public static final String HEADER_PREFIX = "Bearer "; // Bearer 토큰 사용을 위한 선언 값

    /**
     * Access Token : 주로 사용자의 인증과 권한 부여를 위해 사용
     * Refresh Token : 액세스 토큰의 만료 시간이 지난 후, 새로운 액세스 토큰을 발급받기 위해 사용
     * Bearer Token : HTTP 인증에 사용되는 토큰,  헤더에 담겨 서버로 전송
     */

    /**
     * 토큰 생성
     *
     * @param userId 유저아이디
     * @param roles  유저 권한
     * @return JWT token
     * <p>
     * 1. 정보저장(생성자, 유저아이디, 유저권한)을 저장하는 Claims 객체를 생성하여 정보 저장
     * 2. 보안키 JWT key 정보 형태로 변수에 저장
     * 3. claims 객체, 발행시간, 만료시간, (보안키, 사용할 알고리즘) 형태로 토큰 생성
     */

    public String createToken(String userId, String roles) {
        log.info(getClass().getName() + "토큰 생성 시작");

        log.info("유저 Id : " + userId);
        Claims claims = Jwts.claims().setIssuer(creator) // JWT 생성자 poly
                .setSubject(userId); // 회원 아이디 저장

        claims.put("roles", roles);
        Date now = new Date();

        // 보안키 문자들을 JWT key 형태로 변경
        SecretKey secret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));

        log.info(getClass().getName() + "토큰 생성 종료");

        // Token 생성
        return Jwts.builder().setClaims(claims) // 정보 저장 (생성자, 회원아이디, 권한)
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + (accessTokenValidTime * 1000))) // 토큰 만료시간
                .signWith(secret, SignatureAlgorithm.HS256) // 사용할 암호화 알고리즘
                .compact();
    }


    /**
     * 토큰에 저장된 값 가져오기
     *
     * @param token 토큰값
     * @return 토큰에 들어있는 값으로 유저정보, 유저 권한
     * <p>
     * 1. yml 설정파일에 저장된 key 값을 jwt 토큰값에 맞게 변환
     * 2. key 값을 활용하여 유저정보 가져오기
     * 3. 유저정보 recode 객체에 담아서 반환
     */
    public TokenDTO getTokenInfo(String token) {
        log.info(this.getClass().getName() + "getTokenInfo Start");

        // 보안 키 문자들을 Jwt Key 형태로 변경하기
        SecretKey secret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));

        // JWT 토큰 정보
        Claims claims = Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token).getBody();

        String userId = CmmUtil.nvl(claims.getSubject());
        String role = CmmUtil.nvl((String) claims.get("roles"));// LoginService 생성된 토큰의 권한명과 동일

        log.info("유저 Id  : " + userId);
        log.info("유저 권한 : " + role);

        //Token DTO 에 유저 정보 담기
        TokenDTO rDTO = TokenDTO.builder().userId(userId).role(role).build();

        log.info(this.getClass().getName() + "getTokenInfo End");

        return rDTO;
    }

    /**
     * Spring Security 인증 정보에 저장하기
     *
     * @param token
     * @return 인증 처리한 정보(로그인 성공, 실패)
     * <p>
     * 1. token 으로 부터 getTokenInfo 를 이용하여 userId, 권한정보 가져오기
     * 2. 권한정보 (,)를 기준으로 잘라서  사용자가 현재 있는 권한 Set에 구조에 저장
     * 3. 인증토큰 (id, pw, 권한) 을 넣고 생성
     */
    public Authentication getAuthentication(String token) {
        log.info("토큰 인증이후 Security 인증 정보 저장 시작 ");
        log.info(" token : " + token);

        TokenDTO rDTO = getTokenInfo(token); // 토큰 정보 저장

        String userId = CmmUtil.nvl(rDTO.userId());
        String roles = CmmUtil.nvl(rDTO.role());

        log.info("유저 아이디 : " + userId);
        log.info("유저 권한 : " + roles);

        //GrantedAuthority 사용자가 있는 현재 있는 권한
        Set<GrantedAuthority> pSet = new HashSet<>();

        if (roles.length() > 0) {
            for (String role : roles.split(",")) {
                pSet.add(new SimpleGrantedAuthority(role));
            }
        }

        log.info("토큰 인증이후 Security 인증 정보 저장 종료");

        // Spring Security 가 로그인 성공된 정보를 Spring Security 에서 사용하기 위해
        // Spring Security 용 UsernamePasswordAuthenticationToken 생성
        return new UsernamePasswordAuthenticationToken(userId, "", pSet);

    }

    /**
     * 쿠기에 저장 및 HTTP 인증 헤더에 저장된 JWT 토큰(Access Token, Refresh Token) 가져오기
     *
     * @param request   request 정보
     * @param tokenType token 유형
     * @return 토큰 값
     * <p>
     * 토큰 유형
     */

    //ServerHttpRequest 비동기식 처리 형태 객체 자주 쓰는 HttpServletRequest와 구별됨
    //
    public String resolveToken(ServerHttpRequest request, JwtTokenType tokenType) {
        log.info(getClass().getName() + "토큰 저장장소에서 가져오기 시작");

        String token = "";
        String tokenName = "";

        if (tokenType == JwtTokenType.ACCESS_TOKEN) { // Access Token이라면
            tokenName = accessTokenName;

        } else if (tokenType == JwtTokenType.REFRESH_TOKEN) { // Refresh Token이라면
            tokenName = refreshTokenName;

        }

        // getFirst() : ServerHttpRequest 에서 사용하는 함수로 비동기식에서 사용하는
        // 함수로 원하는값이 보이면 바로 key 값이 동일한 값을 반환
        HttpCookie cookie = request.getCookies().getFirst(tokenName);

        if (cookie != null) {
            token = CmmUtil.nvl(cookie.getValue());

        }

        // Cookies에 토큰이 존재하지 않으면, Baerer 토큰에 값이 있는지 확인함
        if (token.length() == 0) {
            String bearerToken = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

            log.info("bearerToken : " + bearerToken);
            if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(HEADER_PREFIX)) {
                token = bearerToken.substring(7);
            }
            log.info("bearerToken token : " + token);
        }


        log.info(getClass().getName() + "토큰 저장장소에서 가져오기 종료");
        return token;
    }

    /**
     * Jwt 토큰 상태 확인
     * @param token 토큰
     * @return 상태정보(EXPIRED, ACCESS, DENIED)
     */
    public JwtStatus validateToken(String token) {
        log.info(getClass().getName() + "Token 정보 확인 시작");

        if (token.length() > 0) try {
            // 보안키 문자 JWT KEY 형태로 변경
            SecretKey secret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));

            // 토큰 값 꺼내기
            Claims claims = Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token).getBody();

            // 토큰의 만료 여부 체크
            if (claims.getExpiration().before(new Date())) {
                return JwtStatus.EXPIRED; // 만료
            } else {
                return JwtStatus.ACCESS; // 유효
            }


        } catch (ExpiredJwtException e) {
            return JwtStatus.EXPIRED; // 만료기간 Exception 으로 한번 더 체크
        } catch (JwtException | IllegalArgumentException e) {
            log.info("jwtException : {}", e);

            return JwtStatus.DENIED;
        }
        else {
            return JwtStatus.DENIED;
        }


    }


}