package com.example.demo.controller;

import com.example.demo.entity.City;
import com.example.demo.service.CityService;
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
@RequestMapping("/c")
public class CityRestController {

    @Resource
    private CityService cityService;

    @GetMapping("/cities")
    public List<City> cities () {
        return cityService.getAll();
    }

    @GetMapping("/city/{provinceId}")
    public List<City> city(@PathVariable long provinceId) {
        return cityService.getByProvinceId(provinceId);
    }

    @GetMapping("/add/{cName}/{provinceId}")
    public String add (@PathVariable String cName,@PathVariable long provinceId) {
        return cityService.saveCity(cName,provinceId);
    }


}
