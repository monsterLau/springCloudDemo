package com.lau.springcloud.service;

import com.lau.springcloud.dao.DeptDao;
import com.lau.springcloud.pojo.Dept;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DeptService implements DeptDao {

    @Resource
    private DeptDao deptDao;

    @Override
    public boolean addDept(Dept dept) {
        return deptDao.addDept(dept);
    }

    @Override
    public Dept queryById(Long id) {
        return deptDao.queryById(id);
    }

    @Override
    public List<Dept> queryAll(Dept dept) {
        return deptDao.queryAll(dept);
    }
}
