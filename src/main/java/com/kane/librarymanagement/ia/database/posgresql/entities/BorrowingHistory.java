package com.kane.librarymanagement.ia.database.posgresql.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;


@Getter
@Setter
@Entity
@Table(name = "borrowing_history")  // Custom join table
public class BorrowingHistory {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "borrowing_history_seq")
  @SequenceGenerator(name = "borrowing_history_seq", sequenceName = "borrowing_history_seq", allocationSize = 1)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id")  // Custom foreign key for User
  private User user;

  @ManyToOne
  @JoinColumn(name = "book_id")  // Custom foreign key for Book
  private Book book;

  private OffsetDateTime borrowFrom;
  private BigDecimal penalty = BigDecimal.ZERO;

  @CreationTimestamp
  @Column(name = "created_at", updatable = false)
  private OffsetDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private OffsetDateTime updatedAt;

  // for JPA only, no use
  public BorrowingHistory() {}

  public BorrowingHistory(User user, Book book) {
    this.user = user;
    this.book = book;
  }
}
