package model.heroes;

import java.io.IOException;
import java.util.ArrayList;

import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.Flamestrike;
import model.cards.spells.Polymorph;
import model.cards.spells.Pyroblast;

public class Mage extends Hero 
{
	public Mage()
	{
		super("Jaina Proudmoore");
	}

public void buildDeck() throws IOException
{
	ArrayList<Minion> nm = getNeutralMinions(getAllNeutralMinions("neutral_minions.csv"), 13);
	
	for(int i=0; i<nm.size();i++)
	{
		getDeck().add(nm.get(i));
	}
	getDeck().add(new Polymorph());
	getDeck().add(new Polymorph());
	getDeck().add(new Flamestrike());
	getDeck().add(new Flamestrike());
	getDeck().add(new Pyroblast());
	getDeck().add(new Minion("Kalycgos", 10, Rarity.LEGENDARY, 4, 12, false, false, false));
	System.out.print(getDeck().size());
	
}
}