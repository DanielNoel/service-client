package com.nttdata.clientservice.infraestructure.data.interfaces;

import com.nttdata.clientservice.infraestructure.data.entity.Client;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

/**
 * ClientRepository .
 */
public interface ClientRepository extends ReactiveMongoRepository<Client, String> {


}
