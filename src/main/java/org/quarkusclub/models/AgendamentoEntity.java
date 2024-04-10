package org.quarkusclub.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.asm.Advice;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "agendamento")
@NoArgsConstructor
@Getter
@Setter
public class AgendamentoEntity extends PanacheEntityBase {
    @Id
    private UUID id;
    private String nomeMedico;
    private String nomeConvenio;
    private UUID idCliente;
    private LocalDateTime horaConsulta;

    public AgendamentoEntity(UUID id, UUID idCliente, String nomeMedico, String nomeConvenio, LocalDateTime horaConsulta) {
        this.id = id;
        this.idCliente = idCliente;
        this.nomeMedico = nomeMedico;
        this.nomeConvenio = nomeConvenio;
        this.horaConsulta = horaConsulta;
    }
}
