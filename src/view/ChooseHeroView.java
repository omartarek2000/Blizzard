package view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import controller.Controller;
import model.heroes.*;

public class ChooseHeroView extends JFrame implements ActionListener {

	private JPanel player1;
	private JPanel player2;
	
	private String p1Name;
	private String p2Name;
	
	private JLabel p1Text;
	private JLabel p2Text;
	private JPanel p1Buttons;
	private JPanel p2Buttons;
	private JPanel p1Done;
	private JPanel p2Done;
	
	private Hero[] selectedHeroes;
	
	private JButton hunter1;
	private JButton mage1;
	private JButton paladin1;
	private JButton priest1;
	private JButton warlock1;
	
	private JButton hunter2;
	private JButton mage2;
	private JButton paladin2;
	private JButton priest2;
	private JButton warlock2;
	
	private JButton done1;
	private JButton done2;
	
	private boolean donep1 =false;
	private boolean donep2 =false;
	
	public ChooseHeroView() {
		
		selectedHeroes = new Hero[2];
		
		this.setBounds(20,20,1280,720);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(0,2));
		this.setTitle("HearthStone");
		
		player1 = new JPanel();
		player1.setPreferredSize(new Dimension(this.getWidth()/2, this.getHeight()));
		//player1.setLayout(new GridLayout(0,1));
		this.add(player1);
		
		player2 = new JPanel();
		player2.setPreferredSize(new Dimension(this.getWidth()/2, this.getHeight()));
		//player1.setLayout(new GridLayout(0,1));
		this.add(player2);
		
		p1Text = new JLabel();
		p1Text.setPreferredSize(new Dimension((this.getWidth()/2)-10, 50));
		//p1Text.setEditable(false);
		
		p1Text.setFont(p1Text.getFont().deriveFont(20f));
		
		p2Text = new JLabel();
		p2Text.setPreferredSize(new Dimension((this.getWidth()/2)-10, 50));
		//p2Text.setEditable(false);
		
		p2Text.setFont(p2Text.getFont().deriveFont(20f));
		
		
		player1.add(p1Text, BorderLayout.NORTH);
		player2.add(p2Text, BorderLayout.NORTH);
		
		p1Buttons = new JPanel();
		p1Buttons.setPreferredSize(new Dimension((this.getWidth()/2)-10,515));
		p1Buttons.setLayout(new GridLayout(0,2));
		
		p2Buttons = new JPanel();
		p2Buttons.setPreferredSize(new Dimension((this.getWidth()/2)-10,515));
		p2Buttons.setLayout(new GridLayout(0,2));
		
		player1.add(p1Buttons, BorderLayout.CENTER);
		player2.add(p2Buttons, BorderLayout.CENTER);
		
		p1Done = new JPanel();
		p1Done.setPreferredSize(new Dimension((this.getWidth()/2)-10,100));
		p1Done.setLayout(new GridLayout(0,1));
		
		p2Done = new JPanel();
		p2Done.setPreferredSize(new Dimension((this.getWidth()/2)-10,100));
		p2Done.setLayout(new GridLayout(0,1));
		
		player1.add(p1Done, BorderLayout.SOUTH);
		player2.add(p2Done, BorderLayout.SOUTH);
		
		hunter1 = new JButton();
		mage1 = new JButton();
		paladin1 = new JButton();
		priest1 = new JButton();
		warlock1 = new JButton();
		
		hunter1.setText("Hunter");
		mage1.setText("Mage");
		paladin1.setText("Paladin");
		priest1.setText("Priest");
		warlock1.setText("Warlock");
		
		hunter1.addActionListener(this);
		mage1.addActionListener(this);
		paladin1.addActionListener(this);
		priest1.addActionListener(this);
		warlock1.addActionListener(this);
		
		hunter2 = new JButton();
		mage2 = new JButton();
		paladin2 = new JButton();
		priest2 = new JButton();
		warlock2 = new JButton();
		
		hunter2.setText("Hunter");
		mage2.setText("Mage");
		paladin2.setText("Paladin");
		priest2.setText("Priest");
		warlock2.setText("Warlock");
		
		hunter2.addActionListener(this);
		mage2.addActionListener(this);
		paladin2.addActionListener(this);
		priest2.addActionListener(this);
		warlock2.addActionListener(this);
		
		p1Buttons.add(hunter1);
		p2Buttons.add(hunter2);

		p1Buttons.add(mage1);
		p2Buttons.add(mage2);

		p1Buttons.add(paladin1);
		p2Buttons.add(paladin2);

		p1Buttons.add(priest1);
		p2Buttons.add(priest2);

		p1Buttons.add(warlock1);
		p2Buttons.add(warlock2);
		
		done1 = new JButton();
		done2 = new JButton();
		
		done1.setText("Done");
		done2.setText("Done");
		done1.addActionListener(this);
		done2.addActionListener(this);
		
		p1Done.add(done1);
		p2Done.add(done2);
		
		this.revalidate();
		this.repaint();
		
		 p1Name = JOptionPane.showInputDialog(this,"Enter First Player Name");
		 p2Name = JOptionPane.showInputDialog(this,"Enter Second Player Name");
		 
		 p1Text.setText(p1Name);
		 p2Text.setText(p2Name);
		
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		JButton b = (JButton) e.getSource();
		try {
		if(b.equals(hunter1))
			selectedHeroes[0] =new Hunter();
		else if(b.equals(mage1))
			selectedHeroes[0] =new Mage();
		else if(b.equals(paladin1))
			selectedHeroes[0] =new Paladin();
		else if(b.equals(priest1))
			selectedHeroes[0] =new Priest();
		else if(b.equals(warlock1))
			selectedHeroes[0] =new Warlock();
	
		else if(b.equals(hunter2))
			selectedHeroes[1] = new Hunter();
		else if(b.equals(mage2))
			selectedHeroes[1] = new Mage();
		else if(b.equals(paladin2))
			selectedHeroes[1] = new Paladin();
		else if(b.equals(priest2))
			selectedHeroes[1] = new Priest();
		else if(b.equals(warlock2))
			selectedHeroes[1] = new Warlock();
		
		else if(b.equals(done1)) {
			donep1=true;
			if(donep1&&donep2) {
				if(selectedHeroes[0]!=null && selectedHeroes[1]!=null) {
					this.setVisible(false);
				new Controller(selectedHeroes, p1Name, p2Name);
				
				}
				else
					donep1=false;
			}
			
		}
			
		else if(b.equals(done2)){
			
			donep2=true;
			if(donep1&&donep2) {
				if(selectedHeroes[0]!=null && selectedHeroes[1]!=null) {
					this.setVisible(false);
				new Controller(selectedHeroes, p1Name, p2Name);
				
				}
				else
					donep2=false;
			}
		}
		
		}
		catch (CloneNotSupportedException c) {
			c.printStackTrace();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		
		
	
		
	}

	public Hero[] getSelectedHeroes() {
		return selectedHeroes;
	}
	

	
}
