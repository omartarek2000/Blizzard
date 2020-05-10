package model.heroes;

import exceptions.FullHandException;

public interface HeroListener {
	public void onHeroDeath();

	public void damageOpponent(int amount);

	public void endTurn() throws FullHandException, CloneNotSupportedException;
	
	public void updateHand();

	public void updateField();

	public void updateHero();
	
	public void updateManaCrystals();
	
	public void updateDeck();
	

}
