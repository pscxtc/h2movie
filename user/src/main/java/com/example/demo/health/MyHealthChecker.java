package com.example.demo.health;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpStatus;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

import java.util.Date;

/**
 * 自定义 健康检查
 *
 * 放弃,在状态 up后,依然还有 一段不可响应期
 *
 * @author chenxu
 * @since 2019年11月19日
 */
@Slf4j
//@Component
@Data
public class MyHealthChecker implements HealthIndicator {

    /** 健康检查状态 */
    private boolean state = false;

    /** 是否需要端口检查标识*/
    private boolean changeFlag = true;

    /** 启动时间 */
    private Long initTime = null;

    /** 需求倒计时的时间 单位 ms */
    private Long startTime = 60*1000L;

    @Value("${server.port}")
    private String port ;

    @Override
    public Health health() {
        log.info("{}------健康检查被调用!当前状态:{}",new Date(),this.state);

        //是否开启倒计时
        if (ObjectUtil.isNull(initTime)){
            //未初始化倒计时,验证端口状态
            log.info("http://localhost:{}/healthCheck",port);
            HttpResponse res = HttpRequest.post("http://localhost:"+port+"/healthCheck").execute();
            log.info("返回状态:{}",res.getStatus());
            if (HttpStatus.HTTP_OK == res.getStatus()){
                //开启倒计时
                initTime = System.currentTimeMillis();
                log.info("开启倒计时:{}", initTime);
            }
        }else {
            if (System.currentTimeMillis() - initTime > startTime ){
                if(!state){
                    //到达开启时间,服务开启
                    state = true;
                    log.info("开启服务:{}", System.currentTimeMillis());
                }
            }else {
                log.info("等待服务开启,剩余时间:{}ms",startTime -( System.currentTimeMillis()-initTime));
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
