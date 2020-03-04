package model.heroes;

import java.io.IOException;
import java.util.ArrayList;

import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.LevelUp;
import model.cards.spells.SealOfChampions;

public class Paladin extends Hero 
{
	public Paladin() throws IOException
	{
		super("Uther Lightbringer");
		buildDeck();
	}

	public void buildDeck() throws IOException
	{
		ArrayList<Minion> nm = getNeutralMinions(getAllNeutralMinions("neutral_minions.csv"), 15);
		
		for(int i=0; i<nm.size();i++)
		{
			getDeck().add(nm.get(i));
		}
		getDeck().add(new SealOfChampions());
		getDeck().add(new SealOfChampions());
		getDeck().add(new LevelUp());
		getDeck().add(new LevelUp());
		
	getDeck().add(new Minion("Tirion Fordring", 4, Rarity.LEGENDARY, 6, 6, true, true, false));
		System.out.print(getDeck().size());
		
	}










}
