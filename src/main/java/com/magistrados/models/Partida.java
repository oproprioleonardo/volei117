package com.magistrados.models;

import com.magistrados.managers.enums.TeamID;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Partida {
    private Long id;
    private LocalDateTime dateTime;
    private String local;
    private Time timeA, timeB;
    private Integer setsA = 0, setsB = 0;
    private Long idTimeA, idTimeB;
    private int quantidadeSets;
    private Set<GameSet> gameSets = new HashSet<>();
    private String vencedor;
    private boolean finalizada = false;

    public Partida() {
    }

    public Partida(LocalDateTime dateTime, String local, Time timeA, Time timeB, int quantidadeSets, String vencedor) {
        this.dateTime = dateTime;
        this.local = local;
        this.timeA = timeA;
        this.timeB = timeB;
        setQuantidadeSets(quantidadeSets);
        this.vencedor = vencedor;
    }

    public void finalizarPartida(TeamID teamID) {
        this.finalizada = true;
        switch (teamID) {
            case TIME_A -> {
                this.getTimeA().addVitoria();
                this.getTimeB().addDerrota();
            }
            case TIME_B -> {
                this.getTimeB().addVitoria();
                this.getTimeA().addDerrota();
            }
        }
        this.vencedor = teamID.getId();

        this.getJogadores().forEach(jogador -> {
            final MatchPlayerStats playerStats = jogador.matchStats(this.id);
            jogador.addPontos(playerStats.getQuantidadePontos());
            jogador.addSaques(playerStats.getQuantidadeSaques());
            jogador.addBloqueios(playerStats.getQuantidadeBloqueios());
            jogador.addDefesas(playerStats.getQuantidadeDefesas());
        });

    }

    public boolean isCreated() {
        return this.id != null && this.id > 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GameSet getSetByOrder(int order) {
        return this.gameSets.stream().filter(gameSet -> gameSet.getOrdem() == order).findFirst().orElse(null);
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
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

    public Time getTimeA() {
        return timeA;
    }

    public void setTimeA(Time time) {
        this.timeA = time;
        if (timeA != null)
            this.idTimeA = timeA.getId();
    }

    public Time getTimeB() {
        return timeB;
    }

    public void setTimeB(Time time) {
        this.timeB = time;
        if (timeB != null)
            this.idTimeB = timeB.getId();
    }

    public Set<Jogador> getJogadores() {
        final HashSet<Jogador> players = new HashSet<>(this.getTimeA().getJogadores());
        players.addAll(this.getTimeB().getJogadores());
        return players;
    }

    public int getQuantidadeSets() {
        return quantidadeSets;
    }

    public void setQuantidadeSets(int quantidadeSets) {
        //caso passe uma quantidade par de sets, aumenta um, voltando um impar
        this.quantidadeSets = quantidadeSets % 2 == 1 ? quantidadeSets : quantidadeSets + 1;
        for (int i = 1; i <= quantidadeSets; i++) {
            this.gameSets.add(new GameSet(this, this.id, i));
        }
    }

    public Set<GameSet> getGameSets() {
        return gameSets;
    }

    public void setGameSets(Set<GameSet> gameSets) {
        this.gameSets = gameSets;
    }

    public String getVencedor() {
        return vencedor;
    }

    public void setVencedor(String vencedor) {
        this.vencedor = vencedor;
    }

    public boolean isFinalizada() {
        return finalizada;
    }

    public Integer getSetsB() {
        return setsB;
    }

    public void addSetsB() {
        this.setsB++;
    }

    public void setSetsB(Integer setsB) {
        this.setsB = setsB;
    }

    public void addSetsA() {
        this.setsA++;
    }

    public Integer getSetsA() {
        return setsA;
    }

    public void setSetsA(Integer setsA) {
        this.setsA = setsA;
    }
}
