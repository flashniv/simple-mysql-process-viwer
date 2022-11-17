package ua.com.serverhelp.simplemysqlprocessviwer.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ua.com.serverhelp.simplemysqlprocessviwer.driver.MariaDBDriver;
import ua.com.serverhelp.simplemysqlprocessviwer.entity.Process;
import ua.com.serverhelp.simplemysqlprocessviwer.service.ProcessList;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class ProcessesRestTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MariaDBDriver mariaDBDriver;
    @Autowired
    private ProcessList processList;

    @BeforeEach
    void setUp() {
        processList.getMap().clear();
        Process process = new Process();
        process.setId(1L);
        Process process1 = new Process();
        process1.setId(1L);
        Process process2 = new Process();
        process2.setId(2L);
        Process process3 = new Process();
        process3.setId(2L);
        Mockito.when(mariaDBDriver.getProcessList())
                .thenReturn(List.of(process))
                .thenReturn(List.of(process1, process2))
                .thenReturn(List.of(process3))
                .thenReturn(List.of());
        processList.processProcesses();
        processList.processProcesses();
        processList.processProcesses();
        processList.processProcesses();
    }

    //{"db":null,"start":"2022-11-17T14:06:12.420940Z","stop":"2022-11-17T14:06:12.421139Z","progress":null,"info":null,"id":1,"state":null,"host":null,"time":null,"user":null,"command":null},
    //{"db":null,"start":"2022-11-17T14:06:12.421087Z","stop":"2022-11-17T14:06:12.421187Z","progress":null,"info":null,"id":2,"state":null,"host":null,"time":null,"user":null,"command":null}
    @Test
    @WithMockUser(username = "user")
    void getAllProcesses() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/processesRest/")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].stop").isString());
    }
}
