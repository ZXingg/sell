package com.zxing.sell.utils;

import com.zxing.sell.VO.ResultVO;

/**
 * Created by ZXing at 2018/2/11
 * QQ:1490570560
 */
public class ResultVOUtil {
    public static ResultVO success() {
        return new ResultVO(0, "成功", null);
    }

    public static ResultVO success(Object object) {
        return new ResultVO(0, "成功", object);
    }

    public static ResultVO error(Integer code, String msg) {
        return new ResultVO(code, msg, null);
    }

}
