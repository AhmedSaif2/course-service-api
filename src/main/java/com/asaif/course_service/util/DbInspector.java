package com.asaif.course_service.util;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DbInspector {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void inspectDb() {
        System.out.println("📋 Tables in HSQLDB:");
        List<Map<String, Object>> tables = jdbcTemplate.queryForList(
                "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.SYSTEM_TABLES WHERE TABLE_TYPE='TABLE'"
        );
        tables.forEach(row -> {
            System.out.println("🔹 " + row.get("TABLE_NAME"));
            List<Map<String, Object>> data = jdbcTemplate.queryForList("SELECT * FROM " + row.get("TABLE_NAME"));
            data.forEach(r -> {
                System.out.println("  🔸 " + r);
            });
        });


    }
}