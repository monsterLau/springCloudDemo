package com.lau.springcloud.service;

import com.lau.springcloud.pojo.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeptClientServiceFallBackFactory implements FallbackFactory {
    @Override
    public DeptClientService create(Throwable throwable) {
        return new DeptClientService() {
            @Override
            public Dept queryById(Long id) {
                Dept dept = new Dept();
                dept.setDeptno(id);
                dept.setDname("id=>" + id + "没有对应信息，客户端提供了降级的信息，这个服务已被关闭");
                dept.setDb_source("no data.");
                return dept;
            }

            @Override
            public List<Dept> queryAll() {
                return null;
            }

            @Override
            public boolean add(Dept dept) {
                return false;
            }
        };
    }
}
