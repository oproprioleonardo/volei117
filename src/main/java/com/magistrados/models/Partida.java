package com.magistrados.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Partida {
    private Long id;
    private LocalDateTime dateTime;
    private String local;
    private Time timeA, timeB;
    private Long idTimeA, idTimeB;
    private int quantidadeSets;
    private List<Set> sets = new ArrayList<>();
    private String vencedor;

    public Partida(){
    }

    public Partida(LocalDateTime dateTime, String local, Time timeA, Time timeB, int quantidadeSets, String vencedor){
        this.dateTime = dateTime;
        this.local = local;
        this.timeA = timeA;
        this.timeB = timeB;
        this.quantidadeSets = quantidadeSets;
        this.vencedor = vencedor;
    }

    public boolean isCreated() {
        return this.id != null && this.id > 0;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Set getSetByOrder(int order) {
        return this.sets.stream().filter(set -> set.getOrdem() == order).findFirst().orElse(null);
    }

    public void setDateTime(LocalDateTime dateTime){
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime(){
        return dateTime;
    }

    public void setLocal(String local){
        this.local = local;
    }

    public String getLocal(){
        return local;
    }

    public Long getIdTimeA() {
        return idTimeA;
    }

    public void setIdTimeA(Long idTimeA) {
        this.idTimeA = idTimeA;
    }

    public Long getIdTimeB() {
        return idTimeB;
    }

    public void setIdTimeB(Long idTimeB) {
        this.idTimeB = idTimeB;
    }

    public void setTimeA(Time time){
        this.timeA = time;
        if (timeA != null)
            this.idTimeA = timeA.getId();
    }

    public Time getTimeA(){
        return timeA;
    }

    public void setTimeB(Time time){
        this.timeB = time;
        if (timeB != null)
            this.idTimeB = timeB.getId();
    }

    public Time getTimeB(){
        return timeB;
    }

    public void setQuantidadeSets(int quantidadeSets){
        this.quantidadeSets = quantidadeSets;
    }

    public int getQuantidadeSets(){
        return quantidadeSets;
    }

    public List<Set> getSets(){
        return sets;
    }

    public String getVencedor() {
        return vencedor;
    }

    public void setVencedor(String vencedor) {
        this.vencedor = vencedor;
    }
}
