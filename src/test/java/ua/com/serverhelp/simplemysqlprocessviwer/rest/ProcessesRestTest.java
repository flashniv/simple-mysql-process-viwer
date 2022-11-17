package ua.com.serverhelp.simplemysqlprocessviwer.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

import java.time.Instant;

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
        Process process1 = new Process();
        process1.setQUERY_ID(1L);
        process1.setStart(Instant.now().minusSeconds(3600));
        process1.setStop(Instant.now());
        Process process2 = new Process();
        process2.setStart(Instant.now().minusSeconds(600));
        process2.setStop(Instant.now());
        processList.getMap().put(1L, process1);
        processList.getMap().put(2L, process2);
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
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].query_ID").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].stop").isString());
    }
}
