package org.quarkusclub.restclients;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.quarkusclub.dtos.ClienteStatusResponse;
import org.quarkusclub.dtos.MedicoStatusResponse;

import java.util.UUID;

@RegisterRestClient
public interface ConvenioService {

    @GET
    @Path("/conveniados/status/{idConveniado}")
    ClienteStatusResponse getStatusConveniado(@PathParam("idConveniado") UUID idConveniado);

    @GET
    @Path("/medicos/status/{nomeMedico}")
    MedicoStatusResponse getStatusMedico(@PathParam("nomeMedico") String nomeMedico);


}
