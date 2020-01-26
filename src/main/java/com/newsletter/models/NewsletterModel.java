package com.newsletter.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class NewsletterModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String name;
  private float dailyprice;
  private float weeklyprice;
  private String content;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public float getDailyPrice() {
    return dailyprice;
  }

  public void setDailyPrice(float dailyprice) {
    this.dailyprice = dailyprice;
  }

  public float getWeeklyPrice() {
    return weeklyprice;
  }

  public void setWeeklyPrice(float weeklyprice) {
    this.weeklyprice = weeklyprice;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

}
