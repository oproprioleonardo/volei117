package com.magistrados.services;

import com.magistrados.api.repositories.TimeRepository;
import com.magistrados.models.Time;

import java.util.HashSet;

public class TimeService {

    private final TimeRepository timeRepository;
    private final JogadorService jogadorService;

    public TimeService(TimeRepository timeRepository, JogadorService jogadorService) {
        this.timeRepository = timeRepository;
        this.jogadorService = jogadorService;
    }

    public Time buscarTime(String nome) {
        final Time time = this.timeRepository.findByName(nome);
        time.setJogadores(this.jogadorService.buscarJogadores(time.getId()));
        return time;
    }

    public Time buscarTime(Long id) {
        final Time time = this.timeRepository.findById(id);
        time.setJogadores(this.jogadorService.buscarJogadores(time.getId()));
        return time;
    }

    public void deletarTime(Time time) {
        time.getJogadores().forEach(jogador -> {
            jogador.setTimeId(null);
            this.jogadorService.salvarJogador(jogador);
        });
        time.setJogadores(new HashSet<>());
        this.timeRepository.deleteById(time.getId());
    }

    public void salvarTime(Time time) {
        this.timeRepository.save(time);
        time.getJogadores().forEach(this.jogadorService::salvarJogador);
    }
}
