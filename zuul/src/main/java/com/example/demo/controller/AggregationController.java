package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.AggregationService;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import rx.Observable;
import rx.Observer;

import java.util.HashMap;

/**
 * @Description:
 * @version: v1.0.0
 * @author: chenxu <br/><b>e-mail:</b><br/>pscxtc@163.com
 * @date: 2019/9/6 0006 下午 1:41
 * <p>
 * Modification History:
 * Date           Author          Version            Description
 * ---------------------------------------------------------*
 * 2019/9/6 0006      chenxu                     v1.0.0               初始创建
 */
@RestController
@RequestMapping("/aggregation")
public class AggregationController {
    public static final Logger LOGGER = LoggerFactory.getLogger(AggregationController.class);

    @Autowired
    private AggregationService aggregationService;

    @RequestMapping("/aggregate/{id}")
    public DeferredResult<HashMap<String,User>> aggregate(@PathVariable Long id){
        Observable<HashMap<String,User>> result = this.aggregateObservable(id);
        return toDeferredResult(result);
    }

    /**
     * 用于整合不同 微服务数据,这里调用 movie 2次代替
     * @param id
     * @return
     */
    public Observable<HashMap<String,User>> aggregateObservable(Long id){
        return Observable.zip(
        this.aggregationService.getMovieByUser(1L),
        this.aggregationService.getMovieByUser(2L),
                (user,admin)->{
                  HashMap<String,User> map = Maps.newHashMap();
                  map.put("user",user);
                  map.put("admin",admin);
                  return map;
                }
        );
    }

    public DeferredResult<HashMap<String,User>> toDeferredResult(Observable<HashMap<String,User>> details){
        DeferredResult<HashMap<String,User>> result = new DeferredResult<>();
        //订阅
        details.subscribe(new Observer<HashMap<String, User>>() {
            @Override
            public void onCompleted() {
                LOGGER.info("完成");
            }

            @Override
            public void onError(Throwable throwable) {
                LOGGER.error("发生错误");
            }

            @Override
            public void onNext(HashMap<String, User> stringUserHashMap) {
                result.setResult(stringUserHashMap);
            }
        });
        return result;
    }
}
