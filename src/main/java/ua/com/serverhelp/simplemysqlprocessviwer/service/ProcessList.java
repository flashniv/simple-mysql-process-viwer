package ua.com.serverhelp.simplemysqlprocessviwer.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ua.com.serverhelp.simplemysqlprocessviwer.driver.MariaDBDriver;
import ua.com.serverhelp.simplemysqlprocessviwer.entity.Process;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class ProcessList {
    @Autowired
    private MariaDBDriver mariaDBDriver;
    @Value("${housekeeper.delay}")
    private Long housekeeperDelay;

    @Getter
    private final Map<Long, Process> map = new ConcurrentHashMap<>();

    public void processProcesses() {
        List<Process> processes = mariaDBDriver.getProcessList();
        processes.forEach(process -> {
            if (!map.containsKey(process.getId())) {
                process.setStart(Instant.now());
                map.put(process.getId(), process);
            }
        });
        List<Long> processesIds = processes.stream().map(Process::getId).collect(Collectors.toList());
        map.entrySet().stream()
                .filter(longProcessEntry -> longProcessEntry.getValue().getStop() == null)
                .filter(longProcessEntry -> !processesIds.contains(longProcessEntry.getKey()))
                .forEach(longProcessEntry -> longProcessEntry.getValue().setStop(Instant.now()));
    }

    public void housekeeper() {
        map.entrySet().removeIf(longProcessEntry -> longProcessEntry.getValue().getStop().isBefore(Instant.now().minusSeconds(housekeeperDelay)));
    }
}
