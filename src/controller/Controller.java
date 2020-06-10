package controller;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.*;

import engine.Game;
import engine.GameListener;
import exceptions.CannotAttackException;
import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotSummonedException;
import exceptions.NotYourTurnException;
import exceptions.TauntBypassException;
import model.cards.Card;
import model.cards.minions.Minion;
import model.cards.spells.*;
import model.heroes.Hero;
import model.heroes.Hunter;
import model.heroes.Mage;
import model.heroes.Paladin;
import model.heroes.Priest;
import model.heroes.Warlock;
import view.GameView;
import view.WinnerView;

public class Controller  implements GameListener, ActionListener{
	
	private Game model;
	private GameView view;
	
	private Component[] curHand;
	private Component[] curField;
	private Component[] oppField;
	//private Component[] curHand;
	
	private String p1Name;
	private String p2Name;
	private Hero[] heroes;
	
	private Spell spellAttacker = null;
	private Minion minionAttacker = null;
	private boolean usingHeroPower = false;
	
	private Minion minionTargeted  = null;
	private Hero heroTargeted  = null;
	
	private int mouseX;
	private int mouseY;
	
	
	public Controller(Hero[] heroes, String p1Name, String p2Name) {
		
		this.p1Name = p1Name;
		this.p2Name = p2Name;
		this.heroes = heroes;
		
		view = new GameView();
		
		try {
			model = new Game(heroes[0], heroes[1]);
			model.setListener(this);
		} catch (FullHandException e) {
		
			JOptionPane.showMessageDialog(view,e.getMessage());
		} catch (CloneNotSupportedException e) {
			
			JOptionPane.showMessageDialog(view,e.getMessage());
		}
		
		if(model.getCurrentHero().equals(heroes[0]))
			JOptionPane.showMessageDialog(view,p1Name+" is going to start");
		else if(model.getCurrentHero().equals(heroes[1]))
			JOptionPane.showMessageDialog(view,p2Name+" is going to start");
		
		onHandUpdated();
		
		onHeroUpdated();
		
		onManaCrystalsUpdated();
		
		onFieldUpdated();
		
		onDeckUpdated();
		
		
		view.getEndTurn().addActionListener(this);
		
		view.getReset().addActionListener(this);
		
		//view.getCurHero().add(comp)
		
		
		
		
		
	}
	
	
	public void currentHeroNameDisplayed() {
		
		if(model.getCurrentHero().equals(heroes[0]))
			JOptionPane.showMessageDialog(view,"It is the turn of "+p1Name);
		else if(model.getCurrentHero().equals(heroes[1]))
			JOptionPane.showMessageDialog(view,"It is the turn of "+p2Name);
	}
	public String winnerName() {
		if(model.getCurrentHero().getCurrentHP()==0) {
			
		if(model.getCurrentHero().equals(heroes[0]))
			return p1Name;
		else 
			return p2Name;
		
		}else if(model.getOpponent().equals(heroes[0]))
			return p1Name;
		else 
			return p2Name;
		
	}

	@Override
	public void onGameOver() {
		// TODO Auto-generated method stub
		
		
			new WinnerView(winnerName(), view);
		
	}

	@Override
	public void onHandUpdated() {
		view.getCurHand().removeAll();
		view.getOppHand().removeAll();
		
		
		for(int i=0;i<model.getCurrentHero().getHand().size();i++) {
			JButton x  = new JButton();
			if(model.getCurrentHero().getHand().get(i) instanceof Minion)
			x.setText(((Minion)model.getCurrentHero().getHand().get(i)).toStringHand());
			else
				x.setText(((Spell)model.getCurrentHero().getHand().get(i)).toStringHand());
			
			x.addActionListener(this);
			x.setFont(x.getFont().deriveFont(8f));
			view.getCurHand().add(x);
			
		}
		
		curHand = view.getCurHand().getComponents();
		
		
		
		for(int i=0;i<model.getOpponent().getHand().size();i++) {
			JButton x  = new JButton();
			x.setEnabled(false);
			x.setBackground(Color.BLUE);
			//x.setFont(x.getFont().deriveFont(9f));
			view.getOppHand().add(x);
			
		}
		
		view.revalidate();
		view.repaint();
	}

	@Override
	public void onFieldUpdated() {
		
		view.getCurField().removeAll();
		view.getOppField().removeAll();
		view.revalidate();
		view.repaint();
		
		for(int i=0;i<model.getCurrentHero().getField().size();i++) {
			JButton x  = new JButton();
			x.setText(model.getCurrentHero().getField().get(i).toStringField());
			x.addActionListener(this);
			x.setFont(x.getFont().deriveFont(8f));
			view.getCurField().add(x);
			
		}
		curField = view.getCurField().getComponents();
		
		
		
		
		for(int i=0;i<model.getOpponent().getField().size();i++) {
			JButton x  = new JButton();
			x.setText(model.getOpponent().getField().get(i).toStringField());
			x.setFont(x.getFont().deriveFont(8f));
			x.addActionListener(this);
			view.getOppField().add(x);
			
		}
		oppField = view.getOppField().getComponents();
		
	}

	@Override
	public void onHeroUpdated() {
		
		view.getCurHero().removeAll();
		view.getOppHero().removeAll();
		
		view.revalidate();
		view.repaint();

		JButton x = new JButton();
		x.setPreferredSize(new Dimension(200, 40));
		x.setText("<html>"+model.getCurrentHero().getName()+"<br>"+ "CurrentHP:   " + model.getCurrentHero().getCurrentHP() + "<html>");
		x.addActionListener(this);
		
		JButton y = new JButton();
		y.setPreferredSize(new Dimension(200, 40));
		y.setText("<html>"+model.getOpponent().getName()+"<br>"+ "CurrentHP:   " + model.getOpponent().getCurrentHP() + "<html>");
		y.addActionListener(this);
		
		view.getCurHero().add(x, BorderLayout.CENTER);
		view.getOppHero().add(y, BorderLayout.CENTER);
		
	}

	
	public void onManaCrystalsUpdated() {
		
		view.getCurManaCrystals().removeAll();
		view.getOppManaCrystals().removeAll();
		
		view.revalidate();
		view.repaint();
		
		String s="";
		
		for(int i=0;i<model.getCurrentHero().getCurrentManaCrystals();i++)
			s+="●";
		
		for(int i=0;i<10-model.getCurrentHero().getCurrentManaCrystals();i++)
			s+="○";
		
		JLabel x = new JLabel(s);
		x.setFont(x.getFont().deriveFont(20f));
		
		view.getCurManaCrystals().add(x);
		
		
		String y="";
		
		for(int i=0;i<model.getOpponent().getCurrentManaCrystals();i++)
			y+="●";
		
		for(int i=0;i<10-model.getOpponent().getCurrentManaCrystals();i++)
			y+="○";
		
		JLabel z = new JLabel(y);
		z.setFont(x.getFont().deriveFont(20f));
		
		view.getOppManaCrystals().add(z);
		
	}

	
	public void onDeckUpdated() {
		
		view.revalidate();
		view.repaint();
		
		view.getCurDeck().setText("Deck Size:\n    "+model.getCurrentHero().getDeck().size());
		
		view.getOppDeck().setText("Deck Size:\n    "+model.getOpponent().getDeck().size());
		
	}
	
	
	public void castSpell(Spell s) {
		
		
		if(s instanceof MinionTargetSpell && s instanceof HeroTargetSpell) {
			
			
			if(minionTargeted!=null) {
				try {
					model.getCurrentHero().castSpell((MinionTargetSpell) s, minionTargeted);
				} catch (NotYourTurnException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(view,e.getMessage());
				} catch (NotEnoughManaException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(view,e.getMessage());
				} catch (InvalidTargetException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(view,e.getMessage());
				}
			}else if(heroTargeted!=null) {
				try {
					model.getCurrentHero().castSpell((HeroTargetSpell) s, heroTargeted);
				} catch (NotYourTurnException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(view,e.getMessage());
				} catch (NotEnoughManaException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(view,e.getMessage());
				}
			}
			
			
		}else if(s instanceof FieldSpell) {
			try {
				model.getCurrentHero().castSpell((FieldSpell) s);
			} catch (NotYourTurnException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(view,e.getMessage());
			} catch (NotEnoughManaException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(view,e.getMessage());
			}

			
		}else if (s instanceof MinionTargetSpell) {
			
				try {
					model.getCurrentHero().castSpell((MinionTargetSpell) s, minionTargeted);
				} catch (NotYourTurnException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(view,e.getMessage());
				} catch (NotEnoughManaException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(view,e.getMessage());
				} catch (InvalidTargetException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(view,e.getMessage());
				}

			
		}

		else if (s instanceof LeechingSpell) {
			
			try {
				model.getCurrentHero().castSpell((LeechingSpell) s, minionTargeted);
			} catch (NotYourTurnException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(view,e.getMessage());
			} catch (NotEnoughManaException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(view,e.getMessage());
			}
			
			
		}else if (s instanceof HeroTargetSpell) {
			
			try {
				model.getCurrentHero().castSpell((HeroTargetSpell) s, heroTargeted);
			} catch (NotYourTurnException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(view,e.getMessage());
			} catch (NotEnoughManaException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(view,e.getMessage());
			}


		}else if (s instanceof AOESpell) {
			
			try {
				model.getCurrentHero().castSpell((AOESpell) s, model.getOpponent().getField());
			} catch (NotYourTurnException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(view,e.getMessage());
			} catch (NotEnoughManaException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(view,e.getMessage());
			}

			
		}
		
		for(int i =0;i<view.getOppHero().getComponentCount();i++)
			view.getOppHero().getComponent(i).setEnabled(true);
			
		for(int i =0;i<view.getOppField().getComponentCount();i++)
			view.getOppField().getComponent(i).setEnabled(true);
		
		for(int i =0;i<view.getCurHero().getComponentCount();i++)
			view.getCurHero().getComponent(i).setEnabled(true);
		
		for(int i =0;i<view.getCurHand().getComponentCount();i++)
			view.getCurHand().getComponent(i).setEnabled(true);
		
		for(int i =0;i<view.getCurField().getComponentCount();i++)
			view.getCurField().getComponent(i).setEnabled(true);
		
		onDeckUpdated();
		onFieldUpdated();
		onHeroUpdated();
		onManaCrystalsUpdated();
		onHandUpdated();
		
		
		spellAttacker=null;
		minionTargeted = null;
		heroTargeted = null;
			
		
	}

	
	
	public void useHeroPower() {
		
		if(model.getCurrentHero() instanceof Hunter) {
			
			try {
				((Hunter)model.getCurrentHero()).useHeroPower();
			} catch (NotEnoughManaException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(view,e.getMessage());
			} catch (HeroPowerAlreadyUsedException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(view,e.getMessage());
			} catch (NotYourTurnException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(view,e.getMessage());
			} catch (FullHandException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(view,e.getMessage());
			} catch (FullFieldException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(view,e.getMessage());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(view,e.getMessage());
			}
			
		}else if(model.getCurrentHero() instanceof Mage) {
			
			if(minionTargeted!=null) {
				
			try {
			((Mage)	model.getCurrentHero()).useHeroPower(minionTargeted);
			} catch (NotEnoughManaException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(view,e.getMessage());
			} catch (HeroPowerAlreadyUsedException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(view,e.getMessage());
			} catch (NotYourTurnException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(view,e.getMessage());
			} catch (FullHandException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(view,e.getMessage());
			} catch (FullFieldException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(view,e.getMessage());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(view,e.getMessage());
			}
			
			}else if(heroTargeted!=null) {
				
				try {
					((Mage)	model.getCurrentHero()).useHeroPower(heroTargeted);
					} catch (NotEnoughManaException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(view,e.getMessage());
					} catch (HeroPowerAlreadyUsedException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(view,e.getMessage());
					} catch (NotYourTurnException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(view,e.getMessage());
					} catch (FullHandException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(view,e.getMessage());
					} catch (FullFieldException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(view,e.getMessage());
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(view,e.getMessage());
					}
			}
			
			for(int i =0;i<view.getCurField().getComponentCount();i++)
				view.getCurField().getComponent(i).setEnabled(true);
		
		for(int i =0;i<view.getCurHand().getComponentCount();i++)
				view.getCurHand().getComponent(i).setEnabled(true);
		
		
		for(int i =0;i<view.getCurHero().getComponentCount();i++)
				view.getCurHero().getComponent(i).setEnabled(true);
			
			
			
		}else if(model.getCurrentHero() instanceof Paladin) {
			
			try {
				((Paladin)model.getCurrentHero()).useHeroPower();;
			} catch (NotEnoughManaException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(view,e.getMessage());
			} catch (HeroPowerAlreadyUsedException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(view,e.getMessage());
			} catch (NotYourTurnException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(view,e.getMessage());
			} catch (FullHandException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(view,e.getMessage());
			} catch (FullFieldException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(view,e.getMessage());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(view,e.getMessage());
			}
			
		}else if(model.getCurrentHero() instanceof Priest) {
			
			if(minionTargeted!=null) {
				
				try {
				((Priest)	model.getCurrentHero()).useHeroPower(minionTargeted);
				} catch (NotEnoughManaException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(view,e.getMessage());
				} catch (HeroPowerAlreadyUsedException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(view,e.getMessage());
				} catch (NotYourTurnException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(view,e.getMessage());
				} catch (FullHandException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(view,e.getMessage());
				} catch (FullFieldException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(view,e.getMessage());
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(view,e.getMessage());
				}
				
				}else if(heroTargeted!=null) {
					
					try {
						((Priest)	model.getCurrentHero()).useHeroPower(heroTargeted);
						} catch (NotEnoughManaException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view,e.getMessage());
						} catch (HeroPowerAlreadyUsedException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view,e.getMessage());
						} catch (NotYourTurnException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view,e.getMessage());
						} catch (FullHandException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view,e.getMessage());
						} catch (FullFieldException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view,e.getMessage());
						} catch (CloneNotSupportedException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(view,e.getMessage());
						}
				}
			
			
			for(int i =0;i<view.getOppField().getComponentCount();i++)
				view.getOppField().getComponent(i).setEnabled(false);


			for(int i =0;i<view.getOppHero().getComponentCount();i++)
				view.getOppHero().getComponent(i).setEnabled(false);
			
			for(int i =0;i<view.getCurHand().getComponentCount();i++) {
				
				if(model.getCurrentHero().getHand().get(i) instanceof Spell)
					view.getCurHand().getComponent(i).setEnabled(true);
				
			}
			
			
		}else if(model.getCurrentHero() instanceof Warlock) {
			
			try {
				((Warlock)model.getCurrentHero()).useHeroPower();;
			} catch (NotEnoughManaException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(view,e.getMessage());
			} catch (HeroPowerAlreadyUsedException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(view,e.getMessage());
			} catch (NotYourTurnException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(view,e.getMessage());
			} catch (FullHandException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(view,e.getMessage());
			} catch (FullFieldException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(view,e.getMessage());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(view,e.getMessage());
			}
			
		}
		
		minionTargeted = null;
		heroTargeted = null;
		usingHeroPower = false;
		
		
		onDeckUpdated();
		onFieldUpdated();
		onHeroUpdated();
		onManaCrystalsUpdated();
		onHandUpdated();
		
		
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		
		int card = 0;
		
		JButton source = (JButton)e.getSource();
		JPanel x = (JPanel) source.getParent();
		

		
		if(source.equals(view.getReset())) {
			
			minionTargeted = null;
			heroTargeted = null;
			usingHeroPower = false;
			minionAttacker = null;
			spellAttacker = null;
			
			onDeckUpdated();
			onFieldUpdated();
			onHeroUpdated();
			onManaCrystalsUpdated();
			onHandUpdated();
			
		}
		
		if(x.equals(view.getCurHand())) {
			
			for(int i =0;i<curHand.length;i++) {
				if(source.equals(curHand[i])) {
					card = i;
				}
					
			}
			if(model.getCurrentHero( ) instanceof Priest) {
				if(model.getCurrentHero().getHand().get(card) instanceof Minion) {
					if(usingHeroPower) {
						minionTargeted = (Minion)model.getCurrentHero().getHand().get(card);
						useHeroPower();
						return;
					}
					
				}
			}
			
			if(model.getCurrentHero().getHand().get(card) instanceof Minion) {
				try {
					model.getCurrentHero().playMinion((Minion)model.getCurrentHero().getHand().get(card));
				} catch (NotYourTurnException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(view,e1.getMessage());
				} catch (NotEnoughManaException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(view,e1.getMessage());
					
				} catch (FullFieldException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(view,e1.getMessage());
				}
				
				onDeckUpdated();
				onFieldUpdated();
				onHeroUpdated();
				onManaCrystalsUpdated();
				onHandUpdated();
				
			}
			else if(model.getCurrentHero().getHand().get(card) instanceof Spell) {
				
				
				if(model.getCurrentHero().getHand().get(card) instanceof FieldSpell || model.getCurrentHero().getHand().get(card) instanceof AOESpell) {
					castSpell((Spell)model.getCurrentHero().getHand().get(card));
					
				}
				else {
					
					int res;
					if(model.getCurrentHero().getHand().get(card) instanceof HeroTargetSpell && model.getCurrentHero().getHand().get(card) instanceof MinionTargetSpell) {
						res = JOptionPane.showConfirmDialog(view, "Do you want cast this Spell? \n If yes please choose a Hero or a minion to be Targeted.","Cast a Spell", JOptionPane.YES_NO_OPTION);
						
					}
					else if(model.getCurrentHero().getHand().get(card) instanceof HeroTargetSpell) {
						res = JOptionPane.showConfirmDialog(view, "Do you want cast this Spell? \n If yes please choose the Hero Targeted.","Cast a Spell", JOptionPane.YES_NO_OPTION);
						
					}
					else
						res = JOptionPane.showConfirmDialog(view, "Do you want cast this Spell? \n If yes please choose the Minion Targeted.","Cast a Spell", JOptionPane.YES_NO_OPTION);
					
					if(res == JOptionPane.YES_OPTION) {
						
					spellAttacker = (Spell) model.getCurrentHero().getHand().get(card);
					

					for(int i =0;i<view.getCurHand().getComponentCount();i++)
						((JButton)view.getCurHand().getComponent(i)).setEnabled(false);
					
					if(model.getCurrentHero().getHand().get(card) instanceof DivineSpirit) {
						
						for(int i =0;i<view.getOppField().getComponentCount();i++)
							view.getOppField().getComponent(i).setEnabled(false);
	
						for(int i =0;i<view.getOppHero().getComponentCount();i++)
							view.getOppHero().getComponent(i).setEnabled(false);
						
						for(int i =0;i<view.getCurHero().getComponentCount();i++)
							view.getCurHero().getComponent(i).setEnabled(false);
						
						for(int i =0;i<view.getCurHand().getComponentCount();i++)
							view.getCurHand().getComponent(i).setEnabled(false);
						
					}else if(model.getCurrentHero().getHand().get(card) instanceof KillCommand) {
						
						for(int i =0;i<view.getCurHero().getComponentCount();i++)
							view.getCurHero().getComponent(i).setEnabled(false);
						
						for(int i =0;i<view.getCurHand().getComponentCount();i++)
							view.getCurHand().getComponent(i).setEnabled(false);
						
						for(int i =0;i<view.getCurField().getComponentCount();i++)
							view.getCurField().getComponent(i).setEnabled(false);
						
					}else if(model.getCurrentHero().getHand().get(card) instanceof Polymorph) {
						
						for(int i =0;i<view.getOppHero().getComponentCount();i++)
							view.getOppHero().getComponent(i).setEnabled(false);
						
						for(int i =0;i<view.getCurHero().getComponentCount();i++)
							view.getCurHero().getComponent(i).setEnabled(false);
						
						for(int i =0;i<view.getCurHand().getComponentCount();i++)
							view.getCurHand().getComponent(i).setEnabled(false);
						
					}else if(model.getCurrentHero().getHand().get(card) instanceof Pyroblast) {
						
						for(int i =0;i<view.getCurHero().getComponentCount();i++)
							view.getCurHero().getComponent(i).setEnabled(false);
						
						for(int i =0;i<view.getCurHand().getComponentCount();i++)
							view.getCurHand().getComponent(i).setEnabled(false);
						
						for(int i =0;i<view.getCurField().getComponentCount();i++)
							view.getCurField().getComponent(i).setEnabled(false);
						
					}else if(model.getCurrentHero().getHand().get(card) instanceof SealOfChampions) {
						
						for(int i =0;i<view.getOppField().getComponentCount();i++)
							view.getOppField().getComponent(i).setEnabled(false);
	
						for(int i =0;i<view.getOppHero().getComponentCount();i++)
							view.getOppHero().getComponent(i).setEnabled(false);
						
						for(int i =0;i<view.getCurHero().getComponentCount();i++)
							view.getCurHero().getComponent(i).setEnabled(false);
						
						for(int i =0;i<view.getCurHand().getComponentCount();i++)
							view.getCurHand().getComponent(i).setEnabled(false);
						
					}else if(model.getCurrentHero().getHand().get(card) instanceof ShadowWordDeath) {
						
						for(int i =0;i<view.getOppHero().getComponentCount();i++)
							view.getOppHero().getComponent(i).setEnabled(false);
						
						for(int i =0;i<view.getCurHero().getComponentCount();i++)
							view.getCurHero().getComponent(i).setEnabled(false);
						
						for(int i =0;i<view.getCurHand().getComponentCount();i++)
							view.getCurHand().getComponent(i).setEnabled(false);
						
						for(int i =0;i<view.getCurField().getComponentCount();i++)
							view.getCurField().getComponent(i).setEnabled(false);
						
					}else if(model.getCurrentHero().getHand().get(card) instanceof SiphonSoul) {
						
						for(int i =0;i<view.getOppHero().getComponentCount();i++)
							view.getOppHero().getComponent(i).setEnabled(false);
						
						for(int i =0;i<view.getCurHero().getComponentCount();i++)
							view.getCurHero().getComponent(i).setEnabled(false);
						
						for(int i =0;i<view.getCurHand().getComponentCount();i++)
							view.getCurHand().getComponent(i).setEnabled(false);
						
						for(int i =0;i<view.getCurField().getComponentCount();i++)
							view.getCurField().getComponent(i).setEnabled(false);
							
					}
					
					}else {
						spellAttacker = null;
					}
					
					
				
					view.revalidate();
					view.repaint();
				//castSpell((Spell)model.getCurrentHero().getHand().get(card));
			}	
			
			}
			
				
		}
		
		if(source.equals(view.getEndTurn())) {
			
			//System.out.println("dd");
			try {
				model.getCurrentHero().endTurn();
			} catch (FullHandException e1) {
				// TODO Auto-generated catch block
				Card c = e1.getBurned();
				String s = (c instanceof Minion)?((Minion)c).toStringField():((Spell)c).toStringHand();
				JOptionPane.showMessageDialog(view,e1.getMessage()+"\n"+s);
			} catch (CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			currentHeroNameDisplayed();
			
			onDeckUpdated();
			onFieldUpdated();
			onHeroUpdated();
			onManaCrystalsUpdated();
			onHandUpdated();
			
			spellAttacker = null;
			minionTargeted = null;
			heroTargeted = null;
			minionAttacker = null;
			usingHeroPower = false;
			
		}
		
		
		
		if(x.equals(view.getOppField())) {
					
			for(int i =0;i<oppField.length;i++) {
				if(source.equals(oppField[i])) {
					card = i;
				}
							
			}
			
			minionTargeted = model.getOpponent().getField().get(card);
			
			if(minionAttacker!=null) {
				try {
					model.getCurrentHero().attackWithMinion(minionAttacker, minionTargeted);
				} catch (CannotAttackException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(view,e1.getMessage());
				} catch (NotYourTurnException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(view,e1.getMessage());
				} catch (TauntBypassException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(view,e1.getMessage());
				} catch (InvalidTargetException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(view,e1.getMessage());
				} catch (NotSummonedException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(view,e1.getMessage());
				}
				
				
				minionAttacker = null;
				minionTargeted = null;
				
				
				onDeckUpdated();
				onFieldUpdated();
				onHeroUpdated();
				onManaCrystalsUpdated();
				onHandUpdated();
				
			}
			else if(spellAttacker!=null) {
				castSpell(spellAttacker);
				
			}else if(usingHeroPower) {
				useHeroPower();
			}
					
		}
		
		if(x.equals(view.getCurField())) {
			
			for(int i =0;i<curField.length;i++) {
				if(source.equals(curField[i])) {
					card = i;
				}
							
			}
			
			
			
			if(spellAttacker!=null) {
				minionTargeted = model.getCurrentHero().getField().get(card);
				castSpell(spellAttacker);
				
			}else if(usingHeroPower) {
				minionTargeted = model.getCurrentHero().getField().get(card);
				useHeroPower();
			}else {
				int res = JOptionPane.showConfirmDialog(view, "Do you want to attack with this Minion?","Attack With Minion", JOptionPane.YES_NO_OPTION);
				
				if(res == JOptionPane.YES_OPTION) {
				minionAttacker = model.getCurrentHero().getField().get(card);
				
				for(int i =0;i<view.getCurField().getComponentCount();i++)
					view.getCurField().getComponent(i).setEnabled(false);
			
				for(int i =0;i<view.getCurHand().getComponentCount();i++)
					view.getCurHand().getComponent(i).setEnabled(false);
			
			
				for(int i =0;i<view.getCurHero().getComponentCount();i++)
					view.getCurHero().getComponent(i).setEnabled(false);
				
				
				}
			}
			
					
		}
					
		
		
		
		
		
		if(x.equals(view.getOppHero())) {
			
			heroTargeted = model.getOpponent();
			
			if(minionAttacker!=null) {
				
					try {
						model.getCurrentHero().attackWithMinion(minionAttacker, heroTargeted);
					} catch (CannotAttackException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(view,e1.getMessage());
					} catch (NotYourTurnException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(view,e1.getMessage());
					} catch (TauntBypassException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(view,e1.getMessage());
					} catch (NotSummonedException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(view,e1.getMessage());
					} catch (InvalidTargetException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(view,e1.getMessage());
					}
				
				
				
				minionAttacker = null;
				heroTargeted = null;
				
				
				onDeckUpdated();
				onFieldUpdated();
				onHeroUpdated();
				onManaCrystalsUpdated();
				onHandUpdated();
				
			}
			
			else if(spellAttacker!=null) {
				
				castSpell(spellAttacker);
			
			}else if(usingHeroPower) {
				
				useHeroPower();
				
			}
			
		}
		
		if(x.equals(view.getCurHero())) {
			
			if(usingHeroPower) {
				heroTargeted = model.getCurrentHero();
				useHeroPower();
				
			}
			
			if(model.getCurrentHero() instanceof Hunter) {
				int res = JOptionPane.showConfirmDialog(view, "Do you want use Hero Power?","Hero Power", JOptionPane.YES_NO_OPTION);
				
				if(res == JOptionPane.YES_OPTION) {
					usingHeroPower = true;
					useHeroPower();
				}
				
				
			}else if(model.getCurrentHero() instanceof Mage) {
				
				int res = JOptionPane.showConfirmDialog(view, "Do you want use Hero Power? \n If yes please choose the Minion or Hero Targeted.","Hero Power", JOptionPane.YES_NO_OPTION);
				
				if(res == JOptionPane.YES_OPTION) {
					usingHeroPower = true;
					
					
					for(int i =0;i<view.getCurField().getComponentCount();i++)
							view.getCurField().getComponent(i).setEnabled(false);
					
					for(int i =0;i<view.getCurHand().getComponentCount();i++)
							view.getCurHand().getComponent(i).setEnabled(false);
					
					
					for(int i =0;i<view.getCurHero().getComponentCount();i++)
							view.getCurHero().getComponent(i).setEnabled(false);
					

				
					
				}
				
			}else if(model.getCurrentHero() instanceof Paladin) {
				
				int res = JOptionPane.showConfirmDialog(view, "Do you want use Hero Power?","Hero Power", JOptionPane.YES_NO_OPTION);
				
				if(res == JOptionPane.YES_OPTION) {
					usingHeroPower = true;
					useHeroPower();
				}
				
			}else if(model.getCurrentHero() instanceof Priest) {
				
				int res = JOptionPane.showConfirmDialog(view, "Do you want use Hero Power? \n If yes please choose the Minion or Hero Targeted.","Hero Power", JOptionPane.YES_NO_OPTION);
				
				if(res == JOptionPane.YES_OPTION) {
					usingHeroPower = true;
					
					
				
					for(int i =0;i<view.getOppField().getComponentCount();i++)
						view.getOppField().getComponent(i).setEnabled(false);


					for(int i =0;i<view.getOppHero().getComponentCount();i++)
						view.getOppHero().getComponent(i).setEnabled(false);
				
					
					for(int i =0;i<view.getCurHand().getComponentCount();i++) {
						
						if(model.getCurrentHero().getHand().get(i) instanceof Spell)
							view.getCurHand().getComponent(i).setEnabled(false);
						
					}
			
					
				}
				
			}else if(model.getCurrentHero() instanceof Warlock) {
				
				int res = JOptionPane.showConfirmDialog(view, "Do you want use Hero Power?","Hero Power", JOptionPane.YES_NO_OPTION);
				
				if(res == JOptionPane.YES_OPTION) {
					usingHeroPower = true;
					useHeroPower();
					
				}
				
			}
			
			
			
		}

		
		
		
}


}
