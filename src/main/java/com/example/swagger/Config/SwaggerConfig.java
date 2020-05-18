package com.example.swagger.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /** 配置多个Docket实例 */
    @Bean
    public Docket docket1(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("A");
    }

    @Bean
    public Docket docket2(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("B");
    }

    @Bean
    public Docket docket3(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("C");
    }

    /** 配置swagger的Docket的bean实例 */
    @Bean
    public Docket docket(Environment environment){

        /** 设置要显示的Swagger环境，读到哪个取哪个，dev为true，否则为false */
        Profiles profiles = Profiles.of("dev","test");
        
        /**通过 environment.acceptsProfiles 判断是否处在自己设定的环境当中*/
        boolean flag = environment.acceptsProfiles(profiles);


        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(flag) //监听环境
                .select()
                //RequestHandlerSelectors:配置要扫描接口的方式
                .apis(RequestHandlerSelectors.basePackage("com.example.swagger.Controller"))
                //paths()：过滤什么路径
                //.paths(PathSelectors.ant("/**"))
                .build();
    }

    /** 配置Swagger信息=apiInfo */
    private ApiInfo apiInfo(){

        /** 作者信息 */
        Contact contact = new Contact("drion","http:xxxxxxx.com","qqq@qq.com");

        return new ApiInfo(
                "Api Documentation",
                "Api Documentation",
                "1.0",
                "urn:tos",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList()
        );
    }
}
