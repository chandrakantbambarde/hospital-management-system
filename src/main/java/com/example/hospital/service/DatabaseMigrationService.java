package com.example.hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
public class DatabaseMigrationService {

    @Autowired
    private JdbcTemplate h2JdbcTemplate;

    @Autowired
    private JdbcTemplate mysqlJdbcTemplate;

    @Transactional
    public void migrateData() {
        // Get all table names from H2
        List<String> tables = h2JdbcTemplate.queryForList(
                "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA='PUBLIC'",
                String.class
        );

        for (String table : tables) {
            // Copy data from H2 to MySQL
            List<Map<String, Object>> data = h2JdbcTemplate.queryForList(
                    "SELECT * FROM " + table
            );

            for (Map<String, Object> row : data) {
                // Build and execute insert statements for MySQL
                // (This would need to be implemented based on your schema)
            }
        }
    }
}