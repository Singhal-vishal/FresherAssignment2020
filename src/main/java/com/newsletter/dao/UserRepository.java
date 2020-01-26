package com.newsletter.dao;

import com.newsletter.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


//todo : implement master and slave
public interface UserRepository extends JpaRepository<UserEntity, String> {

}
