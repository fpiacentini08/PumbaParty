package main.java.pumba.minigame.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import main.java.pumba.log.Log;
import main.java.pumba.minigame.MiniGame;

public class MinigameThrowTheDiceImpl implements MiniGame
{

	private final static Integer coinsMultiplier = 100;

	@Override
	public Map<String, Integer> play(List<String> players)
	{
		Random dice = new Random();

		Map<String, Integer> mapCoins = new HashMap<String, Integer>();

		for (String player : players)
		{
			Log.debug(player + " comienza a jugar");
			
			mapCoins.put(player, (dice.nextInt(6) + 1) * coinsMultiplier);
			
			Log.debug(player + " obtuvo " + mapCoins.get(player) + " monedas");
		}

		return mapCoins;
	}

}
