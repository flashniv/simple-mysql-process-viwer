package ua.com.serverhelp.simplemysqlprocessviwer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Cron {
    @Autowired
    private ProcessList processList;

    @Scheduled(fixedRate = 1000L)
    public void processProcesses() {
        try {
            processList.processProcesses();
            log.info("Cron::processProcesses");
        } catch (Exception e) {
            log.error("Error processing processes", e);
        }
    }

    @Scheduled(fixedRate = 1800000L, initialDelay = 1800000L)
    public void housekeeper() {
        processList.housekeeper();
        log.info("Cron::housekeeper");
    }
}
