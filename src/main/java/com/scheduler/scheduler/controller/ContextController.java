package com.scheduler.scheduler.controller;

import com.scheduler.scheduler.config.ClientContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ContextController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // this function will be called by scheduler
    public void setDataSource() {
        ClientContextHolder.setContext(ClientContextHolder.CLIENT.CLIENT1);
        List<Map<String, Object>> results = jdbcTemplate.queryForList("SELECT * FROM READ");
        results.stream().forEach(row -> {
            row.entrySet().stream().forEach(values -> {
                System.out.println("values : " + values);
            });
        });
        ClientContextHolder.clearContext();
    }
}
