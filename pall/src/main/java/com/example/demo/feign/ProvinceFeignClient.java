package com.example.demo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description:
 * @version: v1.0.0
 * @author: chenxu <br/><b>e-mail:</b><br/>pscxtc@163.com
 * @date: 2019/9/3 0003 下午 5:20
 * <p>
 * Modification History:
 * Date           Author          Version            Description
 * ---------------------------------------------------------*
 * 2019/9/3 0003      chenxu                     v1.0.0               初始创建
 */
//@FeignClient(value = "provider-user",fallback = FeignClientFallback.class)
@FeignClient(value = "provider-province",configuration = FeignConfiguration.class)
@Component
public interface ProvinceFeignClient {

    /**
     * @Description: config中启用 SpringMvcContract ,才能采用spingmvc 参数传递
     * @param
     * @return com.example.demo.model.User
     * @version: v1.0.0
     * @author: chenxu <br/><b>e-mail:</b><br/>pscxtc@163.com
     * @date: 2019/9/5 0005 下午 3:02
     * Modification History:
     * Date           Author          Version            Description
     *---------------------------------------------------------*
     * 2019/9/5 0005      chenxu           v1.0.0             初始创建
     */
    @RequestMapping(value = "/p/add/{pName}", method = RequestMethod.GET)
    String addProvince(@PathVariable("pName") String pName);
}
