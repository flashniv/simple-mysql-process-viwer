package ua.com.serverhelp.simplemysqlprocessviwer.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.com.serverhelp.simplemysqlprocessviwer.entity.Process;
import ua.com.serverhelp.simplemysqlprocessviwer.service.ProcessList;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/processesRest")
@CrossOrigin
public class ProcessesRest {
    @Autowired
    private ProcessList processList;

    @RequestMapping(path = "/",method = RequestMethod.GET)
    public ResponseEntity<List<Process>> getAllProcesses(){
        return ResponseEntity.ok(new ArrayList<>(processList.getMap().values()));
    }
}
