package com.nttdata.clientservice.infraestructure.data.mongodb;

import com.nttdata.clientservice.domain.document.ClientDto;
import com.nttdata.clientservice.infraestructure.data.entity.Client;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * ClientRepositoryMongoDb .
 */
@Repository
public interface ClientRepositoryMongoDb extends ReactiveMongoRepository<Client, String> {
  Mono<Client> findByDocumentNumber(String documentNumber);
}
