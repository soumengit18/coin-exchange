package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.CoinServiceDao;
import com.example.demo.model.CoinInventory;
import com.example.demo.services.CoinDelegateService;

@RestController
@RequestMapping("/coin/service")
public class ServiceController {

	private static final Logger log = LoggerFactory.getLogger(ServiceController.class);

	@Autowired
	private CoinServiceDao coinServiceDao;
	
	@Autowired
	private CoinDelegateService coinDelegateService;

	@GetMapping("/getChange")
	public ResponseEntity<String> getChange(@RequestParam(value = "billAmount", required = false) Double billAmount) {
		if (log.isInfoEnabled()) {
			log.info("ServiceController.getChange << ");
		}
		if (billAmount == null || billAmount == 0) {
			return new ResponseEntity<String>("Bill Amount Sent was NULL / ZERO", HttpStatus.BAD_REQUEST);
		}

		CoinInventory coinInventory = coinServiceDao.getCoinsMap();
		
		if (log.isInfoEnabled()) {
			log.info("ServiceController.getChange >> Coins fetched from DB {}", coinInventory);
		}
		coinDelegateService.getCoins(coinInventory, billAmount);
		if (log.isInfoEnabled()) {
			log.info("ServiceController.getChange >> Changes Found {} current coins map {}", coinInventory.getChangeMap(), coinInventory.getCoinsMap());
		}

		if (log.isInfoEnabled()) {
			log.info("ServiceController.getChange >> ");
		}
		return new ResponseEntity<String>("Coins You Got", HttpStatus.OK);
	}

}
