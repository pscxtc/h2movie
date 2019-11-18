package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @version: v1.0.0
 * @author: chenxu <br/><b>e-mail:</b><br/>pscxtc@163.com
 * @date: 2019/9/6 0006 下午 3:55
 * <p>
 * Modification History:
 * Date           Author          Version            Description
 * ---------------------------------------------------------*
 * 2019/9/6 0006      chenxu                     v1.0.0               初始创建
 */
@RestController
public class ConfigClientController {

    @Value("${profile}")
    private String profile;

    @RequestMapping("/profile")
    public String hello() {
        return profile;
    }
}
