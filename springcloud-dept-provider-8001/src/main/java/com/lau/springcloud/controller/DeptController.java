package com.lau.springcloud.controller;

import com.lau.springcloud.pojo.Dept;

import com.lau.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/dept")
public class DeptController {

    @Resource
    private DeptService deptService;

    //获取一些配置信息，得到具体的微服务
    @Autowired
    private DiscoveryClient client;

    @PostMapping("add")
    public boolean addDept(Dept dept) {
        return deptService.addDept(dept);
    }

    @GetMapping("/get/{id}")
    public Dept get(@PathVariable("id") Long id) {
        Dept dept = deptService.queryById(id);
        if (dept == null) {
            throw new RuntimeException("no id:" + id);
        }
        return dept;
    }

    @GetMapping("list")
    public List<Dept> queryAll(Dept dept) {
        return deptService.queryAll(dept);
    }

    //注册进来的微服务，获取信息
    @GetMapping("/discovery")
    public Object discovery() {
        //获取微服务列表清单
        List<String> services = client.getServices();
        System.out.println("discovery => services:" + services);
        //得到一个具体的微服信息，通过具体的微服务id
        List<ServiceInstance> instances = client.getInstances("SPRINGCLOUD-PROVIDER-DEPT");
        for (ServiceInstance instance : instances) {
            System.out.println("host:" + instance.getHost() + "\t"
                    + "port:" + instance.getPort() + "\t"
                    + "uri:" + instance.getUri() + "\t"
                    + "service id:" + instance.getServiceId());

        }
        return this.client;
    }
}
