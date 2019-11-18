package com.example.demo.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.example.demo.entity.Province;
import com.example.demo.mapper.ProvinceMapper;
import com.example.demo.service.ProvinceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @version: v1.0.0
 * @author: chenxu <br/><b>e-mail:</b><br/>pscxtc@163.com
 * @date: 2019/10/15 0015 下午 1:29
 * <p>
 * Modification History:
 * Date           Author          Version            Description
 * ---------------------------------------------------------*
 * 2019/10/15 0015      chenxu                     v1.0.0               初始创建
 */
@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Resource
    private ProvinceMapper provinceMapper;

    @Override
    public List<Province> getByProvinceId(Long provinceId) {
        return provinceMapper.findByProvinceId(provinceId);
    }

    @Override
    public List<Province> getAll() {
        return provinceMapper.findAll();
    }

    @Override
    @LcnTransaction //分布式事务注解
    @Transactional  //本地事务注解
    public String saveProvince(String pName) {
        Province c = new Province();
        c.setName(pName);

        int result = provinceMapper.insert(c);

        if (result > 0)
            return " save Province success";
        else {
            return " save Province failed";
        }
    }


}