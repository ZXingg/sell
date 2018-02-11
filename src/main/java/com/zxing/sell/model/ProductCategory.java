package com.zxing.sell.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

/**
 *商品类目
 * Created by ZXing at 2018/2/10
 * QQ:1490570560
 */
@Entity
@DynamicUpdate //动态更新时间
@Data //省去get set toString 等方法
@NoArgsConstructor
public class ProductCategory {

    @Id
    @GeneratedValue
    /**
     * 类目id
     */
    private Integer categoryId;
    /**
     * 名称
     */
    private String categoryName;
    /**
     * 类型
     */
    private Integer categoryType;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
