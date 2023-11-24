package kopo.poly.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "PoliceApiService", url = "https://www.safe182.go.kr")
public interface IPoliceApiService {

    /**
     *
     * @param esntlId 고유아이디
     * @param authKey 인증키
     * @param rowSize 가져오는 사이즈
     * @return
     */
      @PostMapping (value = "/api/lcm/amberList.do",
              consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        String warnAPI(
            @RequestParam("esntlId") String esntlId,
            @RequestParam ("authKey") String authKey,
            @RequestParam ("rowSize")  int rowSize,
            @RequestParam ("page")  int page
    );


}
