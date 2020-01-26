package com.newsletter.services;

import com.newsletter.bos.NewsletterBo;
import com.newsletter.exceptions.NewsletterNotFoundException;
import java.util.List;

public interface INewsletterService {

  List<NewsletterBo> getAllNewslettersDetails();

  void saveNewsletter(NewsletterBo dto);

  void editNewsletter(NewsletterBo dto) throws NewsletterNotFoundException;

  boolean deleteNewsletter(int id);

  boolean addContent(int id, NewsletterBo dto);

  String getContent(int id);
}
