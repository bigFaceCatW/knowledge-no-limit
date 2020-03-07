package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @create 2020/3/6
 */
@SpringBootApplication
@EnableEurekaServer
public class SpringCloudServerApplication {


    public static void main(String[] args) {
        SpringApplication.run(SpringCloudServerApplication.class, args);
    }


}
