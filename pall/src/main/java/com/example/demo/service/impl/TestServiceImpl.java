package com.example.demo.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.example.demo.feign.CityFeignClient;
import com.example.demo.feign.ProvinceFeignClient;
import com.example.demo.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:
 * @version: v1.0.0
 * @author: chenxu <br/><b>e-mail:</b><br/>pscxtc@163.com
 * @date: 2019/10/15 0015 下午 3:00
 * <p>
 * Modification History:
 * Date           Author          Version            Description
 * ---------------------------------------------------------*
 * 2019/10/15 0015      chenxu                     v1.0.0               初始创建
 */
@Service
public class TestServiceImpl implements ITestService {


    @Autowired
    private CityFeignClient cityFeignClient;
    @Autowired
    private ProvinceFeignClient provinceFeignClient;

    @Override
    @LcnTransaction //分布式事务注解
    @Transactional //本地事务注解
    public String saveAll() {

        String pResult = provinceFeignClient.addProvince("内蒙古");
        String cResult = cityFeignClient.addCity("大庆",5);

        return cResult+","+pResult;
    }
}
