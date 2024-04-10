package org.quarkusclub.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import org.quarkusclub.models.AgendamentoEntity;
import org.quarkusclub.models.ClienteEntity;

import java.util.List;

@ApplicationScoped
public class AgendamentoRepository {

    public AgendamentoEntity createAgendamento(AgendamentoEntity agendamento) {
        AgendamentoEntity.persist(agendamento);
        return agendamento;
    }

    public List<AgendamentoEntity> consultaAgendamentosPorMedico(String nomeMedico) {
        return AgendamentoEntity.list("nomeMedico", nomeMedico);
    }
}
