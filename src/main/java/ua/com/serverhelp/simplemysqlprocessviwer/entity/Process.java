package ua.com.serverhelp.simplemysqlprocessviwer.entity;

import lombok.Data;

import java.time.Instant;

@Data
public class Process {
    private Long Id;
    private String User;
    private String Host;
    private String db;
    private String Command;
    private Integer Time;
    private String State;
    private String Info;
    private Double Progress;
    private Instant start = null;
    private Instant stop = null;
}
