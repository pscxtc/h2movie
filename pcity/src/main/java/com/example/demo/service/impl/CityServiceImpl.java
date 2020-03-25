package com.example.demo.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.example.demo.entity.City;
import com.example.demo.mapper.CityMapper;
import com.example.demo.service.CityService;
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
public class CityServiceImpl implements CityService {

    @Resource
    private CityMapper cityMapper;

    @Override
    public List<City> getByProvinceId(Long provinceId) {
        return cityMapper.findByProvinceId(provinceId);
    }

    @Override
    public List<City> getAll() {
        return cityMapper.findAll();
    }

    @Override
    @LcnTransaction //分布式事务注解
    @Transactional  //本地事务注解
    public String saveCity(String cName, long provinceId) {

        City c = new City();
        c.setCityName(cName);
        c.setProvinceId(provinceId);

        int result = cityMapper.insert(c);

        int i = 1/0;

        if (result > 0)
            return " save City success";
        else {
            return " save City failed";
        }
    }
}