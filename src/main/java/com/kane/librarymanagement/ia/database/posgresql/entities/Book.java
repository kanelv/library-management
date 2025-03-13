package com.kane.librarymanagement.ia.database.posgresql.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"users"})
@Entity
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
  @SequenceGenerator(name = "book_seq", sequenceName = "book_seq", allocationSize = 1)
  private Long id;

  @Column(nullable = false, length = 255)
  private String title;
  private String author;

  @Column(unique = true, nullable = false)
  private String ISBN;
  private LocalDate publicationDate;

  @Column(precision = 10, scale = 2)
  private BigDecimal price;

  @Column(name = "total", nullable = false)
  private int total = 0;

  @Column(name = "stock", nullable = false)
  private int stock = 0;

  @CreationTimestamp
  @Column(name = "created_at", updatable = false)
  private OffsetDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private OffsetDateTime updatedAt;

  @ManyToMany(mappedBy = "borrowedBooks")  // Bidirectional, mapped by the 'courses' field in Student
  private List<User> users = new ArrayList<>();

  // for JPA only, no use
  public Book() {}

  public Book(
      String title,
      String author,
      String ISBN,
      BigDecimal price,
      LocalDate publicationDate,
      int total,
      int stock
  ) {
    this.title = title;
    this.author = author;
    this.ISBN = ISBN;
    this.publicationDate = publicationDate;
    this.price = price;
    this.total = total;
    this.stock = stock;
  }
}
