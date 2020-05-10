package view;


import java.awt.BorderLayout;

import java.awt.*;
import java.awt.GridLayout;

import javax.swing.*;


public class GameView extends JFrame {
	private JPanel currentHero;
	private JPanel opponent;
	private JPanel oppField;
	private JPanel oppHand;
	private JPanel oppHeroMana;
	private JPanel curField;
	private JPanel curHand;
	private JPanel curHeroMana;
	
	private JPanel curManaCrystals;
	private JPanel curHero;
	
	private JPanel oppManaCrystals;
	private JPanel oppHero;
	
	private JPanel curhandDeck;
	private JPanel opphandDeck;
	
	//private JPanel deck;
	private JLabel curDeck;
	private JLabel oppDeck;
	
	private JPanel endPanel;
	private JButton endTurn;
	
	private JButton reset;

	
	public GameView() {
		
	
		this.setBounds(20,20,1280,720);
		//this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("HearthStone");
		
		currentHero = new JPanel();
		currentHero.setPreferredSize(new Dimension(this.getWidth()-20,335));
		
		opponent = new JPanel();
		opponent.setPreferredSize(new Dimension(this.getWidth()-20,335));
		
//		deck = new JPanel();
//		deck.setPreferredSize(new Dimension(50,this.getHeight()-10));
//		deck.setLayout(new GridLayout(0,1));
		

		
		this.add(currentHero, BorderLayout.CENTER);
		this.add(opponent, BorderLayout.NORTH);

		//this.add(deck, BorderLayout.EAST);
		

		
		oppHeroMana = new JPanel();
		oppHeroMana.setBorder(BorderFactory.createTitledBorder("Opponent Hero && Mana Crystals"));
		oppHeroMana.setPreferredSize(new Dimension(this.getWidth()-30,100));
		opponent.add(oppHeroMana, BorderLayout.NORTH);
		
		oppHero = new JPanel();
		oppHero.setBorder(BorderFactory.createTitledBorder("Hero"));
		oppHero.setPreferredSize(new Dimension(650,70));
		oppHeroMana.add(oppHero, BorderLayout.CENTER);
		
		oppManaCrystals = new JPanel();
		oppManaCrystals.setBorder(BorderFactory.createTitledBorder("Mana Crystals"));
		oppManaCrystals.setPreferredSize(new Dimension(250,70));
		oppHeroMana.add(oppManaCrystals, BorderLayout.EAST);
		
		opphandDeck = new JPanel();
		opphandDeck = new JPanel();
		opphandDeck.setBorder(BorderFactory.createTitledBorder("Hand && Deck"));
		opphandDeck.setPreferredSize(new Dimension(this.getWidth()-30,100));
		opponent.add(opphandDeck, BorderLayout.CENTER);
		
		oppHand = new JPanel();
		oppHand.setBorder(BorderFactory.createTitledBorder("Hand"));
		oppHand.setPreferredSize(new Dimension(this.getWidth()-150,70));
		oppHand.setLayout(new GridLayout(0, 10));
		opphandDeck.add(oppHand, BorderLayout.WEST);
		
		oppField = new JPanel();
		oppField.setBorder(BorderFactory.createTitledBorder("Field"));
		oppField.setPreferredSize(new Dimension(this.getWidth()-30,105));
		oppField.setLayout(new GridLayout(0, 7));
		opponent.add(oppField, BorderLayout.SOUTH);
		
		curField = new JPanel();
		curField.setBorder(BorderFactory.createTitledBorder("Field"));
		curField.setPreferredSize(new Dimension(this.getWidth()-30,105));
		curField.setLayout(new GridLayout(0,7));
		currentHero.add(curField, BorderLayout.NORTH);
		
		curhandDeck = new JPanel();
		curhandDeck = new JPanel();
		curhandDeck.setBorder(BorderFactory.createTitledBorder("Hand && Deck"));
		curhandDeck.setPreferredSize(new Dimension(this.getWidth()-30,130));
		currentHero.add(curhandDeck, BorderLayout.CENTER);
		
		curHand = new JPanel();
		curHand.setBorder(BorderFactory.createTitledBorder("Hand"));
		curHand.setPreferredSize(new Dimension(this.getWidth()-150,100));
		curHand.setLayout(new GridLayout(0,10));
		curhandDeck.add(curHand, BorderLayout.WEST);
		
		curHeroMana = new JPanel();
		curHeroMana.setBorder(BorderFactory.createTitledBorder("Current Hero"));
		curHeroMana.setPreferredSize(new Dimension(this.getWidth()-30,95));
		curHeroMana.setLayout(new FlowLayout());
		currentHero.add(curHeroMana, BorderLayout.SOUTH);
		
		curHero = new JPanel();
		curHero.setBorder(BorderFactory.createTitledBorder("Hero"));
		curHero.setPreferredSize(new Dimension(650,68));
		curHeroMana.add(curHero);
		
		curManaCrystals = new JPanel();
		curManaCrystals.setBorder(BorderFactory.createTitledBorder("Mana Crystals"));
		curManaCrystals.setPreferredSize(new Dimension(250,65));
		curHeroMana.add(curManaCrystals);
		
		endTurn = new JButton();
		endTurn.setText("End Turn");
		curHeroMana.add(endTurn);
		
		reset = new JButton();
		reset.setText("Reset");
		curHeroMana.add(reset);
		
		oppDeck = new JLabel("OppoNent Deck");
		oppDeck.setPreferredSize(new Dimension(100, 60));
		oppDeck.setBorder(BorderFactory.createTitledBorder("Deck"));
		oppDeck.setText("Opponent Deck");
		
		curDeck = new JLabel("Current Deck");
		curDeck.setPreferredSize(new Dimension(100, 90));
		curDeck.setBorder(BorderFactory.createTitledBorder("Deck"));
		curDeck.setText("Current Deck");

		
		opphandDeck.add(oppDeck, BorderLayout.EAST);
		curhandDeck.add(curDeck, BorderLayout.EAST);
		
		this.revalidate();
		this.repaint();
		
	}

	public JPanel getOppField() {
		return oppField;
	}

	public JPanel getOppHand() {
		return oppHand;
	}

	public JPanel getOppHero() {
		return oppHero;
	}

	public JPanel getCurField() {
		return curField;
	}

	public JPanel getCurHand() {
		return curHand;
	}

	public JPanel getCurHero() {
		return curHero;
	}

	public JLabel getCurDeck() {
		return curDeck;
	}

	public JLabel getOppDeck() {
		return oppDeck;
	}

	public JButton getEndTurn() {
		return endTurn;
	}

	public JPanel getOppHeroMana() {
		return oppHeroMana;
	}

	public JPanel getCurHeroMana() {
		return curHeroMana;
	}

	public JPanel getCurManaCrystals() {
		return curManaCrystals;
	}

	public JPanel getOppManaCrystals() {
		return oppManaCrystals;
	}
	

	public JButton getReset() {
		return reset;
	}

	public static void main(String[] args) {
		new GameView();
	}

	
	

}
