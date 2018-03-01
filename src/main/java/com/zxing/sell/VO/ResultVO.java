package com.zxing.sell.VO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * http请求返回的最外层结果
 * Created by ZXing at 2018/2/11
 * QQ:1490570560
 */
@Data
@AllArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)  //配置文件做全局配置
public class ResultVO <T> implements Serializable{
    private Integer code;
    private String msg;
    private T data;
}
