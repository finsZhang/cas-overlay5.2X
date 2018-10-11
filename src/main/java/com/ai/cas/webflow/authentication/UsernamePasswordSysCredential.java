package com.ai.cas.webflow.authentication;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apereo.cas.authentication.UsernamePasswordCredential;
/**
 * Created with IntelliJ IDEA.
 * Description:添加额外绑定字段
 * Author: zhangfengzhou
 * Date: 2018-10-11
 * Time: 15:15
 */
public class UsernamePasswordSysCredential extends UsernamePasswordCredential {
    private String appId;
    private String originalData;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getOriginalData() {
        return originalData;
    }

    public void setOriginalData(String originalData) {
        this.originalData = originalData;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(this.appId)
                .append(this.originalData)
                .toHashCode();
    }
}
