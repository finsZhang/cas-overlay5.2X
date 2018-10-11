/**
 * Copyright (c) 2005-2009 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * $Id: WebUtil.java,v 1.1 2014/04/23 14:14:33 hexg Exp $
 */
package com.ai.cas.util;


import org.springframework.webflow.execution.RequestContext;


/**
 * Created with IntelliJ IDEA.
 * Description:额外的字段注入校验
 * Author: zhangfengzhou
 * Date: 2018-10-11
 * Time: 15:09
 */
public class WebUtil {

    public static String generateRandomNum() {
        /**************************
         * 第二步 服务端产生认证原文   *
         **************************/
        String num = "1234567890abcdefghijklmnopqrstopqrstuvwxyz";
        int size = 6;
        char[] charArray = num.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < size; i++) {
            sb .append(charArray[((int) (Math.random() * 10000) % charArray.length)]);
        }
        return sb.toString();
    }

    public static void putOriginalData(RequestContext context, String originalData) {
        context.getFlowScope().put("originalData",originalData);
    }

    public static void putAppId(RequestContext context, String appId) {
        context.getFlowScope().put("appId",appId);
    }

    public static String getOriginalDataFromFlowScope(RequestContext context){
        String originalData = (String)context.getFlowScope().remove("originalData");
        return originalData != null?originalData:"";
    }
    public static String getOriginalDataFromRequest(RequestContext context){
        return context.getRequestParameters().get("originalData");
    }

}
