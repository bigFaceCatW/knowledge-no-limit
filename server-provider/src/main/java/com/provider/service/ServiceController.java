package com.provider.service;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

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
    @HystrixCommand(fallbackMethod = "errorContent",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "100")
    })
    public String service(@PathVariable String message) throws InterruptedException {
        int time = new Random().nextInt(200);
        Thread.sleep(time);

        return "服务端返回--->"+message+"--->端口号："+getPort()+"--->"+time;

    }

    public  String errorContent(String message){
        return "请求超时--"+message;

    }


}
