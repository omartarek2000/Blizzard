package engine;
import java.util.ArrayList;
import java.util.Random;

import model.heroes.*;
public class Game
{
	private Hero firstHero;
	private Hero secondHero;
	private Hero currentHero;
	private Hero opponent;

	public Game(Hero p1, Hero p2)
	{
		ArrayList<Hero> all = new ArrayList<Hero>();
		
		all.add(p1);
		all.add(p2);
		
		firstHero=p1;
		secondHero=p2;
		
		Random rand = new Random();
		 currentHero = all.get(rand.nextInt(2));
		if(currentHero == p1)
		{
			opponent=p2;
		}
		else
		opponent=p1;
	}

	public Hero getCurrentHero() {
		return currentHero;
	}

	public Hero getOpponent() {
		return opponent;
	}
	
	
	
}