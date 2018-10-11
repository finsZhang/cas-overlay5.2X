package com.ai.cas.webflow.handler;

import com.ai.cas.domain.SysCaUser;
import com.ai.cas.domain.UserCa;
import com.ai.cas.exception.NoCAConfigException;
import com.ai.cas.mvc.service.LoginService;
import com.ai.cas.util.Encrypt;
import com.ai.cas.webflow.authentication.UsernamePasswordSysCredential;
import org.apereo.cas.authentication.Credential;
import org.apereo.cas.authentication.HandlerResult;
import org.apereo.cas.authentication.handler.support.AbstractPreAndPostProcessingAuthenticationHandler;
import org.apereo.cas.authentication.principal.PrincipalFactory;
import org.apereo.cas.services.ServicesManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.security.auth.login.AccountNotFoundException;
import javax.security.auth.login.FailedLoginException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Created with IntelliJ IDEA.
 * Description:权限校验handler
 * Author: zhangfengzhou
 * Date: 2018-10-11
 * Time: 15:09
 */
public class LoginAuthenticationHandler extends AbstractPreAndPostProcessingAuthenticationHandler {
    private static final Logger LOGGER =LoggerFactory.getLogger(LoginAuthenticationHandler.class);
    @Autowired
    private LoginService loginService;

    public LoginAuthenticationHandler(String name, ServicesManager servicesManager, PrincipalFactory principalFactory,
                 Integer order) {
        super(name, servicesManager, principalFactory, order);
    }

    @Override
    protected HandlerResult doAuthentication(Credential credential) throws GeneralSecurityException {
        UsernamePasswordSysCredential transformedCredential = (UsernamePasswordSysCredential) credential;

        transformedCredential.setUsername(transformedCredential.getUsername().toUpperCase());
        // TODO 校验用户名
        String caUser=transformedCredential.getUsername();
        SysCaUser sysCaUser = loginService.getUserByUsername(caUser);
        if(sysCaUser == null){
            LOGGER.error("账号不存在");
            throw new AccountNotFoundException();
        }

        // TODO 校验密码
        String oldPwdEnc = Encrypt.getDecodePsw(transformedCredential.getPassword());
        if(!oldPwdEnc.equals(sysCaUser.getPassword())){
            LOGGER.error("用户密码错误");
            throw new FailedLoginException();
        }
        // TODO 是否有对应系统的用户
        UserCa userCa = loginService.getUserInfo(transformedCredential.getUsername(), transformedCredential.getAppId());
        if(userCa==null){
            LOGGER.debug("数据库无对应CA用户信息:" + transformedCredential.getUsername());
            throw new NoCAConfigException();
        }

        // TODO 返回所有系统用户
        List<UserCa> userCas = loginService.getUsersInfo(transformedCredential.getUsername());

        //返回多属性
        Map<String, Object> map=new HashMap<>();
        userCas.forEach(userCaTmp ->
            map.put(userCaTmp.getAppid(),userCaTmp.getUsername())
        );
        return createHandlerResult(transformedCredential, principalFactory.createPrincipal(caUser, map), null);
    }


    @Override
    public boolean supports(Credential credential) {
        return credential instanceof UsernamePasswordSysCredential;
    }
}
