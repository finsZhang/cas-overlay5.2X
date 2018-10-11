package com.ai.cas.exception;

import javax.security.auth.login.LoginException;

/**
 * Created with IntelliJ IDEA.
 * Description:自定义异常无CA配置异常
 * Author: zhangfengzhou
 * Date: 2018-10-11
 * Time: 15:09
 */
public class NoCAConfigException extends LoginException {

    public NoCAConfigException() {
        super();
    }

    public NoCAConfigException(String msg) {
        super(msg);
    }
}
