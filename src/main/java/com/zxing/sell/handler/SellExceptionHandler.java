package com.zxing.sell.handler;

import com.zxing.sell.VO.ResultVO;
import com.zxing.sell.config.ProjectUrlProperties;
import com.zxing.sell.exception.ResponseBankException;
import com.zxing.sell.exception.SellAuthorizeException;
import com.zxing.sell.exception.SellException;
import com.zxing.sell.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by ZXing at 2018/2/22
 * QQ:1490570560
 */
@ControllerAdvice
public class SellExceptionHandler {

    @Autowired
    private ProjectUrlProperties projectUrlProperties;

    @ExceptionHandler(value = SellAuthorizeException.class)
    public ModelAndView handleAuthorizeException() {
        //TODO 需要公司资质
        /*return new ModelAndView("redirect:"
        .concat(projectUrlProperties.getOpenAuthorizeUrl())
        .concat("/sell/wechat/qrAuthorize?returnUrl=")
        .concat(projectUrlProperties.getSellUrl())
        .concat("/sell/seller/login")
        );*/
        return new ModelAndView("index");
    }

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handleSellException(SellException e) {
        return ResultVOUtil.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = ResponseBankException.class)
    @ResponseStatus(code = HttpStatus.FORBIDDEN) //修改响应码为403
    public void changeResponseStatus(){
        //修改响应码为403
    }
}
