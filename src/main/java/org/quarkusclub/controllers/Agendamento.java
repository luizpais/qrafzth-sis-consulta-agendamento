package org.quarkusclub.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestResponse;
import org.quarkusclub.models.AgendamentoDto;
import org.quarkusclub.models.exceptions.SisConsultaException;
import org.quarkusclub.services.AgendamentoService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Path("/v1/agendamentos")
public class Agendamento {

    @Inject
    AgendamentoService agendamentoService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/medico/{nomeMedico}")
    public RestResponse<List<AgendamentoDto>> agendamentosMedico(String nomeMedico) {
        return RestResponse.status(Response.Status.OK, agendamentoService.getAgendamentosMedico(nomeMedico));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/dia/{dtAgendamento}")
    public RestResponse<List<AgendamentoDto>> agendamentosMedico(@PathParam("dtAgendamento") LocalDateTime dtdia) {
        return RestResponse.status(Response.Status.OK, agendamentoService.getAgendamentosDoDia(dtdia));
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RestResponse<AgendamentoDto> createAgendamento(AgendamentoDto agendamento) {
        AgendamentoDto novoAgendamento = agendamentoService.createAgendamento(agendamento);
        return RestResponse.status(Response.Status.CREATED, novoAgendamento);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{idAgendamento}")
    public RestResponse<AgendamentoDto> deleteAgendamento(UUID idAgendamento) throws SisConsultaException {
        AgendamentoDto agendamento = agendamentoService.excluiAgendamento(idAgendamento);
        return RestResponse.status(Response.Status.OK, agendamento);
    }

    @PATCH
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RestResponse<AgendamentoDto> updateAgendamento(AgendamentoDto agendamento) throws SisConsultaException {
        if(agendamento == null) {
            throw new SisConsultaException("Agendamento não informado", Response.Status.BAD_REQUEST);
        }
        AgendamentoDto updatedAgendamento = agendamentoService.updateAgendamento(agendamento);
        return RestResponse.status(Response.Status.ACCEPTED, updatedAgendamento);
    }
}
