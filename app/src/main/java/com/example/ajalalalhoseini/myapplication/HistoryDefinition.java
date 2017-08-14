package com.example.ajalalalhoseini.myapplication;

public class HistoryDefinition {
    private int id;
    private String statement;
    private String equality;
    private String time;
    HistoryDefinition() {
    }

    HistoryDefinition(int id, String statement, String equality) {
        this.id = id;
        this.statement = statement;
        this.equality = equality;
    }
    HistoryDefinition(String time, String statement, String equality) {
        this.time = time;
        this.statement = statement;
        this.equality = equality;
    }
    HistoryDefinition(String statement, String equality) {
        this.statement = statement;
        this.equality = equality;
    }
    HistoryDefinition(String statement) {
        this.statement = statement;
    }

    String getStatement() {
        return statement;
    }
    void setStatement(String statement) {
        this.statement = statement;
    }
    int getId() {
        return id;
    }
    void setId(int id) {
        this.id = id;
    }
    String getEquality() {
        return equality;
    }
    void setEquality(String equality) {
        this.equality = equality;
    }
}
