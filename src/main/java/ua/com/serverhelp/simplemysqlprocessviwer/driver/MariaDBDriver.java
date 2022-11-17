package ua.com.serverhelp.simplemysqlprocessviwer.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ua.com.serverhelp.simplemysqlprocessviwer.entity.Process;

import java.util.List;

@Service
public class MariaDBDriver {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Process> getProcessList() {
        return jdbcTemplate.query("select * from INFORMATION_SCHEMA.PROCESSLIST", new BeanPropertyRowMapper<>(Process.class));
    }
}
