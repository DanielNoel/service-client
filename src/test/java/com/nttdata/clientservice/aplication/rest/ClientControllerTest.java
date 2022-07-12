package com.nttdata.clientservice.aplication.rest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.nttdata.clientservice.domain.document.ClientDto;
import com.nttdata.clientservice.domain.document.ClientType;
import com.nttdata.clientservice.infraestructure.data.entity.DocumentType;
import com.nttdata.clientservice.infraestructure.data.interfaces.ClientRepository;
import com.nttdata.clientservice.infraestructure.data.utils.Convert;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
class ClientControllerTest {

  @Autowired
  private WebTestClient webTestClient;

  @Autowired
  private ClientRepository clientRepository;

  static String CLIENT_URL = "client";

  @BeforeEach
  void setUp() {
    ClientDto clientDto1 = ClientDto.builder().id("001").firstName("Daniel")
        .surnames("Caqui Mejía").documentNumber("78945612").email("caqui@gmail.com")
        .address("Av. Universitaria 493")
        .documentType(DocumentType.RUC.name())
        .clientType(ClientType.PERSONAL.name())
        .status(true).build();

    ClientDto clientDto2 = ClientDto.builder().firstName("Wilfredo")
        .surnames("Gomez Rivera").documentNumber("00000016").email("czarate@gmail.com")
        .address("Av. Brasil 1345")
        .documentType(DocumentType.RUC.name())
        .clientType(ClientType.BUSINESS.name())
        .status(false).build();

    List<ClientDto> clients = List.of(clientDto1, clientDto2);

    clientRepository
        .deleteAll()
        .thenMany(clientRepository.saveAll(Flux.fromIterable(clients).map(Convert::dtoToEntity)))
        .blockLast();
  }

  @Test
  void getAllClients() {

    webTestClient
        .get()
        .uri("http://localhost:8885/client/list")
        .exchange()
        .expectStatus()
        .is2xxSuccessful()
        .expectBodyList(ClientDto.class)
        .hasSize(2);
  }

  @Test
  void getClientById() {
    var id = "62c8569e342f0d5bc020cf55";
    webTestClient
        .get()
        .uri("http://localhost:8885/client" + "/{id}", id)
        .exchange()
        .expectStatus()
        .is2xxSuccessful()
        .expectBody(ClientDto.class)
        .consumeWith(clientDtoEntityExchangeResult -> {
          var clientDto = clientDtoEntityExchangeResult.getResponseBody();
          assert clientDto != null;
        });
  }

  @Test
  void getClientByDocumentNumber() {
    var number = "00000015";
    webTestClient
        .get()
        .uri(CLIENT_URL + "/document-number/{number}", number)
        .exchange()
        .expectStatus()
        .is2xxSuccessful()
        .expectBody(ClientDto.class)
        .consumeWith(clientDtoEntityExchangeResult -> {
          var clientDto = clientDtoEntityExchangeResult.getResponseBody();
          assert clientDto != null;
        });
  }

  @Test
  void getClientByIdNotFoud() {
    var id = "62c8569e342f0d5bc020cf55";
    webTestClient
        .get()
        .uri(CLIENT_URL + "/{id}", id)
        .exchange()
        .expectStatus()
        .isNotFound();
  }

  @Test
  void createClient() {

    ClientDto createClient = ClientDto.builder().firstName("Juana Diana")
        .surnames("Ramos Garcia").documentNumber("00000020").email("juana.ramos@gmail.com")
        .address("Jr. Caraz 500")
        .documentType(DocumentType.DNI.name())
        .clientType(ClientType.PERSONAL.name())
        .status(false).build();

    webTestClient
        .post()
        .uri(CLIENT_URL)
        .bodyValue(createClient)
        .exchange()
        .expectStatus()
        .is2xxSuccessful()
        .expectBody(ClientDto.class)
        .consumeWith(clientDtoEntityExchangeResult -> {
          var clientDto = clientDtoEntityExchangeResult.getResponseBody();
          assert clientDto != null;
          assertEquals("Juana Diana", clientDto.getFirstName());
        });
  }

  @Test
  void updateClient() {
    var id = "001";

    ClientDto updatedClient = ClientDto.builder().firstName("Luis Jorge")
        .surnames("Cadillo Basso").documentNumber("00000015").email("luis.cadillo@gmail.com")
        .address("Jr. Eucaliptos 089")
        .documentType(DocumentType.DNI.name())
        .clientType(ClientType.PERSONAL.name())
        .status(true).build();

    webTestClient
        .put()
        .uri(CLIENT_URL + "/{id}", id)
        .bodyValue(updatedClient)
        .exchange()
        .expectStatus()
        .is2xxSuccessful()
        .expectBody(ClientDto.class)
        .consumeWith(clientDtoEntityExchangeResult -> {
          var clientDto = clientDtoEntityExchangeResult.getResponseBody();
          assert clientDto != null;
          assertEquals("Luis Jorge", clientDto.getFirstName());
        });
  }

  @Test
  void deleteClientById() {
    var id = "001";

    webTestClient
        .delete()
        .uri(CLIENT_URL + "/{id}", id)
        .exchange()
        .expectStatus()
        .isNoContent();
  }

}