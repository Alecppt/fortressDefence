package game_ui;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;

import game_logic.Board;

//main GUI class 

public class Graphical_UI extends JFrame{
	private Board game; 
	private String game_title;
	
	public Graphical_UI(String name, Board board){
		game = board;
		game_title = name;
		
	}
	
	public void playgame() {
		setLayout(new BorderLayout());
		setTitle(game_title);
		addGameStuff();
		setSize(930, 1000);
	
		pack();
		setVisible(true);
	}

	private void addGameStuff() {
		FortressHP fortressHP = new FortressHP(game);
		GameBoard gameBoard = new GameBoard(game, this);
		TanksAttack tankAttacks = new TanksAttack(game);
		
		fortressHP.setFocusable(false);
		gameBoard.setFocusable(false);
		tankAttacks.setFocusable(false);
		
		add(fortressHP, BorderLayout.NORTH);
		add(gameBoard, BorderLayout.CENTER);
		add(tankAttacks, BorderLayout.SOUTH);
		
	}

	
	

	
	

}
