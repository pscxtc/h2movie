package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.IpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @version: v1.0.0
 * @author: chenxu <br/><b>e-mail:</b><br/>pscxtc@163.com
 * @date: 2019/9/3 0003 下午 2:16
 * <p>
 * Modification History:
 * Date           Author          Version            Description
 * ---------------------------------------------------------*
 * 2019/9/3 0003      chenxu                     v1.0.0               初始创建
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/getById/{id}")
    public User getById(@PathVariable("id") Long id){
        User user = userRepository.findById(id).get();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        user.setIp(IpUtil.getIpAddr(request)+"---"+System.currentTimeMillis());
        return user;
    }

}
