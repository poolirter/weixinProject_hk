package com.example.hk_ht.Controller;


import com.example.hk_ht.Service.Ex.AddressCountLimitException;
import com.example.hk_ht.Service.Ex.InsertException;
import com.example.hk_ht.Service.Ex.ServiceException;
import com.example.hk_ht.Util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.Enumeration;

public class BaseController {
    public static final Integer ok = 200;

    @ExceptionHandler(ServiceException.class)
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<>(e);
        if (e instanceof InsertException) {
            result.setState(4000);
            result.setMessage("插入数据发生异常");
        } else if (e instanceof AddressCountLimitException) {
            result.setState(4001);
            result.setMessage("收货地址超出上限");
        }
        return result;

    }

    protected final Integer getUidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("uid").toString());
    }
    protected final String getUsernameFromSession(HttpSession session) {
        return session.getAttribute("username").toString();
    }
}
