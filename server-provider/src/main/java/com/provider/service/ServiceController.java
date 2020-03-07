package com.provider.service;


import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Facecat
 * @Date: 2020/3/7 15:06
 */

@RestController
public class ServiceController {

    private final Environment environment;

    public ServiceController(Environment environment) {
        this.environment = environment;
    }

    private String getPort(){
        return environment.getProperty("local.server.port");
    }

    @GetMapping(value="/service/{message}")
    public String service(@PathVariable String message){
        return "服务端返回--->"+message+"--->端口号："+getPort();

    }
}
