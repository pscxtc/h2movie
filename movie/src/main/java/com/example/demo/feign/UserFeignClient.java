package com.example.demo.feign;

import com.example.demo.feign.fallBack.UserFeignClientFallBack;
import com.example.demo.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

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
@FeignClient(value = "provider-user",fallbackFactory = UserFeignClientFallBack.class)
//@FeignClient(value = "provider-user")
@Component
public interface UserFeignClient {

    /**
     * @Description: config中启用 SpringMvcContract ,才能采用spingmvc 参数传递
     * @param id
     * @return com.example.demo.model.User
     * @version: v1.0.0
     * @author: chenxu <br/><b>e-mail:</b><br/>pscxtc@163.com
     * @date: 2019/9/5 0005 下午 3:02
     * Modification History:
     * Date           Author          Version            Description
     *---------------------------------------------------------*
     * 2019/9/5 0005      chenxu           v1.0.0             初始创建
     */
    @RequestMapping(value = "/user/getById/{id}", method = RequestMethod.GET)
    User getById(@PathVariable("id") Long id);

    /**
     * 查询全部
     * @param
     * @return {@link List<User>}
     * @since 2019/11/20
     * 版本历史:
     * Date         Author         Description
     *---------------------------------------------------------*
     * 2019/11/20    chenxu          初始创建
     */
    @RequestMapping(value = "/user/getAll", method = RequestMethod.GET)
    List<User> getAll();
}
