package org.quarkusclub.models;

import java.time.LocalDateTime;
import java.util.UUID;

public record AgendamentoDto (
        UUID id,
        UUID idCliente,
        String nomeMedico,
        LocalDateTime horaConsulta,
        String nomeConvenio
) {
}
