package com.newsletter.exceptions;

import com.newsletter.constants.NewsletterConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NewsletterNotFoundException extends Exception {

  private static final Logger LOGGER = LoggerFactory.getLogger(NewsletterNotFoundException.class);

  public NewsletterNotFoundException() {

  }

  public NewsletterNotFoundException(String msg) {
    LOGGER.error(msg);
  }
}
