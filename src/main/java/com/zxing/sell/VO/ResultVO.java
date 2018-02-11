package com.zxing.sell.VO;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * http请求返回的最外层结果
 * Created by ZXing at 2018/2/11
 * QQ:1490570560
 */
@Data
@AllArgsConstructor
public class ResultVO <T>{
    private Integer code;
    private String msg;
    private T data;
}
