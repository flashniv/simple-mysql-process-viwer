package ua.com.serverhelp.simplemysqlprocessviwer.driver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.com.serverhelp.simplemysqlprocessviwer.entity.Process;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MariaDBDriverTest {
    @Autowired
    private MariaDBDriver mariaDBDriver;

    @Test
    void getProcessList() {
        List<Process> processes = mariaDBDriver.getProcessList();
    }
}