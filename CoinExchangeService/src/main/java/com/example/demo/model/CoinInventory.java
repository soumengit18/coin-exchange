package com.example.demo.model;

import java.util.Map;

public class CoinInventory {
	Map<Double, Integer> coinsMap;
	Map<Double, Integer> changeMap;

	public Map<Double, Integer> getCoinsMap() {
		return coinsMap;
	}

	public void setCoinsMap(Map<Double, Integer> coinsMap) {
		this.coinsMap = coinsMap;
	}

	public Map<Double, Integer> getChangeMap() {
		return changeMap;
	}

	public void setChangeMap(Map<Double, Integer> changeMap) {
		this.changeMap = changeMap;
	}

	@Override
	public String toString() {
		return "CoinInventory [coinsMap=" + coinsMap + ", changeMap=" + changeMap + "]";
	}
}
