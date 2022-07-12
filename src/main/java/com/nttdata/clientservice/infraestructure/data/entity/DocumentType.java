package com.nttdata.clientservice.infraestructure.data.entity;

import java.util.Arrays;

public enum DocumentType {

  DNI, RUC, CE;

  public boolean in(DocumentType... documentTypes) {
    return Arrays.stream(documentTypes).anyMatch(type -> type == this);
  }

}
