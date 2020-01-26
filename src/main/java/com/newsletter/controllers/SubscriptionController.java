package com.newsletter.controllers;

import com.newsletter.bos.SubscriptionBo;
import com.newsletter.constants.SubscriptionConstants;
import com.newsletter.dtos.SubscriptionDto;
import com.newsletter.mappers.SubscriptionObjectMapper;
import com.newsletter.services.impl.SubscriptionServiceImpl;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Subscription controller.
 */
@RestController
public class SubscriptionController {

  @Autowired
  private SubscriptionServiceImpl subscriptionService;


  @Autowired
  private static SubscriptionObjectMapper subscriptionObjectMapper;


  /**
   * Gets all subscriptions.
   *
   * @return the all subscriptions
   */
  @GetMapping(path = "/get-all-subscriptions")
  public List<SubscriptionDto> getAllSubscriptions() {
    List<SubscriptionBo> allSubscriptions = subscriptionService.getAllSubscriptions();
    return subscriptionObjectMapper.getSubscriptionResponseFromBoList(allSubscriptions);
  }

  /**
   * Buy a subscription response entity.
   *
   * @param subscriptionDto the subscription dto
   * @return the response entity
   */
  @PostMapping(path = "/buy-a-subscription")
  public ResponseEntity<String> buyASubscription(@RequestBody SubscriptionDto subscriptionDto) {
    subscriptionService.buyASubscription(subscriptionObjectMapper.dtoToBo(subscriptionDto));
    return new ResponseEntity<>(SubscriptionConstants.SUCCESSFULLY_BOUGHT, HttpStatus.OK);
  }

  /**
   * Cancel subscription response entity.
   *
   * @param id the id
   * @return the response entity
   */
  @PutMapping(path = "/cancel-subscription/{id}")
  public ResponseEntity<String> cancelSubscription(@PathVariable long id) {
    boolean response = subscriptionService.cancelSubscription(id);
    if (response) {
      return new ResponseEntity<>(SubscriptionConstants.SUCCESSFULLY_DONE, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(SubscriptionConstants.TRY_LATER, HttpStatus.BAD_REQUEST);
    }
  }

  /**
   * Renew subscription response entity.
   *
   * @param id          the id
   * @param dailyweekly the dailyweekly
   * @param date        the date
   * @return the response entity
   */
  @PutMapping(path = "/re-new-subscription/{id}/{dailyweekly}")
  public ResponseEntity<String> renewSubscription(@PathVariable long id,
      @PathVariable String dailyweekly,
      @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
    boolean response = subscriptionService.renewSubscription(id, date, dailyweekly);
    if (response) {
      return new ResponseEntity<>(SubscriptionConstants.SUCCESSFULLY_DONE, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(SubscriptionConstants.TRY_LATER, HttpStatus.BAD_REQUEST);
    }
  }

  /**
   * Filter subscriptions list.
   *
   * @param filterType the filter type
   * @return the list
   */
  @GetMapping(path = "/filter-subscriptions")
  public List<SubscriptionDto> filterSubscriptions(@RequestParam String filterType) {
    final List<SubscriptionBo> subscriptionOfType =
        subscriptionService.getSubscriptionOfType(filterType);
    return subscriptionObjectMapper.getSubscriptionResponseFromBoList(subscriptionOfType);
  }

  /**
   * Gets sorted subscription.
   *
   * @param type     the type
   * @param sortType the sort type
   * @return the sorted subscription
   */
  @GetMapping(path = "/sort-subscriptions")
  public List<SubscriptionDto> getSortedSubscription(@RequestParam final String type,
      @RequestParam final String sortType) {
    return subscriptionObjectMapper.getSubscriptionResponseFromBoList(
        subscriptionService.getSortedSubscriptions(type, sortType));
  }


  /**
   * Send content through email.
   */
  @Scheduled(cron = SubscriptionConstants.CRON)
  public void sendContentThroughEmail() {
    subscriptionService.sendContentThroughEmail();
  }

  /**
   * Gets my subscriptions.
   *
   * @param email the email
   * @return the my subscriptions
   */
  @GetMapping(path = "/get-user-all-subscriptions")
  public List<SubscriptionDto> getMySubscriptions(@RequestParam String email) {
    return subscriptionObjectMapper
        .getSubscriptionResponseFromBoList(subscriptionService.getMySubscriptions(email));
  }
}

