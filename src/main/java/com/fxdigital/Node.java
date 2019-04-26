package com.fxdigital;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * 节点信息查询
 *
 */
public class Node {
    private static Logger logger= LoggerFactory.getLogger(Node.class);
    JdbcTemplate jdbcTemplate;
    public boolean init(DataSource dataSource){
        jdbcTemplate=new JdbcTemplate(dataSource);
        return true;
    }

}