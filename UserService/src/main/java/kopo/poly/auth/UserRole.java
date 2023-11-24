package kopo.poly.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * 일반회원과 관리자 구분
 */
@AllArgsConstructor
@Getter
public enum UserRole {

    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private final String value;
}
