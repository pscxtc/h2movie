package com.example.demo.controller;

import com.example.demo.entity.Province;
import com.example.demo.service.ProvinceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @version: v1.0.0
 * @author: chenxu <br/><b>e-mail:</b><br/>pscxtc@163.com
 * @date: 2019/10/15 0015 下午 1:30
 * <p>
 * Modification History:
 * Date           Author          Version            Description
 * ---------------------------------------------------------*
 * 2019/10/15 0015      chenxu                     v1.0.0               初始创建
 */
@RestController
@RequestMapping("/p")
public class ProvinceRestController {

    @Resource
    private ProvinceService provinceService;

    @GetMapping("/provinces")
    public List<Province> cities () {
        return provinceService.getAll();
    }

    @GetMapping("/province/{provinceId}")
    public List<Province> city(@PathVariable long provinceId) {
        return provinceService.getByProvinceId(provinceId);
    }

    @GetMapping("/add/{pName}")
    public String add (@PathVariable String pName) {
        return provinceService.saveProvince(pName);
    }
}
