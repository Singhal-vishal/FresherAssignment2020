package com.newsletter.newsletter.web;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newsletter.newsletter.model.Subscription;
import com.newsletter.newsletter.service.SubscriptionService;

@RestController
public class SubscriptionController {
	@Autowired SubscriptionService subscriptionService;
	
	@GetMapping(path="/getAllSubscriptions")
	public List<Subscription> getAllSubscriptions(){
		return subscriptionService.getAllSubscriptions();
	}
	
	@PostMapping(path="/buyASubscription")
	public ResponseEntity<String> buyASubscription(@RequestBody Subscription s){
		int response = subscriptionService.buyASubscription(s);
		if(response == 1) {
			return new ResponseEntity<>("Successfully bought.",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Sorry buy again later.",HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping(path="/cancelSubscription/{id}")
	public ResponseEntity<String> cancelSubscription(@PathVariable long id){
		int response = subscriptionService.cancelSubscription(id);
		if(response == 1) {
			return new ResponseEntity<>("Successfully canceled.",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Sorry try again later.",HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping(path="/renewSubscription/{id}/{dailyweekly}")
	public ResponseEntity<String> cancelSubscription(@PathVariable long id,@PathVariable String dailyweekly,@RequestParam("date") 
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date){
		int response = subscriptionService.renewSubscription(id,date,dailyweekly);
		if(response == 1) {
			return new ResponseEntity<>("Successfully canceled.",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Sorry try again later.",HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(path="/filterSubscriptions")
	public List<Subscription> filterSubscriptions(@RequestParam String filterType){
		List<Subscription> ll = subscriptionService.getSubscriptionOfType(filterType);
		return ll;
	}
	
	@GetMapping(path="/getSubscriptions")
	public List<Subscription> getSortedSubscription(@RequestParam String type,@RequestParam String sortType){
		return subscriptionService.sortSubscriptions(type, sortType);
	}
}

