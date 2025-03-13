package com.kane.librarymanagement.domain.entities;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Getter
@Setter
public class Book {
  private Long id;
  private String title;
  private String author;
  private String ISBN;
  private LocalDate publicationDate;
  private BigDecimal price;
  private int total = 0; // Total copies ever added
  private int stock = 0; // Available copies
  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;
}
