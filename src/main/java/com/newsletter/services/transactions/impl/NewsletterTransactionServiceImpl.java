package com.newsletter.services.transactions.impl;

import com.newsletter.bos.NewsletterBo;
import com.newsletter.constants.NewsletterConstants;
import com.newsletter.dao.NewsletterRepository;
import com.newsletter.entities.NewsletterEntity;
import com.newsletter.exceptions.NewsletterNotFoundException;
import com.newsletter.mappers.NewsletterObjectMapper;
import com.newsletter.services.INewsletterService;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("PMD.DataflowAnomalyAnalysis")
public class NewsletterTransactionServiceImpl implements INewsletterService {

  @Autowired
  private NewsletterRepository newsletterRepository;

  @Autowired
  private NewsletterObjectMapper newsletterObjectMapper;

  @Override
  public List<NewsletterBo> getAllNewslettersDetails() {
    return newsletterObjectMapper.subscriptionBoFromEntityList(newsletterRepository.findAll());
  }

  @Override
  @Transactional
  public void saveNewsletter(NewsletterBo newsletterBo) {
    NewsletterEntity newsletter = new NewsletterEntity();
    newsletter.setName(newsletterBo.getName());
    newsletter.setContent(newsletterBo.getContent());
    newsletter.setDailyPrice(newsletterBo.getDailyPrice());
    newsletter.setWeeklyPrice(newsletterBo.getWeeklyPrice());
    newsletterRepository.save(newsletter);
  }

  @Override
  @Transactional
  public void editNewsletter(NewsletterBo newsletterBo) throws NewsletterNotFoundException {
    boolean isDeleted = deleteNewsletter(newsletterBo.getId());
    if (isDeleted) {
      newsletterRepository.save(newsletterObjectMapper.boToEntity(newsletterBo));
    } else {
      throw new NewsletterNotFoundException(NewsletterConstants.NOT_FOUND);
    }
  }

  @Override
  @Transactional
  public boolean deleteNewsletter(int id) {
    Optional<NewsletterEntity> newsletterEntity = newsletterRepository.findById(id);
    if (newsletterEntity.isPresent()) {
      newsletterRepository.deleteById(id);
      return true;
    }
    return false;
  }

  @Override
  @Transactional
  public boolean addContent(int id, NewsletterBo newsletterBo) {
    try {
      Optional<NewsletterEntity> newsletterId = newsletterRepository.findById(id);
      if (newsletterId.isPresent()) {
        NewsletterEntity newletter = newsletterId.get();
        newletter.setContent(newsletterBo.getContent());
        newsletterRepository.save(newletter);
      }
      return true;
    } catch (Exception e) {
      return false;
    }

  }

  @Override
  @Transactional
  public String getContent(int id) {
    Optional<NewsletterEntity> newsletter = newsletterRepository.findById(id);
    if (newsletter.isPresent()) {
      NewsletterEntity newl = newsletter.get();
      return newl.getContent();
    }
    return null;
  }
}
