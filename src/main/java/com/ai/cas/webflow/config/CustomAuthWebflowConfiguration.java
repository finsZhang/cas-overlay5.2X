package com.ai.cas.webflow.config;

import com.ai.cas.webflow.action.GenerateCAOriginalAction;
import com.ai.cas.webflow.action.ValidateCAOriginalAction;
import com.ai.cas.webflow.authentication.CustomWebflowConfigurer;
import com.ai.cas.stat.DruidMonitorConfig;
import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.web.flow.CasWebflowConfigurer;
import org.apereo.cas.web.flow.config.CasWebflowContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;

/**
 * Created with IntelliJ IDEA.
 * Description:修改默认WebFlow配置
 * Author: zhangfengzhou
 * Date: 2018-10-11
 * Time: 15:09
 */

@Import({DataSourceAutoConfiguration.class,DruidMonitorConfig.class})
@Configuration("CustomAuthWebflowConfiguration")
@EnableConfigurationProperties(CasConfigurationProperties.class)
@AutoConfigureBefore(value = CasWebflowContextConfiguration.class)
public class CustomAuthWebflowConfiguration {

    @Autowired
    private CasConfigurationProperties casProperties;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    @Qualifier("loginFlowRegistry")
    private FlowDefinitionRegistry loginFlowRegistry;

    @Autowired
    @Qualifier("builder")
    private FlowBuilderServices flowBuilderServices;

    @ConditionalOnMissingBean(name = "generateCAOriginalAction")
    @Bean(name = "generateCAOriginalAction")
    public GenerateCAOriginalAction generateCAOriginalAction() {
        final GenerateCAOriginalAction generateCAOriginalAction = new GenerateCAOriginalAction();
        return generateCAOriginalAction;
    }

    @ConditionalOnMissingBean(name = "validateCAOriginalAction")
    @Bean(name = "validateCAOriginalAction")
    public ValidateCAOriginalAction validateCAOriginalAction() {
        final ValidateCAOriginalAction validateCAOriginalAction = new ValidateCAOriginalAction();
        return validateCAOriginalAction;
    }
    /**
     * 注册我们的表单到服务器上
     * @return
     */
    @Bean
    public CasWebflowConfigurer customWebflowConfigurer() {
        //实例化自定义的表单配置类
        final CustomWebflowConfigurer c = new CustomWebflowConfigurer(flowBuilderServices, loginFlowRegistry,
                applicationContext, casProperties);
        //初期化
        c.initialize();
        //返回对象
        return c;
    }



}
