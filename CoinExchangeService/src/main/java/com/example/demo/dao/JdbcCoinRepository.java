package com.example.demo.dao;

import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CoinInventory;

@Repository
public class JdbcCoinRepository implements CoinRepository{
	
	private static final Logger log = LoggerFactory.getLogger(JdbcCoinRepository.class);

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Override
	public CoinInventory findAllCoins() {
		if(log.isInfoEnabled()) {
			log.info("JdbcCoinRepository.findAllCoins << ");
		}
		CoinInventory coinInventory = null;
		Map<Double, Integer> coinsMap = new TreeMap<>();
		
		jdbcTemplate.query("select COIN_VAL, COIN_COUNT from COIN_INVENTORY", 
				(rs, rowNum) ->
					coinsMap.put(rs.getDouble("COIN_VAL"), rs.getInt("COIN_COUNT"))
				);
		if(!coinsMap.isEmpty()) {
			coinInventory = new CoinInventory();
			coinInventory.setCoinsMap(coinsMap);
		}
		if(log.isInfoEnabled()) {
			log.info("JdbcCoinRepository.findAllCoins >> ");
		}
		return coinInventory;
	}

}
