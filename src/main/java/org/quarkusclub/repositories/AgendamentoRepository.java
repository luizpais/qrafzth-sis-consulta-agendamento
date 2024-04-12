package org.quarkusclub.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.quarkusclub.models.AgendamentoDto;
import org.quarkusclub.models.AgendamentoEntity;
import org.quarkusclub.models.ClienteEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class AgendamentoRepository {

    @Inject
    EntityManager entityManager;

    public AgendamentoEntity createAgendamento(AgendamentoEntity agendamento) {
        AgendamentoEntity.persist(agendamento);
        return agendamento;
    }

    public List<AgendamentoEntity> consultaAgendamentosPorMedico(String nomeMedico) {
        return AgendamentoEntity.list("nomeMedico", nomeMedico);
    }

    public List<AgendamentoEntity> consultaAgendamentosDoDia(LocalDateTime dtDia) {
        LocalDateTime dtInicio = LocalDateTime.parse(dtDia.toString().substring(0,10) + "T00:00:00");
        LocalDateTime dtFim = LocalDateTime.parse(dtDia.toString().substring(0,10) + "T23:59:59");
        return AgendamentoEntity.list("horaConsulta between ?1 and ?2", dtInicio, dtFim);
    }

    public AgendamentoEntity excluiAgendamento(UUID idAgendamento) {
        AgendamentoEntity agendamento = AgendamentoEntity.findById(idAgendamento);
        if(agendamento == null) return null;
        agendamento.delete();
        return agendamento;
    }

    public AgendamentoEntity updateAgendamento(AgendamentoEntity agendamentoEntity) {
        AgendamentoEntity agendamento = AgendamentoEntity.findById(agendamentoEntity.getId());
        if(agendamento == null) return null;
        entityManager.merge(agendamentoEntity);
        return agendamento;
    }

}
