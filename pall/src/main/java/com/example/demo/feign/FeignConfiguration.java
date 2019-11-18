package com.example.demo.feign;

import feign.Contract;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @version: v1.0.0
 * @author: chenxu <br/><b>e-mail:</b><br/>pscxtc@163.com
 * @date: 2019/9/5 0005 下午 1:40
 * <p>
 * Modification History:
 * Date           Author          Version            Description
 * ---------------------------------------------------------*
 * 2019/9/5 0005      chenxu                     v1.0.0               初始创建
 */
@Configuration
public class FeignConfiguration {

    /**
     * @Description: 初始化 springmvc 结构很关键,关系到参数格式
     * @param
     * @return feign.Contract
     * @version: v1.0.0
     * @author: chenxu <br/><b>e-mail:</b><br/>pscxtc@163.com
     * @date: 2019/9/5 0005 下午 3:01
     * Modification History:
     * Date           Author          Version            Description
     *---------------------------------------------------------*
     * 2019/9/5 0005      chenxu           v1.0.0             初始创建
     */
    @Bean
    public Contract feignContract(){
        return new SpringMvcContract();
    }

}
