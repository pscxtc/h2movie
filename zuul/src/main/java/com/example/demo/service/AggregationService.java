package com.example.demo.service;

import com.example.demo.model.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rx.Observable;


/**
 * @Description:
 * @version: v1.0.0
 * @author: chenxu <br/><b>e-mail:</b><br/>pscxtc@163.com
 * @date: 2019/9/6 0006 下午 1:34
 * <p>
 * Modification History:
 * Date           Author          Version            Description
 * ---------------------------------------------------------*
 * 2019/9/6 0006      chenxu                     v1.0.0               初始创建
 */
@Service
public class AggregationService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallback")
    public Observable<User> getUserById(Long id){
        return  Observable.create(observer ->{
            User user = restTemplate.getForObject("http://provider-user/user/{id}",User.class,id);
            observer.onNext(user);
            observer.onCompleted();
        });
    }

    @HystrixCommand(fallbackMethod = "fallback")
    public Observable<User> getMovieByUser(Long id){
        return  Observable.create(observer ->{
           User movieUser = restTemplate.getForObject("http://provider-movie/movie/user/{id}",User.class,id) ;
            observer.onNext(movieUser);
            observer.onCompleted();
        });
    }

    public User fallback(Long id){
        User user = new User();
        user.setId(-1L);
        return user;
    }
}
