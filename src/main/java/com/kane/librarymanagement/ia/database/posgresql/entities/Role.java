package com.kane.librarymanagement.ia.database.posgresql.entities;

import com.kane.librarymanagement.domain.enums.RoleType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")
  @SequenceGenerator(name = "role_seq", sequenceName = "role_seq", allocationSize = 1)
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private RoleType roleType;

  private int maxBookNumber;
  private int maxLoanDuration;

  @CreationTimestamp
  @Column(name = "created_at", updatable = false)
  private OffsetDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private OffsetDateTime updatedAt;

  @OneToMany(mappedBy = "role", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
  private List<User> users = new ArrayList<>();

  // for JPA only, no use
  public Role() {}

  public Role(RoleType roleType, int maxBookNumber, int maxLoanDuration) {
    this.roleType = (roleType != null) ? roleType : RoleType.USER; // Null check for roleType
    this.maxBookNumber = maxBookNumber;
    this.maxLoanDuration = maxLoanDuration;
  }
}
