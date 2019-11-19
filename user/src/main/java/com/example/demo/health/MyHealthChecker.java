package com.example.demo.health;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpStatus;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 自定义 健康检查
 *
 * @author chenxu
 * @since 2019年11月19日
 */
@Slf4j
@Component
@Data
public class MyHealthChecker implements HealthIndicator {

    /** 健康检查状态 */
    private boolean state = false;

    /** 是否需要端口检查标识*/
    private boolean changeFlag = true;

    @Value("${server.port}")
    private String port ;
    @Override
    public Health health() {
        log.warn("{}------健康检查被调用!当前状态:{}",new Date(),this.state);

        //是否需要 主动改变 状态
        log.warn("是否需要主动变更:{}",changeFlag);
        if (changeFlag){
            log.warn("http://localhost:{}/healthCheck",port);
            HttpResponse res = HttpRequest.post("http://localhost:"+port+"/healthCheck").execute();

            log.warn("返回状态:{}",res.getStatus());
            if (HttpStatus.HTTP_OK == res.getStatus()){
                state = true;
                changeFlag = false;
                log.warn("健康检查通过!");
            }
        }
        if (state) {
            //自定义监控内容
            return new Health.Builder().withDetail("aaa_cnt", 10)
                    .withDetail("bbb_status", "state").up().build();
        } else {
            return new Health.Builder().withDetail("error", "client is down").down().build();
        }
    }
}
