package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

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
@Slf4j
@RestController
@RequestMapping(value = "/user")
public class UserController {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/getById/{id}")
    public User getById(@PathVariable("id") Long id) throws UnknownHostException {
        User user = userRepository.findById(id).get();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        user.setIp("request_ip:"+IpUtil.getIpAddr(request)+";local_ip:"+ InetAddress.getLocalHost().getHostAddress()+";time:"+System.currentTimeMillis());
        return user;
    }

    @RequestMapping("/getAll/")
    public List<User> getAll(){
        return userRepository.findAll();
    }

    @RequestMapping("/hello/{name}")
    public String hello(@PathVariable("name") String name){
        log.info(name);
        return "Hello : "+name;
    }
}
