package org.quarkusclub.mappers;


import org.quarkusclub.models.AgendamentoDto;
import org.quarkusclub.models.AgendamentoEntity;

import java.util.List;

public class AgendamentoMapper {
    public static AgendamentoDto mapEntityToDto(AgendamentoEntity agendamento) {
        return new AgendamentoDto(agendamento.getId(),  agendamento.getIdCliente(), agendamento.getNomeMedico(), agendamento.getHoraConsulta(), agendamento.getNomeConvenio());
    }
    public static AgendamentoEntity mapDtoToEntity(AgendamentoDto agendamento) {
        return new AgendamentoEntity(agendamento.id(), agendamento.idCliente(), agendamento.nomeMedico(), agendamento.nomeConvenio(), agendamento.horaConsulta());
    }
    public static List<AgendamentoDto> mapEntityListToDtoList(List<AgendamentoEntity> agendamentos) {
        if(agendamentos == null || agendamentos.isEmpty() ) {
            return List.of();
        }
        return agendamentos.stream().map(AgendamentoMapper::mapEntityToDto).toList();
    }
}