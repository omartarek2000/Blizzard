package model.cards.spells;

import model.cards.Card;
import model.cards.Rarity;

public abstract class Spell extends Card {

	public Spell(String n, int m, Rarity r) {
		super(n, m, r);
	}
	
	public String toStringHand() {
		String s="";
		s+="<html>"+getName()+"<br>";
		s+="ManaCost: "+ getManaCost()+"<br>";
		s+=getRarity()+"<html>";
		
		return s;
	}

}
