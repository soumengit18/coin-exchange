package com.example.demo.dao;

import com.example.demo.model.CoinInventory;

public interface CoinRepository {
	CoinInventory findAllCoins();
}
