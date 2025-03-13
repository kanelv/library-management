package com.kane.librarymanagement.domain.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class User {
  private long id;
  private String username;
  private String phoneNumber;
  private String password;
  private String firstName, lastName;
  private String address;
  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;
}
