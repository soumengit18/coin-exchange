package com.example.demo.services;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.model.CoinInventory;

@Service
public class CoinDelegateService {

	private static final Logger log = LoggerFactory.getLogger(CoinDelegateService.class);
	
	public void getCoins(CoinInventory coinInventory, Double billAmount){
		if(log.isInfoEnabled()) {
			log.info("CoinDelegateService.getCoins << bill amount {}", billAmount);
		}
		if(billAmount == 0) {
			return;
		}
		if (coinInventory != null && coinInventory.getCoinsMap() != null && !coinInventory.getCoinsMap().isEmpty()) {
			if(coinInventory.getCoinsMap().containsKey(billAmount)) {
				coinInventory.getChangeMap().put(billAmount, 1);
				return;
			} else {
				for(Map.Entry<Double, Integer> entry : coinInventory.getCoinsMap().entrySet()) {
					if(billAmount > entry.getKey() && entry.getValue() > 0) {
						coinInventory.getCoinsMap().put(entry.getKey(), (entry.getValue()-1));
						billAmount -= entry.getKey();
						if (coinInventory.getChangeMap() != null
								&& coinInventory.getChangeMap().containsKey(entry.getKey())) {
							coinInventory.getChangeMap().put(entry.getKey(), coinInventory.getChangeMap().get(entry.getKey())+1);
						} else {
							coinInventory.setChangeMap(new HashMap<>());
							coinInventory.getChangeMap().put(entry.getKey(),1);
						}
						break;
					} 
				}
				getCoins(coinInventory, billAmount);
			}
				
		}
		if(log.isInfoEnabled()) {
			log.info("CoinDelegateService.getCoins << ");
		}
	}
}
