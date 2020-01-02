package com.newsletter.controllers;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * The type Email controller.
 */
@Controller
public class EmailController {

  @Autowired
  private static JavaMailSender sender;

  /**
   * Home string.
   *
   * @return the string
   */
  @GetMapping(path = "/simpleemail")
  public String home() {
    try {

      sendEmail();
      return "Email Sent!";
    } catch (Exception ex) {
      return "Error in sending email: " + ex;
    }
  }

  //cron for scheduling all the scheduled tasks
  //@Scheduled(cron = "0 44 17 * * ?")
  private void sendEmail() throws MessagingException {
    MimeMessage message = sender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message);

    helper.setTo("agrawalvishal1698@gmail.com");
    helper.setText("How are you?");
    helper.setSubject("Hi");

    sender.send(message);
  }
}
