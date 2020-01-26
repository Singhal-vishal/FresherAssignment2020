package com.newsletter.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserEntity {
  @Id
  private String email;
}
