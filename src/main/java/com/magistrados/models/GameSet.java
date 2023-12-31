package com.magistrados.models;

public class GameSet {

    private Long id;
    private Partida partida;
    private Long idPartida;
    private int ordem;
    // time a
    private int pontosTimeA = 0;
    // time b;
    private int pontosTimeB = 0;
    private boolean iniciado = false;
    private boolean finalizado = false;
    private String vencedor;

    public GameSet() {
    }

    public GameSet(Partida partida, Long idPartida, int ordem) {
        this.partida = partida;
        this.idPartida = idPartida;
        this.ordem = ordem;
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

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    public int getPontosTimeA() {
        return pontosTimeA;
    }

    public void setPontosTimeA(int pontosTimeA) {
        this.pontosTimeA = pontosTimeA;
    }

    public int getPontosTimeB() {
        return pontosTimeB;
    }

    public void setPontosTimeB(int pontosTimeB) {
        this.pontosTimeB = pontosTimeB;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    public String getVencedor() {
        return vencedor;
    }

    public void setVencedor(String vencedor) {
        this.vencedor = vencedor;
    }

    public void addPontosTimeA() {
        this.pontosTimeA++;
    }

    public void addPontosTimeB() {
        this.pontosTimeB++;
    }

    public void remPontosTimeA() {
        if (this.pontosTimeA > 1)
            this.pontosTimeA--;
    }

    public void remPontosTimeB() {
        if (this.pontosTimeB > 1)
            this.pontosTimeB--;
    }

    public void finalizarSet(String timeVencedor) {
        finalizado = true;
        vencedor = timeVencedor;
    }

    public Long getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(Long idPartida) {
        this.idPartida = idPartida;
    }

    public boolean isIniciado() {
        return iniciado;
    }

    public void setIniciado(boolean iniciado) {
        this.iniciado = iniciado;
    }

    @Override
    public String toString() {
        return "GameSet{" +
                "id=" + id +
                ", partida=" + partida +
                ", idPartida=" + idPartida +
                ", ordem=" + ordem +
                ", pontosTimeA=" + pontosTimeA +
                ", pontosTimeB=" + pontosTimeB +
                ", iniciado=" + iniciado +
                ", finalizado=" + finalizado +
                ", vencedor='" + vencedor + '\'' +
                '}';
    }
}
