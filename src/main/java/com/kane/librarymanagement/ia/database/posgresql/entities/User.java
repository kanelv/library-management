package com.kane.librarymanagement.ia.database.posgresql.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"borrowedBooks"})
@Entity
@Table(
    name="user",
    indexes = {
        @Index(name = "idx_username", columnList = "username"),
        @Index(name = "idx_phoneNumber", columnList = "phoneNumber")
    },
    uniqueConstraints = {
        @UniqueConstraint(name = "uc_username_phoneNumber", columnNames = {"username", "phoneNumber"})
    }
)
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
  @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
  private Long id;

  @Column(unique=true, nullable = false, length = 100)
  private String username;

  @Column(nullable = false, length = 15)
  private String phoneNumber;

  @Column(nullable = false)
  private String password;

  @Column(length = 50)
  private String firstName;

  @Column(length = 50)
  private String lastName;

  @Column(length = 255)
  private String address;

  @CreationTimestamp
  @Column(name = "created_at", updatable = false)
  private OffsetDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private OffsetDateTime updatedAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "role_id") // Foreign key in Role table
  private Role role;

  // Many-to-many relationship with custom join table
  @ManyToMany
  @JoinTable(
      name = "borrowing_histories",  // Custom join table name
      joinColumns = @JoinColumn(name = "user_id"),  // Custom foreign key for User
      inverseJoinColumns = @JoinColumn(name = "book_id")  // Custom foreign key for Book
  )
  private List<Book> borrowedBooks = new ArrayList<>();

  // for JPA only, no use
  public User() {}

  public User(
      String username,
      String phoneNumber,
      String password,
      String firstName,
      String lastName,
      String address
  ) {
    this.username = username;
    this.phoneNumber = phoneNumber;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
  }
}
