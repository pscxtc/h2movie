package com.example.demo.mapper;

import com.example.demo.entity.Province;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;

/**
 * @Description:
 * @version: v1.0.0
 * @author: chenxu <br/><b>e-mail:</b><br/>pscxtc@163.com
 * @date: 2019/10/15 0015 下午 1:27
 * <p>
 * Modification History:
 * Date           Author          Version            Description
 * ---------------------------------------------------------*
 * 2019/10/15 0015      chenxu                     v1.0.0               初始创建
 */
@Mapper
public interface ProvinceMapper {

    List<Province> findByProvinceId(Long provinceId);

    List<Province> findAll();

    @Insert("INSERT INTO province(name) VALUES(#{name})")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(Province province);
}
