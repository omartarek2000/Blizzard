package engine;
import java.util.Random;

import model.heroes.*;
abstract public class Game
{
	private Hero firstHero;
	private Hero secondHero;
	private Hero currentHero;
	private Hero opponent;

	public Game(Hero p1, Hero p2)
	{
		firstHero=p1;
		secondHero=p2;
		int rnd = new Random().nextInt(1);
		if(rnd==0)
		{
			currentHero=p1;
			opponent=p2;
		}
		else
			currentHero=p2;
		opponent=p1;
	}
	
}