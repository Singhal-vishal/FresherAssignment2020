package com.newsletter.dao;

import com.newsletter.entities.NewsletterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsletterRepository extends JpaRepository<NewsletterEntity, Integer> {

}
