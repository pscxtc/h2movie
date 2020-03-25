package com.example.demo.service.impl;

import com.example.demo.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * TODO: 类注释
 *
 * @author chenxu
 * @since 2020年01月22日
 */
@Service("customerService")
public class CustomerServiceImpl implements IUserService {
    @Override
    public String test() {
        String path = "com.example.demo.service.impl.CustomerServiceImpl.test";
        System.out.println("this is "+path);
        return path;
    }
}
