package model.heroes;

import java.io.IOException;
import java.util.ArrayList;

import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.*;

public class Warlock extends Hero 
{
	public Warlock()
	{
		super("Gul'dan");
	}
	public void buildDeck() throws IOException
	{
		ArrayList<Minion> nm = getNeutralMinions(getAllNeutralMinions("neutral_minions.csv"), 13);
		
		for(int i=0; i<nm.size();i++)
		{
			getDeck().add(nm.get(i));
		}
		getDeck().add(new CurseOfWeakness());
		getDeck().add(new CurseOfWeakness());
		getDeck().add(new SiphonSoul());
		getDeck().add(new SiphonSoul());
		getDeck().add(new TwistingNether());
		getDeck().add(new TwistingNether());
		getDeck().add(new Minion("Wilfred Fizzlebang",6, Rarity.LEGENDARY, 4, 4, false, false, false));
	}
}
