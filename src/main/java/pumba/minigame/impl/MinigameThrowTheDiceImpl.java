package main.java.pumba.minigame.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import main.java.pumba.minigame.MiniGame;

public class MinigameThrowTheDiceImpl implements MiniGame
{

	private final static Integer coinsMultiplier = 100;
	
	@Override
	public Map<String, Integer> play(List<String> players)
	{
		Random dice = new Random();
		
		Map<String, Integer> mapCoins = new HashMap<String, Integer>(); 
		
		for(String player : players) {
			mapCoins.put(player, (dice.nextInt(6) + 1) * coinsMultiplier);
		}
		
		
		return mapCoins;
	}

}
