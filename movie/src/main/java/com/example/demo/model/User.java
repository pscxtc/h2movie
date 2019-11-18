package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Description:
 * @version: v1.0.0
 * @author: chenxu <br/><b>e-mail:</b><br/>pscxtc@163.com
 * @date: 2019/9/3 0003 下午 2:15
 * <p>
 * Modification History:
 * Date           Author          Version            Description
 * ---------------------------------------------------------*
 * 2019/9/3 0003      chenxu                     v1.0.0               初始创建
 */
@Data
@NoArgsConstructor
public class User {

    private Long id;
    private String userName;
    private String name;
    private Integer age;
    private BigDecimal balance;
    private String ip;
}
