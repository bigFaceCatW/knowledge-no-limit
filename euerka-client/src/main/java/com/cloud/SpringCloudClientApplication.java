package com.cloud;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: Facecat
 * @Date: 2020/3/6 17:23
 */
@RestController
@SpringBootApplication
@EnableDiscoveryClient  //激活服务发现客户端
public class SpringCloudClientApplication {



    public static void main(String[] args) {
        SpringApplication.run(SpringCloudClientApplication.class, args);
    }

    @Resource
    private DiscoveryClient discoveryClient;

/**获取所有服务列表
   * @param
 */
    @GetMapping("/servers")
    public Set<String> getService(){
        return new LinkedHashSet<>(discoveryClient.getServices());
    }
    /** 单个服务获取
     * @param serverName
     */
    @GetMapping("/servers/{serverName}")
    public List<ServiceInstance> getInstance(@PathVariable String serverName){
        return  discoveryClient.getInstances(serverName);

    }

    /**单个实例获取
     * @param serverName
     * @param instanceId
     */
    @GetMapping("/servers/{serverName}/{instanceId}")
    public ServiceInstance getInstance(@PathVariable String serverName,@PathVariable String instanceId){
        return discoveryClient.getInstances(serverName)
                .stream()
                .filter(serviceInstance -> instanceId.equals(serviceInstance.getServiceId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("NO Such service instance"));

    }



}
