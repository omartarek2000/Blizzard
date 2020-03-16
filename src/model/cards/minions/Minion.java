package model.cards.minions;

import model.cards.*;

public class Minion extends Card
{
	private int attack;
	private int maxHP;
	private int currentHP;
	private boolean taunt;
	private boolean divine;
	private boolean sleeping;
	private boolean attacked;
	
	
	
	
	public Minion(String name,int manaCost,Rarity rarity, int attack,int maxHP,boolean taunt,boolean divine,boolean charge)
			{
					super(name,manaCost,rarity);
					
					if(attack<0)
						this.attack=0;
					else
						this.attack=attack;
					
					this.maxHP=maxHP;
					this.currentHP=maxHP;
					this.taunt=taunt;
					this.divine=divine;
					
					if(charge==true)
					{
						sleeping=false;
					}
					else
						sleeping=true;
					
					
			
			}




	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		if(attack<0 )
			this.attack=0;
		else
			this.attack=attack;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}

	public int getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(int currentHP) {
		if(currentHP>maxHP)
			this.currentHP=maxHP;
		else
			this.currentHP = currentHP;
	}

	public boolean isTaunt() {
		return taunt;
	}

	public void setTaunt(boolean taunt) {
		this.taunt = taunt;
	}

	public boolean isDivine() {
		return divine;
	}

	public void setDivine(boolean divine) {
		this.divine = divine;
	}

	public boolean isSleeping() {
		return sleeping;
	}

	public void setSleeping(boolean sleeping) {
		this.sleeping = sleeping;
	}

	public boolean isAttacked() {
		return attacked;
	}

	public void setAttacked(boolean attacked) {
		this.attacked = attacked;
	}
    




}
