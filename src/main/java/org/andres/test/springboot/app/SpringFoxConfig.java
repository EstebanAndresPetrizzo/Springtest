package org.andres.test.springboot.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {

    @Bean
    public Docket api(){
        //va a crear toda la documentacion
        //tod lo que contenga este controlador
        //se puede probar en http://localhost:8080/swagger-ui/
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.andres.test.springboot.app.controllers"))
                .paths(PathSelectors.ant("/api/cuentas/*"))
                .build();
    }
}
