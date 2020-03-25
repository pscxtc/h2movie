package com.example.demo.service.impl;

import com.example.demo.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * TODO: 类注释
 *
 * @author chenxu
 * @since 2020年01月22日
 */
@Service("userService")
public class UserServiceImpl implements IUserService {
    @Override
    public String test() {
        String path = "com.example.demo.service.impl.UserServiceImpl.test";
        System.out.println("this is "+this.getClass().getSimpleName());
        return path;
    }
}
