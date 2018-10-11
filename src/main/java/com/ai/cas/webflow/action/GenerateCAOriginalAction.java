package com.ai.cas.webflow.action;

import com.ai.cas.util.WebUtil;
import org.apereo.cas.web.flow.InitializeLoginAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.webflow.action.AbstractAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;
/**
 * Created with IntelliJ IDEA.
 * Description:追加随机码，可避免暴力攻击
 * Author: zhangfengzhou
 * Date: 2018-10-11
 * Time: 15:09
 */
public class GenerateCAOriginalAction extends AbstractAction {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitializeLoginAction.class);

    @Override
    protected Event doExecute(final RequestContext context) {
        String originalData = WebUtil.generateRandomNum();
        System.out.println(originalData+"-------------------");
        LOGGER.debug("Generated original data {}", originalData);
        WebUtil.putOriginalData(context, originalData);
        WebUtil.putAppId(context, context.getExternalContext().getRequestParameterMap().get("appId"));
        return  new Event(this, "generated");
    }
}