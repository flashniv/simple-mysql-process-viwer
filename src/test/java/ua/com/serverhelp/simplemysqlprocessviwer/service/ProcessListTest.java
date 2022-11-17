package ua.com.serverhelp.simplemysqlprocessviwer.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ua.com.serverhelp.simplemysqlprocessviwer.driver.MariaDBDriver;
import ua.com.serverhelp.simplemysqlprocessviwer.entity.Process;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProcessListTest {
    @MockBean
    private MariaDBDriver mariaDBDriver;
    @Autowired
    private ProcessList processList;

    @BeforeEach
    void setUp() {
        Process process=new Process();
        process.setId(1L);
        Process process1=new Process();
        process1.setId(1L);
        Process process2=new Process();
        process2.setId(2L);
        Process process3=new Process();
        process3.setId(2L);
        Mockito.when(mariaDBDriver.getProcessList())
                .thenReturn(List.of(process))
                .thenReturn(List.of(process1,process2))
                .thenReturn(List.of(process3))
                .thenReturn(List.of());
    }

    @Test
    void processProcesses() throws Exception {
        processList.processProcesses();
        Assertions.assertEquals(1, processList.getMap().size());
        Assertions.assertNotNull(processList.getMap().get(1L).getStart());
        Assertions.assertNull(processList.getMap().get(1L).getStop());
        Thread.sleep(10);
        processList.processProcesses();
        Assertions.assertEquals(2, processList.getMap().size());
        Assertions.assertNotNull(processList.getMap().get(1L).getStart());
        Assertions.assertNull(processList.getMap().get(1L).getStop());
        Assertions.assertNotNull(processList.getMap().get(2L).getStart());
        Assertions.assertNull(processList.getMap().get(2L).getStop());
        Thread.sleep(10);
        processList.processProcesses();
        Assertions.assertEquals(2, processList.getMap().size());
        Assertions.assertNotNull(processList.getMap().get(1L).getStart());
        Assertions.assertNotNull(processList.getMap().get(1L).getStop());
        Assertions.assertNotNull(processList.getMap().get(2L).getStart());
        Assertions.assertNull(processList.getMap().get(2L).getStop());
        Thread.sleep(10);
        processList.processProcesses();
        Assertions.assertEquals(2, processList.getMap().size());
        Assertions.assertNotNull(processList.getMap().get(1L).getStart());
        Assertions.assertNotNull(processList.getMap().get(1L).getStop());
        Assertions.assertNotNull(processList.getMap().get(2L).getStart());
        Assertions.assertNotNull(processList.getMap().get(2L).getStop());
    }

    @Test
    void housekeeper() {
        processList.processProcesses();
        processList.processProcesses();
        processList.processProcesses();
        processList.processProcesses();

        Map<Long,Process> processMap=processList.getMap();
        Process process=processMap.get(1L);
        process.setStop(Instant.MIN);
        processList.housekeeper();
        Assertions.assertEquals(1, processMap.size());
    }
}