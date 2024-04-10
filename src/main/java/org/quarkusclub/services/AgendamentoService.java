package org.quarkusclub.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.ApplicationPath;
import org.quarkusclub.mappers.AgendamentoMapper;
import org.quarkusclub.models.AgendamentoDto;
import org.quarkusclub.repositories.AgendamentoRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class AgendamentoService {

    @Inject
    private AgendamentoRepository agendamentoRepository;

    //List<AgendamentoDto> agendamentos = new ArrayList<>();
    public List<AgendamentoDto> getAgendamentosMedico(String nomeMedico) {
        return AgendamentoMapper.mapEntityListToDtoList(agendamentoRepository.consultaAgendamentosPorMedico(nomeMedico));
    }

    @Transactional
    public AgendamentoDto createAgendamento(AgendamentoDto agendamento) {
        if( agendamento.horaConsulta().isBefore(LocalDateTime.now()) ) {
            throw new IllegalArgumentException("A data da consulta não pode ser anterior a data atual");
        }
        AgendamentoDto novoAgendamento = new AgendamentoDto(UUID.randomUUID(), UUID.randomUUID(), agendamento.nomeMedico(), agendamento.horaConsulta(), agendamento.nomeConvenio());
        agendamentoRepository.createAgendamento(AgendamentoMapper.mapDtoToEntity(novoAgendamento));
        return novoAgendamento;
    }
}
