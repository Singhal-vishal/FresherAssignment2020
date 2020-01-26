package com.newsletter.mappers;

import com.newsletter.bos.NewsletterBo;
import com.newsletter.dtos.NewsletterDto;
import com.newsletter.entities.NewsletterEntity;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NewsletterObjectMapper {

  NewsletterBo dtoToBo(NewsletterDto newsletterDto);

  NewsletterBo entityToBo(NewsletterEntity newsletterEntity);

  NewsletterEntity boToEntity(NewsletterBo newsletterBo);

  NewsletterDto boToDto(NewsletterBo newsletterBo);

  List<NewsletterBo> subscriptionBoFromEntityList(List<NewsletterEntity> newsletterEntityList);

  List<NewsletterDto> getSubscriptionResponseFromBoList(List<NewsletterBo> newsletterBoList);
}
