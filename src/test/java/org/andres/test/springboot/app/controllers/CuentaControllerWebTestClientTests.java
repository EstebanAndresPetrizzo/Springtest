package org.andres.test.springboot.app.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.andres.test.springboot.app.models.TransaccionDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;
import static org.hamcrest.Matchers.*;

import static org.junit.jupiter.api.Assertions.*;

//siempre es mejor probar con random por si el 8080 esta ocupado
//esto levanta un servidor real
//aquí vamos a probar sin mock
//para probar este test se debe levantar la aplicacion
@SpringBootTest(webEnvironment = RANDOM_PORT)
class CuentaControllerWebTestClientTests {

    @Autowired
    private WebTestClient client;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testTransferir() {
        //Given
        TransaccionDto dto = new TransaccionDto();
        dto.setCuentaOrigenId(1L);
        dto.setCuentaDestinoId(2L);
        dto.setBancoId(1L);
        dto.setMonto(new BigDecimal("100"));

        //When
        client.post().uri("/api/cuentas/transferir")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dto)
                .exchange()//intercambio entre la solicitud y la respuesta

                //then
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.mensaje").isNotEmpty()
                .jsonPath("$.mensaje").value(is("Transferencia realizada con éxito"))
                .jsonPath("$.mensaje").value(valor -> assertEquals("Transferencia realizada con éxito", valor))
                .jsonPath("$.mensaje").isEqualTo("Transferencia realizada con éxito")
                .jsonPath("$.transaccion.cuentaOrigenId").isEqualTo(dto.getCuentaOrigenId())
                .jsonPath("$.date").isEqualTo(LocalDate.now().toString());
    }

    @Test
    void testTransferir2() {
        //Given
        TransaccionDto dto = new TransaccionDto();
        dto.setCuentaOrigenId(1L);
        dto.setCuentaDestinoId(2L);
        dto.setBancoId(1L);
        dto.setMonto(new BigDecimal("100"));

        //When
        client.post().uri("/api/cuentas/transferir")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dto)
                .exchange()//intercambio entre la solicitud y la respuesta

                //then
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(respuesta ->{
                    try {
                        JsonNode json = objectMapper.readTree(respuesta.getResponseBody());
                        assertEquals("Transferencia realizada con éxito", json.path("mensaje").asText());
                        assertEquals(1L, json.path("transaccion").path("cuentaOrigenId").asLong());
                        assertEquals(LocalDate.now().toString(), json.path("date").asText());
                        assertEquals("100", json.path("transaccion").path("monto").asText());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
}