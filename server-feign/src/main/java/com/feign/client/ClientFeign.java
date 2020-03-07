package com.feign.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**创建服务端接口API,
 * @Author: Facecat
 * @Date: 2020/3/7 15:59
 */
@FeignClient("provider") //对方服务名称zk上的节点
public interface ClientFeign {

    @GetMapping(value = "/service/{message}")
    String service(@PathVariable(value = "message") String message);

}
