package com.example.demo.controller;

import com.example.demo.feign.UserFeignClient;
import com.example.demo.model.User;
import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @Description:
 * @version: v1.0.0
 * @author: chenxu <br/><b>e-mail:</b><br/>pscxtc@163.com
 * @date: 2019/9/3 0003 下午 2:43
 * <p>
 * Modification History:
 * Date           Author          Version            Description
 * ---------------------------------------------------------*
 * 2019/9/3 0003      chenxu                     v1.0.0               初始创建
 */
@Slf4j
@Import(FeignClientsConfiguration.class)
@RestController
@RequestMapping("/movie")
public class MovieController {

    public static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    private UserFeignClient adminFeignClient;

    @Autowired
    private UserFeignClient userFeignClient;

    @RequestMapping("/findByUserId/{id}")
    public User findByUserId(@PathVariable("id") Long id){
        return restTemplate.getForObject("http://provider-user/user/"+id,User.class);
    }

    @RequestMapping("/test/{id}")
    public String getTest(@PathVariable Long id){
        return "测试结果: "+id;
    }

//    @RequestMapping("/findByUserIdF/{id}")
//    public User findByUserIdF(@PathVariable("id") Long id){
//        return userFeignClient.findById(id);
//    }

    @RequestMapping("/showInfo")
    public List<ServiceInstance> showInfo(){
        return discoveryClient.getInstances("provider-user");
    }

    @RequestMapping("/logUserInstance")
    public void logUserInstance(){
        ServiceInstance serviceInstance = loadBalancerClient.choose("provider-user");

        LOGGER.info("logUserInstance / {}:{}:{}",serviceInstance.getServiceId(),serviceInstance.getHost(),serviceInstance.getPort());
    }

    public MovieController (Decoder decoder, Encoder encoder, Client client, Contract contract){
        this.adminFeignClient = Feign.builder().client(client).encoder(encoder).decoder(decoder).contract(contract)
                .requestInterceptor(new BasicAuthRequestInterceptor("admin","password2")).target(UserFeignClient.class,"http://127.0.0.1:8000/");
    }

    /**   
     * @Description: 
     * @param id
     * @return com.example.demo.model.User
     * @version: v1.0.0
     * @author: chenxu <br/><b>e-mail:</b><br/>pscxtc@163.com
     * @date: 2019/11/19 0019 上午 12:02 
     * Modification History:
     * Date           Author          Version            Description
     *---------------------------------------------------------*
     * 2019/11/19 0019      chenxu           v1.0.0             初始创建
     */
    @RequestMapping("/user/{id}")
    public User findByIdUser(@PathVariable("id") Long id){
        return this.userFeignClient.getById(id);
    }

//    public User findByIdUserFallback( Long id,Throwable throwable){
//        LOGGER.error("退回 :",throwable);
//        User user = new User();
//        user.setId(-1L);
//        user.setName("默认用户");
//        return user;
//    }

    @RequestMapping("/admin/{id}")
    public User findByIdAdmin(@PathVariable("id") Long id){
        return this.adminFeignClient.getById(id);
    }

    @RequestMapping("/bye/{name}")
    public String bye(@PathVariable("name") String name){
        log.info(name);
        return "bye : "+name;
    }


    @RequestMapping("/hello/{name}")
    public String hello(@PathVariable("name") String name){
        log.info(name);
        return "movie : "+this.userFeignClient.hello(name);
    }
//    public User findByIdAdminFallback( Long id){
//        User user = new User();
//        user.setId(-1L);
//        user.setName("默认管理员");
//        return user;
//    }
}
