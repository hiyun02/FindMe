package kopo.poly.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public record DeviceDTO(
        String pushToken,
        String userId,
        String deviceDate

) {
}
