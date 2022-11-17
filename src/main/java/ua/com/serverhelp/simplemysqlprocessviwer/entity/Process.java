package ua.com.serverhelp.simplemysqlprocessviwer.entity;

import lombok.Data;

import java.time.Instant;

@Data
public class Process {
    private Long ID;
    private String USER;
    private String HOST;
    private String DB;
    private String COMMAND;
    private Integer TIME;
    private String STATE;
    private String INFO;
    private Double TIME_MS;
    private Integer STAGE;
    private Integer MAX_STAGE;
    private Double PROGRESS;
    private Long MEMORY_USED;
    private Long MAX_MEMORY_USED;
    private Integer EXAMINED_ROWS;
    private Long QUERY_ID;
    private byte[] INFO_BINARY;
    private Long TID;
    private Instant start = null;
    private Instant stop = null;
}
