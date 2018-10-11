package com.ai.cas.webflow.authentication;

import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.web.flow.CasWebflowConstants;
import org.apereo.cas.web.flow.configurer.AbstractCasWebflowConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.Flow;
import org.springframework.webflow.engine.ViewState;
import org.springframework.webflow.engine.builder.BinderConfiguration;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;

 /**
 * Created with IntelliJ IDEA.
 * Description:重新定义默认的web流程
 * Author: zhangfengzhou
 * Date: 2018-10-11
 * Time: 15:10
 */
public class CustomWebflowConfigurer extends AbstractCasWebflowConfigurer {

    public CustomWebflowConfigurer(FlowBuilderServices flowBuilderServices, FlowDefinitionRegistry loginFlowDefinitionRegistry, ApplicationContext applicationContext, CasConfigurationProperties casProperties) {
        super(flowBuilderServices, loginFlowDefinitionRegistry, applicationContext, casProperties);
    }

    @Override
    protected void doInitialize(){
        final Flow flow = getLoginFlow();
        bindCredential(flow);

        // ToDo 追加webflow
       /* ActionState generateCAOriginal = (ActionState)flow.getState("generateCAOriginal");
        generateCAOriginal.getActionList().forEach(action ->generateCAOriginal.getActionList().remove(action) );
        generateCAOriginal.getActionList().add(generateCAOriginalAction);

        ActionState validateCAOriginal = (ActionState)flow.getState("validateCAOriginal");
        validateCAOriginal.getActionList().forEach(action ->validateCAOriginal.getActionList().remove(action) );
        validateCAOriginal.getActionList().add(validateCAOriginalAction);*/
    }


    /**
     * 绑定输入信息
     *
     * @param flow
     */
    protected void bindCredential(Flow flow) {
        //重写绑定自定义credential
        createFlowVariable(flow, CasWebflowConstants.VAR_ID_CREDENTIAL, UsernamePasswordSysCredential.class);
        //登录页绑定新参数
        final ViewState state = (ViewState) flow.getState(CasWebflowConstants.STATE_ID_VIEW_LOGIN_FORM);
        final BinderConfiguration cfg = getViewStateBinderConfiguration(state);
        //由于用户名以及密码已经绑定，所以只需对新加系统参数绑定即可
        cfg.addBinding(new BinderConfiguration.Binding("appId", null, true));
        cfg.addBinding(new BinderConfiguration.Binding("originalData", null, true));
    }
}
