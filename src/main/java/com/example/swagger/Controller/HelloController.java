package com.example.swagger.Controller;

import com.example.swagger.Pojo.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    /** 只要在接口中，返回值中存在实体类，就会被扫描到Swagger扫描到 */
    @PostMapping("/user")
    public User user(){
        return new User();
    }
    //Operation接口，放在方法上
    @ApiOperation("Hello控制类")
    @GetMapping("/hello2")
    public String hello2(@ApiParam("用户名") String username){
        return "hello"+username;
    }

    //Operation接口，放在方法上
    @ApiOperation("Hello控制类")
    @PostMapping("/post")
    public User post(@ApiParam("用户名") User user){
        return user;
    }

}
