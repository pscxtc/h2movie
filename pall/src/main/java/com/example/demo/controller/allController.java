package com.example.demo.controller;

import com.example.demo.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @version: v1.0.0
 * @author: chenxu <br/><b>e-mail:</b><br/>pscxtc@163.com
 * @date: 2019/10/15 0015 下午 2:56
 * <p>
 * Modification History:
 * Date           Author          Version            Description
 * ---------------------------------------------------------*
 * 2019/10/15 0015      chenxu                     v1.0.0               初始创建
 */
@RestController
@RequestMapping("/all")
public class allController {

    @Autowired
    private ITestService testService;

    @RequestMapping("/testOne")
    public String testOne(){
        return testService.saveAll();
    }

}
