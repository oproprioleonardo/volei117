package com.magistrados.services;

import com.magistrados.api.repositories.TimeRepository;
import com.magistrados.models.Jogador;
import com.magistrados.models.Time;
import com.magistrados.models.create.CreateTeam;
import com.magistrados.models.edit.EditTeam;
import com.magistrados.models.remove.RemoveTeam;

import java.util.HashSet;
import java.util.Optional;

public class TimeService {

    private final TimeRepository timeRepository;
    private final JogadorService jogadorService;

    public TimeService(TimeRepository timeRepository, JogadorService jogadorService) {
        this.timeRepository = timeRepository;
        this.jogadorService = jogadorService;
    }

    public Time criarTime(CreateTeam createTeam){
        final Time time = new Time();
        time.setNomeTime(createTeam.nome());
        time.setVitorias(Integer.parseInt(createTeam.vitorias()));
        time.setDerrotas(Integer.parseInt(createTeam.derrotas()));

        this.timeRepository.save(time);
        return time;
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

    public void editarTime(EditTeam editTeam){
        final Time time = this.buscarTime(Long.parseLong(editTeam.id()));

        time.setNomeTime(editTeam.nome());
        time.setVitorias(Integer.parseInt(editTeam.vitorias()));
        time.setDerrotas(Integer.parseInt(editTeam.derrotas()));
        time.setJogadores(this.jogadorService.buscarJogadores(Long.parseLong(editTeam.id())));

        this.salvarTime(time);
    }

    public void deletarTime(Time time) {
        time.getJogadores().forEach(jogador -> {
            jogador.setTimeId(null);
            this.jogadorService.salvarJogador(jogador);
        });
        time.setJogadores(new HashSet<>());
        this.timeRepository.deleteById(time.getId());
    }

    public void deletarTime(RemoveTeam removeTeam){
        Time time = Optional.ofNullable(this.buscarTime(Long.parseLong(removeTeam.id())))
                .orElse(this.buscarTime(removeTeam.nome()));

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
