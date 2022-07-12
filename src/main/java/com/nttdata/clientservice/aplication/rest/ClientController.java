package com.nttdata.clientservice.aplication.rest;

import com.nttdata.clientservice.domain.document.ClientDto;
import com.nttdata.clientservice.domain.interfaces.ClientService;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * ClientController.
 */
@RestController
@RequestMapping("/client")
public class ClientController {
  private static final Logger log = LoggerFactory.getLogger(ClientController.class);
  @Autowired
  ClientService clientService;

  @GetMapping
  public String index() {
    return "ok Client";
  }

  @PostMapping("/")
  public Mono<ClientDto> register(@RequestBody Mono<ClientDto> clientDto) {
    return clientService.save(clientDto);
  }

  @GetMapping("/list")
  public Flux<ClientDto> findAll() {
    return clientService.findAll();
  }

  @GetMapping("/document-number/{number}")
  public Mono<ResponseEntity<ClientDto>> findNumber(@PathVariable String number) {
    log.info("find Client by documentNumber: {}", number);
    return clientService.findByDocumentNumber(number)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @GetMapping("/list/{id}")
  public Mono<ClientDto> findById(@PathVariable String id) {
    return clientService.findById(id);
  }

  @DeleteMapping("/{id}")
  public Mono<Void> delete(@PathVariable String id) {
    return clientService.deleteById(id);
  }


  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Mono<ResponseEntity<ClientDto>> update(@PathVariable String id,
                                                @RequestBody ClientDto clientDto) {
    log.info("update Client by id: {}", id);
    return clientService.update(id, clientDto)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }
}