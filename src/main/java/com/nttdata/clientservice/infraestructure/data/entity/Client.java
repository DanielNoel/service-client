package com.nttdata.clientservice.infraestructure.data.entity;

import com.nttdata.clientservice.domain.document.ClientType;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Client .
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "clients")
public class Client {
  @Id
  private String id;
  private String firstName;
  private String surnames;
  private String documentNumber;
  private String address;
  private String email;
  private ClientType clientType;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date createAt;
  private Boolean status;
}
