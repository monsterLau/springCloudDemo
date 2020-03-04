package com.lau.springcloud.service;

import com.lau.springcloud.pojo.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Component
@FeignClient(name = "SPRINGCLOUD-PROVIDER-DEPT")
public interface DeptClientService {
    @GetMapping("dept/get/{id}")
    Dept queryById(@PathVariable("id") Long id);

    @GetMapping("dept/list")
    List<Dept> queryAll();

    @PostMapping("dept/add")
    boolean add(Dept dept);
}
