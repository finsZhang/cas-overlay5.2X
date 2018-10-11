package com.ai.cas.webflow.action;

import com.ai.cas.util.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.webflow.action.AbstractAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

/**
 * Created with IntelliJ IDEA.
 * Description:校验随机码，可避免暴力攻击
 * Author: zhangfengzhou
 * Date: 2018-10-11
 * Time: 15:09
 */
public class ValidateCAOriginalAction  extends AbstractAction {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected Event doExecute(final RequestContext context){
        String authoritativeOriginalData = WebUtil.getOriginalDataFromFlowScope(context);
        String providedOriginalData = WebUtil.getOriginalDataFromRequest(context);
        if(!authoritativeOriginalData.equals(providedOriginalData)) {
            this.logger.warn("Invalid CA original data {}", providedOriginalData);
            return this.newEvent("authenticationFailure");
        } else {
            return this.newEvent("success");
        }
    }

    private Event newEvent(String id) {
        return new Event(this, id);
    }
}
