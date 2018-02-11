package com.zxing.sell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 商品（包含类目）
 * Created by ZXing at 2018/2/11
 * QQ:1490570560
 */
@Data
@AllArgsConstructor
public class ProductVO {
    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    private List<ProductInfoVO> foods;
}
