package com.nttdata.clientservice.domain.document;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClientDto.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
  private String id;
  private String firstName;
  private String surnames;
  private String documentNumber;
  private String documentType;
  private String address;
  private String email;
  private String clientType;
  private Date createAt;
  private Boolean status;
}
