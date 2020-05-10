package view;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

import javax.swing.*;

public class StartView extends JFrame implements  ActionListener {

	public StartView() {
	this.setBounds(20,20,1280,720);
	this.setVisible(true);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setTitle("HearthStone");
	
	JPanel view = new JPanel();
	view.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
	view.setLayout(new GridLayout(0,1));
	this.add(view, BorderLayout.CENTER);
	
	JButton start = new JButton("Start Game");
	start.addActionListener(this);
	start.setPreferredSize(new Dimension(200,200));
	view.add(start);;
	
	JButton exit = new JButton("Exit");
	exit.addActionListener(this);
	exit.setPreferredSize(new Dimension(200,200));
	view.add(exit);
	
	
	
	}
	
	public static void main(String[] args) {
		new StartView();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton b = (JButton) e.getSource();
		
		if(b.getActionCommand()=="Start Game") {
			new ChooseHeroView();
			this.setVisible(false);
		}
		else
			System.exit(0);
		
	}
	
	
	
}
