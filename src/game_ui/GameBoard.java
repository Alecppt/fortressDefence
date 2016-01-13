package game_ui;

//import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import game_logic.Board;
import game_logic.Cell;

//show grid layout of cells in the Center of GUI


@SuppressWarnings("serial")
public class GameBoard extends JPanel{
	private Board game;
	private JLabel [][] cells;
	private JFrame frame;
	private static final int IMAGE_SIZE = 90;
	private static ImageIcon hit  = getScaleImageIcon(new ImageIcon("images/hit.png"), IMAGE_SIZE, IMAGE_SIZE);
	private static ImageIcon miss  = getScaleImageIcon(new ImageIcon("images/miss.png"), IMAGE_SIZE, IMAGE_SIZE);
	private static ImageIcon fog  = getScaleImageIcon(new ImageIcon("images/fog.png"), IMAGE_SIZE, IMAGE_SIZE);
	private static ImageIcon tank  = getScaleImageIcon(new ImageIcon("images/tank.png"), IMAGE_SIZE, IMAGE_SIZE);
	private static ImageIcon field  = getScaleImageIcon(new ImageIcon("images/field.jpg"), IMAGE_SIZE, IMAGE_SIZE);


	public GameBoard(Board game, JFrame frame){
		this.game = game;
		this.frame = frame;
		createBoard();
	}

	private void createBoard() {
		cells = new JLabel[game.getRow()][game.getCol()];
		setLayout(new GridLayout(game.getRow(), game.getCol()));

		for (int i = 0; i < game.getRow(); i++){
			for ( int j = 0; j < game.getCol(); j++){
				final int row = i;
				final int col = j;
				JLabel image = new JLabel();
				cells[i][j] = image; 
				cells[i][j].addMouseListener( new MouseListener(){

					@Override
					public void mouseClicked(MouseEvent arg0) {
						shootDatTank(row, col);

					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub

					}					
				});	
				Board.addChangeListener(new ChangeListener(){

					@Override
					public void stateChanged(ChangeEvent e) {

						if (game.isWon() == 0){ // game in progress					
							displayInGame(row ,col);

							//							displayAfterGame(i, j);

						}
						else if (game.isWon() > 0){ // lose 
							showAfterGame();
							JOptionPane.showMessageDialog(frame, "Sorry Your fortress is smashed!");
							System.exit(0);
						}
						else { //win

							JOptionPane.showMessageDialog(frame, "You won!");
							System.exit(0);
						}
					}

				});
				if (game.isWon() == 0){ // game in progress					
					displayInGame(i ,j);

					//					displayAfterGame(i, j);

				}
				else if (game.isWon() > 0){ // lose 
					showAfterGame();
					//					JOptionPane.showMessageDialog(frame, "you loser");
				}
				else { //win

					//					JOptionPane.showMessageDialog(frame, "fuck you");
				}

				add(cells[i][j]);
			}

		}

	}

	private void showAfterGame(){
		//		cells = new JLabel[game.getRow()][game.getCol()];
		//		setLayout(new GridLayout(game.getRow(), game.getCol()));
		for (int i = 0; i < game.getRow(); i++){
			for ( int j = 0; j < game.getCol(); j++){

				//				JLabel image = new JLabel();
				//				cells[i][j] = image; 
				displayAfterGame(i, j);
				//				add(cells[i][j]);
			}
		}

	}

	protected void shootDatTank(int i, int j) {
		game.isFortressHit(i, j);
		ArrayList<Integer> damages = game.getEachTankDamage();


	}

	private void displayInGame(int i, int j) {
		String symbol = game.inGameCells(i, j);
		//		Cell cell = game.getCell(i, j);
		if (symbol == "X") {
			cells[i][j].setIcon(hit);
		}
		else if (symbol == "."){
			cells[i][j].setIcon(miss);
		}
		else if (symbol == "~"){
			cells[i][j].setIcon(fog);
		}

	}

	private void displayAfterGame(int i, int j) {
		String symbol = game.inGameCells(i, j);
		//		Cell cell = game.getCell(i, j);
		if(!game.getCell(i, j).getHasBeenAttacked() && game.getCell(i, j).getIsTank()){  //healthy tank
			cells[i][j].setIcon(tank);
		}
		else if(symbol.equals(".")){ //Miss
			cells[i][j].setIcon(miss);
		}
		else if(symbol.equals("~")){ // Field
			cells[i][j].setIcon(field);
		}
		else if(symbol.equals("X") ){ //HIT
			cells[i][j].setIcon(hit);

		}
	}

	static public ImageIcon getScaleImageIcon(ImageIcon icon, int width, int height) {
		return new ImageIcon(getScaledImage(icon.getImage(), width, height));
	}
	static private Image getScaledImage(Image srcImg, int width, int height){
		BufferedImage resizedImg =
				new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();
		g2.setRenderingHint(
				RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, width, height, null);
		g2.dispose();
		return resizedImg;
	}


}
