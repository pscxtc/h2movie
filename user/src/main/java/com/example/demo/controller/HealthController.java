package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

/**
 * 自定义健康检查 控制层
 *
 * @author chenxu
 * @since 2019年11月19日
 */
@RestController
public class HealthController {

//    @Autowired
//    MyHealthChecker myHealthChecker;
//
//    /**
//     * 手动改变健康检查状态
//     *
//     * 用于测试
//     *
//     * @param state
//     * @return {@link String}
//     * @since 2019/11/19
//     * 版本历史:
//     * Date         Author         Description
//     *---------------------------------------------------------*
//     * 2019/11/19    chenxu          初始创建
//     */
//    @RequestMapping("/state")
//    public String state(@RequestParam("state") Boolean state) {
//        myHealthChecker.setState(state);
//        return state.toString();
//    }
//
//    /**
//     * 用于健康检查 查询项目是否可用
//     * @return {@link String}
//     * @since 2019/11/19
//     * 版本历史:
//     * Date         Author         Description
//     *---------------------------------------------------------*
//     * 2019/11/19    chenxu          初始创建
//     */
//    @RequestMapping("/healthCheck")
//    public Long healthCheck(){
//        return System.currentTimeMillis();
//    }

}
