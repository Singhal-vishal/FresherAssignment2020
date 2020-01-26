package com.newsletter.controllers;

import com.newsletter.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired
  private UserServiceImpl service;

  @GetMapping(path = "/edit-newsletter")
  public ResponseEntity<String> stopRequest() {
    return new ResponseEntity<>("Hello there!!", HttpStatus.OK);
  }

}
