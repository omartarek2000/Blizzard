package model.cards;

abstract public class Card 
{
	private String name;
	private int manaCost;
	private Rarity rarity;
	
	public Card(String name,int manaCost,Rarity rarity)
	{
		if(manaCost>0 && manaCost<10) 
		{
			this.name=name;
			this.manaCost=manaCost;
			this.rarity=rarity;
		
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getManaCost() {
		return manaCost;
	}

	public void setManaCost(int manaCost) {
		this.manaCost = manaCost;
	}

	public Rarity getRarity() {
		return rarity;
	}
	
}
