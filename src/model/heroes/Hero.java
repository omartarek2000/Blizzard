package model.heroes;

import java.util.*;
import java.io.*;
import model.cards.*;
import model.cards.minions.*;

abstract public class Hero 
{
	private String name;
	private int currenHP;
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
	}
	
	
	static ArrayList<Minion> getAllNeutralMinions(String filePath) throws IOException{
		
		ArrayList<Minion> x = new ArrayList<Minion>();
		String currentLine = "";
		FileReader fileReader= new FileReader(filePath);
		BufferedReader br = new BufferedReader(fileReader);
		while ((currentLine = br.readLine()) != null) {
			String [] result= currentLine.split(",");
			switch(result[2])
			{
				
			}
			x.push(Minion(result[0], result[1], result[2], result[3], result[4], result[5], result[6], result[7]));
		//System.out.println(br.readLine());
		}
		return x;
	}
	
	
	
	public int getCurrenHP() {
		return currenHP;
	}
	public void setCurrenHP(int currenHP) {
		this.currenHP = currenHP;
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
	public void setTotalManaCrystals(int totalManaCrystals) {
		this.totalManaCrystals = totalManaCrystals;
	}
	public int getCurrentManaCrystals() {
		return currentManaCrystals;
	}
	public void setCurrentManaCrystals(int currentManaCrystals) {
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
		getAllNeutralMinions("neutral_minions.csv");
	}
	
	


}
