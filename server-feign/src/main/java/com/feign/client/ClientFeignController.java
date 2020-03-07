package com.feign.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: Facecat
 * @Date: 2020/3/7 15:38
 */
@RestController
public class ClientFeignController {

    @Resource
    private ClientFeign clientFeign;

    @GetMapping(value = "/call/service/{message}")
    public String client(@PathVariable String message){
        return clientFeign.service(message);

    }

}
