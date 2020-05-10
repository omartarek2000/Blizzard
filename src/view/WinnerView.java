package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WinnerView extends JFrame implements ActionListener{
	
	GameView v;
	public WinnerView(String s, GameView v) {
		
		this.v = v;
		
		this.setBounds(0, 0, 500, 150);
		this.setVisible(true);
		this.setLocationRelativeTo(v);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("HearthStone");

		
		JPanel message = new JPanel();
		message.setPreferredSize(new Dimension(this.getWidth(), 100));
		this.add(message, BorderLayout.CENTER);
		
		
		JLabel x= new JLabel();
		x.setText("The Winner Is "+s);
		x.setFont(x.getFont().deriveFont(30f));
		
		message.add(x, BorderLayout.CENTER);
		
		JPanel buttons = new JPanel();
		buttons .setPreferredSize(new Dimension(this.getWidth(), 50));
		
		this.add(buttons, BorderLayout.SOUTH);		
		
		JButton playAgain = new JButton("Play Again");
		playAgain.addActionListener(this);
		buttons.add(playAgain);
		
		JButton exit = new JButton("Exit");
		exit.addActionListener(this);
		buttons.add(exit);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton x = (JButton) e.getSource();
		
		if(x.getActionCommand()=="Play Again") {
			
			new StartView();
			this.setVisible(false);
			v.setVisible(false);
			
		}
		
		if(x.getActionCommand()=="Exit") {
			System.exit(0);
		}
		
		
	}

	
	
	


}
