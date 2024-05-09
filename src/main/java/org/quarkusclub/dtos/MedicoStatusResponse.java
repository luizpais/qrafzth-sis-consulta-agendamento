package org.quarkusclub.dtos;

import java.util.UUID;

public record MedicoStatusResponse(UUID idMedico, boolean status){
}
