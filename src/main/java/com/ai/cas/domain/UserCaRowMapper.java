package com.ai.cas.domain;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by fins on 2016/1/27.
 */
public class UserCaRowMapper implements RowMapper {

    public Object mapRow(ResultSet rs, int index) throws SQLException {
        UserCa user=new UserCa();
        user.setAppid(rs.getString("appid"));
        user.setCauser(rs.getString("causer"));
        user.setUsername(rs.getString("username"));
        user.setId(rs.getInt("id"));
        return user;
    }

}
