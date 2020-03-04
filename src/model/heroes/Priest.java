package model.heroes;
import java.io.IOException;
import java.util.ArrayList;

import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.*;

public class Priest extends Hero 
{
	public Priest() throws IOException
	{
		super("Anduin Wrynn");
		buildDeck();
	}	
	public void buildDeck() throws IOException
	{
		ArrayList<Minion> nm = getNeutralMinions(getAllNeutralMinions("neutral_minions.csv"), 13);
		
		for(int i=0; i<nm.size();i++)
		{
			getDeck().add(nm.get(i));
		}
		getDeck().add(new DivineSpirit());
		getDeck().add(new DivineSpirit());
		getDeck().add(new HolyNova());
		getDeck().add(new HolyNova());
		getDeck().add(new ShadowWordDeath());
		getDeck().add(new ShadowWordDeath());
		getDeck().add(new Minion("Prophet Velen", 7, Rarity.LEGENDARY, 7, 7, false, false, false));
	}
}
