package kopo.poly.dto;

import lombok.Builder;

@Builder
public record AlarmMsgDTO(
        String token,
        String title
) {
}
