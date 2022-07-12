package com.nttdata.clientservice.domain.service;

import com.nttdata.clientservice.domain.document.ClientDto;
import com.nttdata.clientservice.domain.interfaces.ClientService;
import com.nttdata.clientservice.infraestructure.data.mongodb.ClientRepositoryMongoDb;
import com.nttdata.clientservice.infraestructure.data.utils.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * ClientSeriviceImpl .
 */
@Service
public class ClientSeriviceImpl implements ClientService {
  @Autowired
  ClientRepositoryMongoDb clientRepositoryMongoDb;

  @Override
  public Flux<ClientDto> findAll() {
    return clientRepositoryMongoDb.findAll().map(Convert::entityToDto);
  }

  @Override
  public Mono<ClientDto> findById(String id) {
    return clientRepositoryMongoDb.findById(id).map(Convert::entityToDto);
  }

  @Override
  public Mono<ClientDto> save(Mono<ClientDto> client) {
    return client.map(Convert::dtoToEntity).flatMap(clientRepositoryMongoDb::insert)
        .map(Convert::entityToDto);
  }

  @Override
  public Mono<Void> deleteById(String id) {
    return clientRepositoryMongoDb.deleteById(id);
  }

  @Override
  public Mono<ClientDto> findByDocumentNumber(String documentNumber) {
    return clientRepositoryMongoDb.findByDocumentNumber(documentNumber).map(Convert::entityToDto);
  }

  @Override
  public Mono<ClientDto> update(String id, ClientDto clientDto) {
    return clientRepositoryMongoDb.findById(id)
        .flatMap(p -> Mono.just(clientDto)
            .map(Convert::dtoToEntity)
            .doOnNext(e -> e.setId(id)))
        .flatMap(this.clientRepositoryMongoDb::save)
        .map(Convert::entityToDto);
  }
}
