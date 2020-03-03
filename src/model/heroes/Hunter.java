package model.heroes;

import java.io.IOException;
import java.util.ArrayList;

import model.cards.Rarity;
import model.cards.minions.*;
import model.cards.spells.*;

public class Hunter extends Hero 
{
	public Hunter()
	{
		super ("Rexxar");
	}
	public void buildDeck() throws IOException
	{
		ArrayList<Minion> nm = getNeutralMinions(getAllNeutralMinions("neutral_minions.csv"), 15);
		
		for(int i=0; i<nm.size();i++)
		{
			getDeck().add(nm.get(i));
		}
		getDeck().add(new KillCommand());
		getDeck().add(new KillCommand());
		getDeck().add(new MultiShot());
		getDeck().add(new MultiShot());
		getDeck().add(new Minion("King Krush", 9, Rarity.LEGENDARY, 8, 8, false, false, true));
		System.out.print(getDeck().size());
		
	}
	
	public static void main(String[] args) throws IOException
	{
		Hunter x = new Hunter();
		x.buildDeck();
	}
}
