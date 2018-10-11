package com.ai.cas.webflow.config;

import com.ai.cas.webflow.handler.LoginAuthenticationHandler;
import org.apereo.cas.authentication.AuthenticationEventExecutionPlan;
import org.apereo.cas.authentication.AuthenticationEventExecutionPlanConfigurer;
import org.apereo.cas.authentication.AuthenticationHandler;
import org.apereo.cas.authentication.principal.DefaultPrincipalFactory;
import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.services.ServicesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * Created with IntelliJ IDEA.
 * Description:修改默认登录校验Handler
 * Author: zhangfengzhou
 * Date: 2018-10-11
 * Time: 15:09
 */
@Configuration("CustomAuthenticationConfiguration")
@EnableConfigurationProperties(CasConfigurationProperties.class)
public class CustomAuthenticationConfiguration implements AuthenticationEventExecutionPlanConfigurer {

    @Autowired
    private CasConfigurationProperties casProperties;

    @Autowired
    @Qualifier("servicesManager")
    private ServicesManager servicesManager;

    @Bean
    public AuthenticationHandler myAuthenticationHandler() {
        final LoginAuthenticationHandler handler = new LoginAuthenticationHandler(LoginAuthenticationHandler.class.getSimpleName(), servicesManager, new DefaultPrincipalFactory(), 10);
        return handler;
    }

    @Override
    public void configureAuthenticationExecutionPlan(AuthenticationEventExecutionPlan plan) {
        plan.registerAuthenticationHandler(myAuthenticationHandler());
    }

}
