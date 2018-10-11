package com.ai.cas.mvc.service;

import com.ai.cas.domain.SysCaUser;
import com.ai.cas.domain.UserCa;
import com.ai.cas.domain.UserCaRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${user.query.appUserSql}")
    private String appUserSql;

    @Value("${user.query.appUsersSql}")
    private String appUsersSql;

    @Value("${user.query.userSql}")
    private String userSql;


    public List<UserCa> getUsersInfo(String caUser){
        return jdbcTemplate.query(appUsersSql,new Object[]{caUser}, new UserCaRowMapper());
    }
    public UserCa getUserInfo(String caUser, String appId){
        List<UserCa> results = jdbcTemplate.query(appUserSql, new Object[]{caUser,appId}, new RowMapperResultSetExtractor<>(new BeanPropertyRowMapper<>(UserCa.class), 1));
        if(results.size()>0) {
            return DataAccessUtils.requiredSingleResult(results);
        }
        return null;
    }

    public SysCaUser getUserByUsername(String caUser){
        List<SysCaUser> results = jdbcTemplate.query(userSql, new Object[]{caUser}, new RowMapperResultSetExtractor<>(new BeanPropertyRowMapper<>(SysCaUser.class), 1));
        if(results.size()>0) {
            return DataAccessUtils.requiredSingleResult(results);
        }
        return null;
    }
}
