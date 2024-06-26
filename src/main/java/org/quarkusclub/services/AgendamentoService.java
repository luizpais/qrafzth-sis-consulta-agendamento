package org.quarkusclub.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Response;
import org.quarkusclub.dtos.ClienteStatusResponse;
import org.quarkusclub.dtos.MedicoStatusResponse;
import org.quarkusclub.mappers.AgendamentoMapper;
import org.quarkusclub.models.AgendamentoDto;
import org.quarkusclub.models.exceptions.SisConsultaException;
import org.quarkusclub.repositories.AgendamentoRepository;
import org.quarkusclub.repositories.ConveniosRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class AgendamentoService {

    @Inject
    private AgendamentoRepository agendamentoRepository;

    @Inject
    private ConveniosRepository convenioRepository;

    //List<AgendamentoDto> agendamentos = new ArrayList<>();
    public List<AgendamentoDto> getAgendamentosMedico(String nomeMedico) {
        return AgendamentoMapper.mapEntityListToDtoList(agendamentoRepository.consultaAgendamentosPorMedico(nomeMedico));
    }

    public List<AgendamentoDto> getAgendamentosDoDia(LocalDateTime dtDia) {
        return AgendamentoMapper.mapEntityListToDtoList(agendamentoRepository.consultaAgendamentosDoDia(dtDia));
    }

    @Transactional
    public AgendamentoDto createAgendamento(AgendamentoDto agendamento) {
        if( agendamento.horaConsulta().isBefore(LocalDateTime.now()) ) {
            throw new IllegalArgumentException("A data da consulta não pode ser anterior a data atual");
        }
        ClienteStatusResponse conveniado = convenioRepository.getStatusConveniado(agendamento.idCliente(), agendamento.nomeConvenio());
        if(conveniado == null || !conveniado.status()){
            throw new RuntimeException("Conveniado não está ativo ou não existe no convenio.");
        }
        MedicoStatusResponse medico = convenioRepository.getStatusMedico(agendamento.nomeMedico(), agendamento.nomeConvenio());
        if(medico == null || !medico.status()){
            throw new RuntimeException("Médico não está ativo ou não existe no convenio.");
        }
        AgendamentoDto novoAgendamento = new AgendamentoDto(UUID.randomUUID(), UUID.randomUUID(), agendamento.nomeMedico(), agendamento.horaConsulta(), agendamento.nomeConvenio());
        agendamentoRepository.createAgendamento(AgendamentoMapper.mapDtoToEntity(novoAgendamento));
        return novoAgendamento;
    }

    @Transactional
    public AgendamentoDto excluiAgendamento(UUID idAgendamento) throws SisConsultaException {
        AgendamentoDto agendamento = AgendamentoMapper.mapEntityToDto(agendamentoRepository.excluiAgendamento(idAgendamento));
        if(agendamento == null) throw new SisConsultaException("Agendamento não encontrado", Response.Status.NOT_FOUND);
        return agendamento;
    }

    @Transactional
    public AgendamentoDto updateAgendamento(AgendamentoDto agendamentoDto) throws SisConsultaException {
        AgendamentoDto agendamento = AgendamentoMapper.mapEntityToDto(
                agendamentoRepository.updateAgendamento(AgendamentoMapper.mapDtoToEntity(agendamentoDto))
        );
        if(agendamento == null) throw new SisConsultaException("Agendamento não encontrado", Response.Status.NOT_FOUND);
        return agendamento;
    }


}
