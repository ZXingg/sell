package com.zxing.sell.model.mapper;

import com.zxing.sell.model.ProductCategory;
import org.apache.ibatis.annotations.*;
import java.util.Map;

/**
 * 测试使用mybatis操作数据库
 * 需要在启动类配置mapper扫描路径 或 为每个map添加@Mapper注解
 * Created by ZXing at 2018/2/23
 * QQ:1490570560
 */
//@Mapper
public interface ProductCategoryMapper {
    //关键字可写为category_name（随意）
    @Insert("insert into product_category(category_name,category_type) values(#{categoryName,jdbcType=VARCHAR},#{categoryType,jdbcType=INTEGER})")
    int insertByMap(Map<String, Object> map);
    //关键字段对应ProductCategory属性
    @Insert("insert into product_category(category_name,category_type) values(#{categoryName,jdbcType=VARCHAR},#{categoryType,jdbcType=INTEGER})")
    int insertByObject(ProductCategory productCategory);

    @Select("select * from product_category where category_type = #{categoryType}")
    @Results({
            @Result(column = "category_id",property = "categoryId"),
            @Result(column = "category_name",property = "categoryName"),
            @Result(column = "category_type",property = "categoryType"),
    })
    ProductCategory findByType(Integer categoryType);

    @Update("update product_category set category_name = #{categoryName} where category_type = #{categoryType}")
    int updateByType(@Param("categoryName")String categoryName,@Param("categoryType") Integer categoryType);

    @Update("update product_category set category_name = #{categoryName} where category_type = #{categoryType}")
    int updateByObject(ProductCategory productCategory);

    @Delete("delete from product_category where category_type = #{categoryType} ")
    int deleteByType(Integer categoryType);

    //使用xml配置  resource/mapper/*.xml
    ProductCategory selectByCategoryType(Integer categoryType);
}
