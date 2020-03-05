package com.lau.springcloud.controller;

import com.lau.springcloud.pojo.Dept;
import com.lau.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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

    @PostMapping("add")
    public boolean addDept(Dept dept) {
        return deptService.addDept(dept);
    }


    @GetMapping("/get/{id}")
    @HystrixCommand(fallbackMethod = "hystrixGet")
    public Dept get(@PathVariable("id") Long id) {
        Dept dept = deptService.queryById(id);
        if (dept != null) {
            throw new RuntimeException("该id->:" + id + "查询不到信息");
        }
        return dept;
    }

    public Dept hystrixGet(@PathVariable("id") Long id) {
        Dept dept = new Dept("no this database in MySQL.");
        dept.setDeptno(id);
        dept.setDname("该id->:" + id + "查询不到信息,null---@Hystrix");
        return dept;
    }

    @GetMapping("/list")
    public List<Dept> queryAll(Dept dept) {
        return deptService.queryAll(dept);
    }

}
