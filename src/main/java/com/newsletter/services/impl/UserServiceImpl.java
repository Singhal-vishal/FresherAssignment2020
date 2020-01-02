package com.newsletter.services.impl;

import com.newsletter.dao.UserRepository;
import com.newsletter.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements IUserService {

  @Autowired
  UserRepository repo;
}
