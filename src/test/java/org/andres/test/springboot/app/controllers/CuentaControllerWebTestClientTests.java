package org.andres.test.springboot.app.controllers;

<<<<<<< HEAD
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.andres.test.springboot.app.models.Cuenta;
import org.andres.test.springboot.app.models.TransaccionDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
=======
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.andres.test.springboot.app.models.TransaccionDto;
import org.junit.jupiter.api.Test;
>>>>>>> origin/Actualizacion
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
<<<<<<< HEAD
import java.util.List;
=======
>>>>>>> origin/Actualizacion

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;
import static org.hamcrest.Matchers.*;

import static org.junit.jupiter.api.Assertions.*;

//siempre es mejor probar con random por si el 8080 esta ocupado
//esto levanta un servidor real
//aquí vamos a probar sin mock
//para probar este test se debe levantar la aplicacion
<<<<<<< HEAD
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)//ordenamos los test por anotation
=======
>>>>>>> origin/Actualizacion
@SpringBootTest(webEnvironment = RANDOM_PORT)
class CuentaControllerWebTestClientTests {

    @Autowired
    private WebTestClient client;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
<<<<<<< HEAD
    @Order(1)
=======
>>>>>>> origin/Actualizacion
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
<<<<<<< HEAD
    @Order(2)
=======
>>>>>>> origin/Actualizacion
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
<<<<<<< HEAD

    @Test
    @Order(3)
    void testDetalle() throws JsonProcessingException {
        Cuenta cuenta = new Cuenta(1L,"Andrés",new BigDecimal("800"));
        client.get().uri("api/cuentas/1").exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.persona").isEqualTo("Andrés")
                .jsonPath("$.saldo").isEqualTo(800)
                .json(objectMapper.writeValueAsString(cuenta));
    }

    @Test
    @Order(4)
    void testDetalle2() {
        client.get().uri("api/cuentas/2").exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(Cuenta.class)
                .consumeWith(response ->{
                    Cuenta cuenta = response.getResponseBody();
                    assertEquals("John", cuenta.getPersona());
                    assertEquals("2200.00", cuenta.getSaldo().toPlainString());
                });
    }

    @Test
    @Order(5)
    void testListar() {
        client.get().uri("api/cuentas").exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$[0].persona").isEqualTo("Andrés")
                .jsonPath("$[0].id").isEqualTo(1)
                .jsonPath("$[0].saldo").isEqualTo(800)
                .jsonPath("$[1].persona").isEqualTo("John")
                .jsonPath("$[1].id").isEqualTo(2)
                .jsonPath("$[1].saldo").isEqualTo(2200)
                .jsonPath("$").isArray()
                .jsonPath("$").value(hasSize(2));
    }

    @Test
    @Order(6)
    void testListar2() {
        client.get().uri("api/cuentas").exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Cuenta.class)// de esta forma esperamos una lista de Cuentas y no un Json
                .consumeWith(response ->{
                    List<Cuenta> cuentas = response.getResponseBody();
                    assertNotNull(cuentas);
                    assertEquals(2, cuentas.size());
                    assertEquals("Andrés", cuentas.get(0).getPersona());
                    assertEquals(1, cuentas.get(0).getId());
                    assertEquals(800, cuentas.get(0).getSaldo().intValue());
                    assertEquals("John", cuentas.get(1).getPersona());
                    assertEquals(2, cuentas.get(1).getId());
                    assertEquals(2200, cuentas.get(1).getSaldo().intValue());
                })
                .hasSize(2);
    }

    @Test
    @Order(7)
    void testGuardar() {
        Cuenta cuenta = new Cuenta(null, "Pepe", new BigDecimal("3000"));
        client.post().uri("api/cuentas")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(cuenta)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.id").isEqualTo(3)
                .jsonPath("$.persona").isEqualTo("Pepe")
                .jsonPath("$.saldo").isEqualTo(3000);
    }

    @Test
    @Order(8)
    void testGuardar2() {
        Cuenta cuenta = new Cuenta(null, "Pepa", new BigDecimal("3500"));
        client.post().uri("api/cuentas")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(cuenta)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(Cuenta.class)
                .consumeWith(response ->{
                    Cuenta c = response.getResponseBody();
                    assertNotNull(c);
                    assertEquals(4L, c.getId());
                    assertEquals("Pepa", c.getPersona());
                    assertEquals("3500", c.getSaldo().toPlainString());
                });

    }
=======
>>>>>>> origin/Actualizacion
}