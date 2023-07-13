package com.magistrados.models;

public class Time {
    private Long id;
    private String nomeTime;

    public Time(){
    }

    public Time(Long id, String nomeTime){
        this.id = id;
        this.nomeTime = nomeTime;
    }

    public boolean isCreated() {
        return this.id != null && this.id != 0;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return this.id;
    }

    public void setNomeTime(String nomeTime){
        this.nomeTime = nomeTime;
    }

    public String getNomeTime(){
        return this.nomeTime;
    }
}
