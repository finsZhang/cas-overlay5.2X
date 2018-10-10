package com.ai.cas.domain;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by fins on 2016/1/27.
 */
public class SysCaUserRowMapper<SysCaUser> implements RowMapper {

    public Object mapRow(ResultSet rs, int index) throws SQLException {
       /* SysCaUser user= new SysCaUser();
        user.setCauser(rs.getString("causer"));
        user.setPassword(rs.getString("password"));
        user.setId(rs.getInt("id"));*/
        return null;
    }

}
