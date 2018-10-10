package com.ai.cas.handler;

import com.ai.cas.domain.SysCaUser;
import com.ai.cas.domain.UserCa;
import org.apereo.cas.authentication.HandlerResult;
import org.apereo.cas.authentication.PreventedException;
import org.apereo.cas.authentication.UsernamePasswordCredential;
import org.apereo.cas.authentication.handler.support.AbstractUsernamePasswordAuthenticationHandler;
import org.apereo.cas.authentication.principal.PrincipalFactory;
import org.apereo.cas.services.ServicesManager;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.security.auth.login.FailedLoginException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Login extends AbstractUsernamePasswordAuthenticationHandler {
    private static final org.slf4j.Logger LOGGER =LoggerFactory.getLogger(Login.class);

    public Login(String name, ServicesManager servicesManager, PrincipalFactory principalFactory,
                 Integer order) {
        super(name, servicesManager, principalFactory, order);
        // TODO Auto-generated constructor stub
    }

    //  private final String sql="select password from sec_user where username=?";
    private String sql="select * from sys_ca_user where CAUSER=?";
    private String sqlUserCa="SELECT * FROM USER_CA where CAUSER=?";


    @Override
    protected HandlerResult authenticateUsernamePasswordInternal(UsernamePasswordCredential transformedCredential,
                                                                 String originalPassword) throws GeneralSecurityException, PreventedException {
        // TODO Auto-generated method stub
        DriverManagerDataSource d=new DriverManagerDataSource();
        d.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        d.setUrl("jdbc:oracle:thin:@43.251.37.15:2209:zhyldb");
        d.setUsername("zw_commonframe");
        d.setPassword("zw_commonframe");
        JdbcTemplate template=new JdbcTemplate();
        template.setDataSource(d);
        LOGGER.debug("test...............");



        String username=transformedCredential.getUsername();
        String pd=transformedCredential.getPassword();
//      //查询数据库加密的的密码
        SysCaUser user=template.queryForObject(sql, new Object[]{username}, new BeanPropertyRowMapper<SysCaUser>(SysCaUser.class));


//      if(sqlpd.equals(pd)){
//          return createHandlerResult(transformedCredential, principalFactory.createPrincipal(username, null), null);
//      }
        if(user==null){
            throw new FailedLoginException("没有该用户");
        }

        List<UserCa> userCas = template.query(sqlUserCa, new Object[]{username}, new BeanPropertyRowMapper<UserCa>(UserCa.class));

        //返回多属性
        Map<String, Object> map=new HashMap<>();
        UserCa userCa;
        for(int i =0;i<userCas.size();i++){
            userCa = userCas.get(i);
            map.put(userCa.getAppid(),userCa.getUsername());
        }

        LOGGER.info("++++++++++++++++++++zjzjzjz",map);


        return createHandlerResult(transformedCredential, principalFactory.createPrincipal(username, map), null);

        //throw new FailedLoginException("Sorry, login attemp failed.");
//      return  null;
    }

}
