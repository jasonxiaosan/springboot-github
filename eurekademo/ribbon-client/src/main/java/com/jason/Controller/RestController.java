package com.jason.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Administrator on 2018/6/22.
 */
@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
private LoadBalancerClient loadBalancerClient;
    @GetMapping("/testribbon")
    public String testribbon(){
        ServiceInstance instance = loadBalancerClient.choose("stores");
        return instance.getHost()+":"+instance.getPort();
    }
}
