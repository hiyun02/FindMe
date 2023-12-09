package kopo.poly.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name = "PushService", url = "https://api.flarelane.com")
public interface IPushAPIService {

    /**
     *
     * Push 알림
     */
    @PostMapping (value = "/v1/projects/8832919c-3f7e-4ae6-b9f5-5bbe274af3a0/notifications",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String pushAPI(
            @RequestHeader ("Authorization") String apiKey,
            @RequestHeader ("Content-Type") String type,
            @RequestPart ("targetType") String targetType,
            @RequestPart ("targetIds") List<String> targetIds,
            @RequestPart ("title") String title,
            @RequestPart ("body") String body,
            @RequestPart ("url") String url

    );




}
