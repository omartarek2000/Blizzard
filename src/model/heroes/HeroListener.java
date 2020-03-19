package model.heroes;

import engine.GameListener;
import exceptions.*;

public interface HeroListener extends GameListener {
	public void onHeroDeath();
	
	public void damageOpponent(int amount);
	
	public void endTurn() throws FullHandException, CloneNotSupportedException;
	
	

}
