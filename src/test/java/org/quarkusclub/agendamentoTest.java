package org.quarkusclub;

import io.quarkus.test.junit.QuarkusTest;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
class agendamentoTest {
    @Test
    void testHelloEndpoint() {
        given()
          .when().get("/agendamentos")
          .then()
             .statusCode(200)
             .body(is("Hello from Quarkus REST"));
    }

    @Test
    void testIntervaloDatas() {
        LocalDateTime dtHoje = LocalDateTime.parse("2021-10-01T00:00:00");
        // "2021-10-01T00:00:00"
        LocalDateTime dtInicio = LocalDateTime.parse("2021-10-01T00:00:00");
        LocalDateTime dtFim = LocalDateTime.parse(dtInicio.toString().substring(0,10) + "T23:59:59");
        System.out.printf("dtHoje: %s, dtInicio: %s, dtFim: %s, %b\n, ", dtHoje, dtInicio, dtFim, dtHoje.isAfter(dtInicio) && dtHoje.isBefore(dtFim));
        assertTrue(dtHoje.isEqual(dtInicio) || dtHoje.isAfter(dtInicio) && dtHoje.isBefore(dtFim), "Data hoje fora do intervalo");
    }

}