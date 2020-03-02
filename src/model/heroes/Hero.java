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
	
	
	public static void readFile(String path) throws IOException{
		
		//String currentLine = "";
		FileReader fileReader= new FileReader(path);
		BufferedReader br = new BufferedReader(fileReader);
		//while ((currentLine = br.readLine()) != null) {
		System.out.println(br.readLine());
		System.out.println(br.readLine());
		//}
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
		readFile("C:\\Users\\user\\eclipse-workspace\\Blizzard\\src\\model\\heroes\\neutral_minions.csv");
	}
	
	


}
