package org.quarkusclub.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestResponse;
import org.quarkusclub.models.AgendamentoDto;
import org.quarkusclub.services.AgendamentoService;

import java.util.List;

@Path("/agendamentos")
public class Agendamento {

    @Inject
    AgendamentoService agendamentoService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/medico/{nomeMedico}")
    public RestResponse<List<AgendamentoDto>> agendamentosMedico(String nomeMedico) {
        return RestResponse.status(Response.Status.OK, agendamentoService.getAgendamentosMedico(nomeMedico));
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RestResponse<AgendamentoDto> createAgendamento(AgendamentoDto agendamento) {
        AgendamentoDto novoAgendamento = agendamentoService.createAgendamento(agendamento);
        return RestResponse.status(Response.Status.CREATED, novoAgendamento);
    }
}
