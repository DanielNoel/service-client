package com.nttdata.clientservice.domain.interfaces;

import com.nttdata.clientservice.domain.document.ClientDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * ClientServicen .
 */
public interface ClientService {
  public Flux<ClientDto> findAll();

  public Mono<ClientDto> findById(String id);

  public Mono<ClientDto> save(Mono<ClientDto> client);

  public Mono<ClientDto> update(String id, ClientDto customerDto);
  public Mono<Void> deleteById(String id);

  Mono<ClientDto> findByDocumentNumber(String documentNumber);
}
