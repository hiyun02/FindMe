package kopo.poly.dto;

import lombok.Builder;

@Builder
public record MailCodeDTO(
        String toMail, // 받는사람
        String mailCode // 메일로 전송될 코드
) {
}
