package ua.com.serverhelp.simplemysqlprocessviwer.driver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ua.com.serverhelp.simplemysqlprocessviwer.entity.Process;
import ua.com.serverhelp.simplemysqlprocessviwer.service.Cron;

import java.util.List;

@SpringBootTest
class MariaDBDriverTest {
    @Autowired
    private MariaDBDriver mariaDBDriver;
    @MockBean
    private Cron cron;

    @Test
    void getProcessList() {
        List<Process> processes = mariaDBDriver.getProcessList();
    }
}