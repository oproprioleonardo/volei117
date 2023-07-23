package com.magistrados.services;

import com.magistrados.api.repositories.TimeRepository;
import com.magistrados.exceptions.EntityNotFoundException;
import com.magistrados.models.Time;
import com.magistrados.models.create.CreateTeam;
import com.magistrados.models.edit.EditTeam;
import com.magistrados.models.find.FindTeam;
import com.magistrados.models.remove.RemoveTeam;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.HashSet;
import java.util.Optional;

public class TimeService {

    private final TimeRepository timeRepository;
    private final JogadorService jogadorService;

    public TimeService(TimeRepository timeRepository, JogadorService jogadorService) {
        this.timeRepository = timeRepository;
        this.jogadorService = jogadorService;
    }

    public Time criarTime(CreateTeam createTeam) {
        final Time time = new Time();
        time.setNomeTime(createTeam.nome());
        time.setVitorias(Integer.parseInt(createTeam.vitorias()));
        time.setDerrotas(Integer.parseInt(createTeam.derrotas()));

        this.timeRepository.save(time);
        return time;
    }

    public Time editarTime(EditTeam editTeam) {
        final Time time = this.buscarTime(Long.parseLong(editTeam.id()));

        time.setNomeTime(editTeam.nome());
        time.setVitorias(Integer.parseInt(editTeam.vitorias()));
        time.setDerrotas(Integer.parseInt(editTeam.derrotas()));
        time.setJogadores(this.jogadorService.buscarJogadores(Long.parseLong(editTeam.id())));

        this.salvarTime(time);
        return time;
    }

    public Time buscarTime(FindTeam findTeam) {
        Time time = null;
        if (!findTeam.nome().isBlank()) time = this.buscarTime(findTeam.nome());
        if (time == null && NumberUtils.isCreatable(findTeam.id())) time = this.buscarTime(findTeam.getId());
        return time;
    }

    public void deletarTime(RemoveTeam removeTeam) {
        final Time time = this.buscarTime(new FindTeam(removeTeam.id(), removeTeam.nome()));
        this.deletarTime(time);
    }

    public Time buscarTime(String nome) {
        final Time time = this.timeRepository.findByName(nome);
        if (!time.isCreated()) {
            throw new EntityNotFoundException(nome, "time");
        }
        time.setJogadores(this.jogadorService.buscarJogadores(time.getId()));
        return time;
    }

    public Time buscarTime(Long id) {
        final Time time = this.timeRepository.findById(id);
        if (!time.isCreated()) {
            throw new EntityNotFoundException(id.toString(), "time");
        }
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
