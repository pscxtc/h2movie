package com.example.demo.feign.fallBack;

import com.example.demo.feign.UserFeignClient;
import com.example.demo.model.User;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * hystrix 熔断处理
 *
 * @author chenxu
 * @since 2019年11月20日
 */
@Slf4j
@Component
public class UserFeignClientFallBack implements FallbackFactory<UserFeignClient> {
    @Override
    public UserFeignClient create(Throwable throwable) {

        String msg = null == throwable ? "":throwable.getMessage();
        if (StringUtils.isNotEmpty(msg)){
            log.error(msg);
        }

        return  new UserFeignClient(){

            @Override
            public User getById(Long id) {
                return new User();
            }

            @Override
            public List<User> getAll() {
                return new ArrayList<>();
            }
        };
    }
}
