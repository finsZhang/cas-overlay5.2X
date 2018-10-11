package com.ai.cas.mvc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * Description:MVC注入配置
 * Author: zhangfengzhou
 * Date: 2018-10-11
 * Time: 15:09
 */
@ComponentScan(value={"com.ai.cas.mvc.controller", "com.ai.cas.mvc.service"})
@Configuration
public class SpringConfig {
}
