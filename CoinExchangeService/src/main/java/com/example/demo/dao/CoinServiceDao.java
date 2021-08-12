package com.example.demo.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.model.CoinInventory;

@Service("coinServiceDao")
public class CoinServiceDao {
	
	private static final Logger log = LoggerFactory.getLogger(CoinServiceDao.class);
	
	@Autowired
    @Qualifier("jdbcCoinRepository") 
	private JdbcCoinRepository jdbcCoinRepository;
	
	public CoinInventory getCoinsMap() {
		if(log.isInfoEnabled()) {
			log.info("CoinServiceDao.getCoinsMap << ");
		}
		return jdbcCoinRepository.findAllCoins();
	}
}
