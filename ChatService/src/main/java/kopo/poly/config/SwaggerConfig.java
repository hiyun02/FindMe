package kopo.poly.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    private Info apiInfo() {
        return new Info()
                .title("ChatService")
                .description("Chat Service Description!")
                .contact(new Contact().name("WonJip Kim")
                        .email("kbg5174@naver.com")
                        .url("https://github.com/wonjibkim"))
                .license(new License()
                        .name("이협건 교수님 git 주소 : https://github.com/Hyeopgeon-Lee"))
                .version("1.0.00");
    }


    /*
        정의한 apiInfo 를 openApi 도구에서 Api 문서 생성
     */
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().components(new Components()).info(apiInfo());
    }

}
