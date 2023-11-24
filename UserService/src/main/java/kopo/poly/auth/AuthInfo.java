package kopo.poly.auth;

import kopo.poly.dto.UserInfoDTO;
import kopo.poly.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


/**
 *
 * @param userInfoDTO 로그인 된 사용자 정보 UserInfoRepository 로 부터
 *                    조회된 정보를 저장하기 위한 객체
 */
@Slf4j
public record AuthInfo(UserInfoDTO userInfoDTO) implements UserDetails {


    /*
        로그인한 사용자 권한 부여
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Set<GrantedAuthority> pSet = new HashSet<>();

        String roles = CmmUtil.nvl(userInfoDTO.roles());

        log.info("getAuthorities / rolse : " + roles);

        if (roles.length() > 0) {
            for (String role : roles.split(",")) {
                pSet.add(new SimpleGrantedAuthority(role));
            }
        }

        return pSet;
    }

    /*
        사용자의 id를 반환
     */
    @Override
    public String getUsername() {
        log.info("uerInfo DTO :" + userInfoDTO.userId());

        return CmmUtil.nvl(userInfoDTO.userId());}

    /*
        사용자의 pw를 반환
     */
    @Override
    public String getPassword() {return CmmUtil.nvl(userInfoDTO.password());}


    //계정 만료 여부 반환 => 만료되지않음
    @Override
    public boolean isAccountNonExpired() {return true;}

    // 계정 잠금 여부 반환 => 잠금 x
    @Override
    public boolean isAccountNonLocked() {return true;}

    // 패스워드의 만료 여부 반환 => 만료되지 않음
    @Override
    public boolean isCredentialsNonExpired() {return true;}

    // 계정 사용가능 여부 반환 => 사용 가능
    @Override
    public boolean isEnabled() {return true;}
}
