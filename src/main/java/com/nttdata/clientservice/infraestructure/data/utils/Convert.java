package com.nttdata.clientservice.infraestructure.data.utils;

import com.nttdata.clientservice.domain.document.ClientDto;
import com.nttdata.clientservice.infraestructure.data.entity.Client;
import org.springframework.beans.BeanUtils;

/**
 * Convert .
 */
public class Convert {
  /**
   * ClientDto .
   */
  public static ClientDto entityToDto(Client client) {
    ClientDto clientDto = new ClientDto();
    BeanUtils.copyProperties(client, clientDto);
    return clientDto;
  }

  /**
   * dtoToEntity .
   */
  public static Client dtoToEntity(ClientDto clientDto) {
    Client client = new Client();
    BeanUtils.copyProperties(clientDto, client);
    return client;
  }
}
