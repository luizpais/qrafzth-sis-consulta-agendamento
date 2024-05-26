package org.quarkusclub.repositories;


import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.jboss.logging.Logger;
import org.quarkusclub.dtos.ClienteStatusResponse;
import org.quarkusclub.dtos.MedicoStatusResponse;
import org.quarkusclub.restclients.ConvenioService;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ApplicationScoped
public class ConveniosRepository {

    private static final Logger log = Logger.getLogger(ConveniosRepository.class);
    private final Map<String, ConvenioService> convenios = new HashMap<>();

    @ConfigProperty( name = "convenio.boa-vida.url")
    private String boaVidaUrl;

    @ConfigProperty( name = "convenio.vida-longa.url")
    private String vidaLongaUrl;

    private ConveniosRepository() {
        convenios.put("boa vida", RestClientBuilder.newBuilder()
                .baseUri(URI.create("http://localhost:8081/"))
                .build(ConvenioService.class));

        convenios.put("vida longa", RestClientBuilder.newBuilder()
                .baseUri(URI.create("http://localhost:8082/"))
                .build(ConvenioService.class));
    }

    public ClienteStatusResponse getStatusConveniado(UUID idConveniado, String plano) {
        ConvenioService conveniadosService = this.convenios.get(plano.toLowerCase());
        log.infof("ConveniadoService: %s", plano);
        return conveniadosService.getStatusConveniado(idConveniado);
    }

    public MedicoStatusResponse getStatusMedico(String nomeMedico, String plano) {
        ConvenioService conveniadosService = this.convenios.get(plano.toLowerCase());
        log.infof("ConveniadoService: %s", plano);
        return conveniadosService.getStatusMedico(nomeMedico);
    }
}
