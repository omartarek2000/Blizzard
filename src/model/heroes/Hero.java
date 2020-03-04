package model.heroes;

import java.util.*;
import java.io.*;
import model.cards.*;
import model.cards.minions.*;

abstract public class Hero 
{
	private String name;
	private int currentHP;
	private boolean heroPowerUsed;
	private int totalManaCrystals;
	private int currentManaCrystals;
	private ArrayList<Card> deck;
	private ArrayList<Minion> field;
	private ArrayList<Card> hand;
	private int fatigueDamage;
	
	
	public Hero(String name)
	{
		this.name=name;
		this.deck = new ArrayList<Card>();
		currentHP=30;
	}
	
	
	public final static ArrayList<Minion> getAllNeutralMinions(String filePath) throws IOException{
		
		ArrayList<Minion> x = new ArrayList<Minion>();
		String currentLine = "";
		FileReader fileReader = new FileReader(filePath);
		BufferedReader br = new BufferedReader(fileReader);
		while ((currentLine = br.readLine()) != null) {
			String [] result = currentLine.split(",");
			Rarity rarity = null;
			switch(result[2])
			{
			case "b" : rarity = Rarity.BASIC;
			case "c" : rarity = Rarity.COMMON;
			case "r" : rarity = Rarity.RARE;
			case "e" : rarity = Rarity.EPIC;
			case "l" : rarity = Rarity.LEGENDARY;
			
			}
			x.add(new Minion(result[0], Integer.parseInt(result[1]), rarity,Integer.parseInt(result[3]), Integer.parseInt(result[4]),  Boolean.parseBoolean(result[5]),  Boolean.parseBoolean(result[6]), Boolean.parseBoolean(result[7])));
			//System.out.println(x);
		}
		
		//x.add(new Icehowl());
		
		return x;
	}
	
	public final static ArrayList<Minion> getNeutralMinions(ArrayList<Minion> minions,int count)
	{
		ArrayList<Minion> x = new ArrayList<Minion>();
		for(int i=0;i<count;i++) 
		{
			 int c=0;
			 boolean exist=false;
			 int rnd = new Random().nextInt(minions.size());
			 Minion current = minions.get(rnd);
			if((current.getRarity()).equals(Rarity.LEGENDARY)) {
				for(int k=0;k<x.size();k++) {
					 if(minions.get(rnd).equals(minions.get(k))) {
						 exist =true;
					 }
				}
			}
			else {
			 for(int j=0;j<x.size();j++) 
			 {
				 if(((Minion)(minions.get(rnd))).equals(((Minion)(x.get(j)))))
				 {
					c++; 
				 }
			 }
			}

			 if(c<2 | !exist)
				 x.add(minions.get(rnd));
			 else
				 i--;
			 
		//System.out.println(x);
		//return x;
	}
		return x;
}
	
	abstract public void buildDeck() throws IOException;

	
	
	
	public int getCurrentHP() {
		return currentHP;
	}
	    public void setCurrentHP(int currentHP)
	    {
	    	
	    	if(currentHP>30)
	    		currentHP=30;
	    	else
	    		this.currentHP = currentHP;
	    }
	public boolean isHeroPowerUsed() {
		return heroPowerUsed;
	}
	public void setHeroPowerUsed(boolean heroPowerUsed) {
		this.heroPowerUsed = heroPowerUsed;
	}
	public int getTotalManaCrystals() {
		return totalManaCrystals;
	}
	public void setTotalManaCrystals(int totalManaCrystals)
	{
		if(totalManaCrystals>10)
			this.totalManaCrystals=10;
		else
			this.totalManaCrystals = totalManaCrystals;
	}
	public int getCurrentManaCrystals() {
		return currentManaCrystals;
	}
	public void setCurrentManaCrystals(int currentManaCrystals)
	{
		if(currentManaCrystals>10)
			this.currentManaCrystals=10;
		else
			this.currentManaCrystals = currentManaCrystals;	
	}
	public String getName() {
		return name;
	}
	public ArrayList<Card> getDeck() {
		return deck;
	}
	public ArrayList<Minion> getField() {
		return field;
	}
	public ArrayList<Card> getHand() {
		return hand;
	}
	
	
	public static void main(String[] args) throws IOException
	{
		getNeutralMinions(getAllNeutralMinions("neutral_minions.csv"), 15);
		
	}
	
	


}
