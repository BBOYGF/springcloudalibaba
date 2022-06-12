package com.southwind.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author guofan
 * @Create 2022/6/12
 */
@RestController
public class ConsumerController {

    @Autowired
    private DiscoveryClient discoveryClient;
    //  需要装载
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/instances")
    public List<ServiceInstance> instanceList() {
        // provider 是服务名称 就是 application name
        List<ServiceInstance> provider = this.discoveryClient.getInstances("provider");
        return provider;
    }

    //    @GetMapping("/index")
//    public String index() {
//        //1、找到服务
//        List<ServiceInstance> providerList = this.discoveryClient.getInstances("provider");
//        int index = ThreadLocalRandom.current().nextInt(providerList.size());
//        ServiceInstance serviceInstance = providerList.get(index);
//        String uri = serviceInstance.getUri() + "/index";
//        //2、调用服务的controller
//        return "调用了端口为：" + serviceInstance.getPort() + "的服务，返回结果是：" + restTemplate.getForObject(uri, String.class);
//    }

    /**
     *  使用Ribbon方法调用服务
     * @return 结果
     */
    @GetMapping("/index")
    public String index() {
        return this.restTemplate.getForObject("http://provider/index", String.class);
    }
}
