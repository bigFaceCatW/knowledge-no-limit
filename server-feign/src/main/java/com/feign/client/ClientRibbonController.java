package com.feign.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: Facecat
 * @Date: 2020/3/8 11:55
 */
@RestController
public class ClientRibbonController {
    @Autowired
    private RestTemplate restTemplate;
    @Resource
    private DiscoveryClient discoveryClient;

    //    线程安全
    private volatile Set<String> urls = new HashSet<>();

    @GetMapping(value = "/callRibbon/service/{message}")
    public String client(@PathVariable String message){
        //Set<String>  set = new LinkedHashSet<>(discoveryClient.getServices()); //获取服务列表
        //轮训列表
        //选择其中一台服务器
        //发送请求
        //获取响应
        List<String> list = new ArrayList<>(this.urls);
        int n = new Random().nextInt(list.size());
        String targetUrl = list.get(n);
        return restTemplate.getForObject(targetUrl+"/service/"+message,String.class);
    }

   @Scheduled(fixedRate = 10*1000)
    public void updateUrl(){

        List<ServiceInstance> list = discoveryClient.getInstances("provider");//获取应用的机器列表
        Set<String> newUrls = list.stream().
                map(s -> "http://" + s.getHost() + ":" + s.getPort())
                .collect(Collectors.toSet());
        this.urls =newUrls;

    }


}


