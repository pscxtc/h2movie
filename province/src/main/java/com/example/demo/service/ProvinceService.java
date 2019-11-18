package com.example.demo.service;

import com.example.demo.entity.Province;

import java.util.List;

/**
 * @Description:
 * @version: v1.0.0
 * @author: chenxu <br/><b>e-mail:</b><br/>pscxtc@163.com
 * @date: 2019/10/15 0015 下午 1:29
 * <p>
 * Modification History:
 * Date           Author          Version            Description
 * ---------------------------------------------------------*
 * 2019/10/15 0015      chenxu                     v1.0.0               初始创建
 */
public interface ProvinceService {
    List<Province> getByProvinceId(Long provinceId);

    List<Province> getAll();

    String saveProvince(String pName);
}
