package game_ui;
import game_logic.Board;

import java.util.ArrayList;
import java.util.Scanner;

/*Text UI class handles, validate store user's input
 * and by reading game logic module, this class is responsible for 
 * printing results to the console
 */

public class TextUI {
	private int user_row;
	private int user_col;
	private Board game;
	private String[] alphabet_uppercase = {"A","B","C","D","E","F","G","H","I","J","K","L",
			"M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	private Scanner scanner = new Scanner(System.in);
	public TextUI(Board board){
		game = board; 
		System.out.println("******************************************************************************************");
		System.out.println("\t\t\t\tWelcome to Fortress Defense!");
		System.out.println("\t\t\t\tby Alec Chen and Jason Yang");
		System.out.println("******************************************************************************************");
		System.out.println("");
	}
	public boolean getUserInput(){

		boolean isAlphabet_found = false;
		boolean isColSet = false;
		boolean isInputCorrect = false;
		
		try{
			while(!isInputCorrect){
				isAlphabet_found = false;
				isColSet = false;
				System.out.print("Enter your move: ");
				
				String input = scanner.nextLine().toUpperCase();
				
				
				if((input.length() == 2&&isInt(input.substring(1,2))||
						(input.length() == 3
						&&isInt(input.substring(1,3))))){//check only 2-3char cna proceed
					String row = input.substring(0,1);
					for (int i = 0; i < game.getRow(); i++){//check 1st is char
						if(alphabet_uppercase[i].equals(row)){
							isAlphabet_found = true;
							setUser_row(i);
						}
					}
					if(input.length() == 2){
						
					}
					int col = Integer.parseInt(input.substring(1));
					if (col <= 0 || col > game.getCol()){
						isColSet = false;
					}
					else{
						setUser_col(col-1);
						isColSet = true;
					}
				}
				
				if(isAlphabet_found&&isColSet){
					isInputCorrect = true;
					
				}
				else{
					isInputCorrect = false;
					re_requestInput();
				}
			}
		}
		catch(Exception e) {
			re_requestInput();
//			getUserInput();
		}
//		scanner.close();
		System.out.println("");
		return isInputCorrect;
	}
	private boolean isInt(String s){
		try{
			Integer.parseInt(s);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	public void doesFortressHit(int isHit){
		/*1= hit , 0 = tank cell is already hit, -1 = missed*/
		if(isHit > 0){
			System.out.println("HIT!");
		}
		else if (isHit == 0){
			System.out.println("The part of tank is already hit!");
		}
		else{
			System.out.println("Miss.");
		}
		
	}
	public void turnToTank(ArrayList<Integer> damages){
		for(int d : damages){
			if(d > 0){
				System.out.println("You were shot for " + d + "!");				
			}
		}
		System.out.println("");
	}
	public void gameOver(int isWon){
		if(isWon > 0){//lose
			System.out.println("I'm sorry, your fortress has been smashed!");
			System.out.println("");
			
			showTanks();
			scanner.close();
		}
		else {
			System.out.println("Congratulation! You Won!");
			
		}
	}
	public void setUserRow(int r){

	}
	public void setUserCol(int c){

	}
	public void showTanks(){
		printFirst_line_Board();
		
		for(int i = 0; i<game.getRow(); i++){	
			System.out.print("   " + alphabet_uppercase[i] + " ");
			for(int j = 0; j < game.getCol(); j++){
				System.out.printf("%s ", game.afterGameCells(i, j));
			}
			System.out.println("");
		}
		System.out.println("Fortress Structure Left: " + game.getFortress().getHp());
	}
	public void printBoard(){

		printFirst_line_Board();

		/*cells of game board starts here
		 * first column is for labeling each row */
		for(int i = 0; i < game.getRow(); i++){	
			System.out.print("   " + alphabet_uppercase[i] + " ");
			for(int j = 0; j < game.getCol(); j++){
				System.out.printf("%s ", game.inGameCells(i, j));
			}
			System.out.println("");
		}
		System.out.println("Fortress Structure Left: "+ game.getFortress().getHp());

	}
	private void printFirst_line_Board() {
		System.out.println("Game board:");
		/*print the first line of the game board
		 * prints game.getCol+1 columns
		 * first row is for labeling each column*/
		for (int i = 0; i <= game.getCol(); i++){
			if (i == 0){
				System.out.print("   " + " " + " ");
			}else {
				System.out.print((i) + " ");
			}
		}
		System.out.println("");
	}
	public int getUser_row() {
		return user_row;
	}
	public void setUser_row(int user_row) {
		this.user_row = user_row;
	}
	public int getUser_col() {
		return user_col;
	}
	public void setUser_col(int user_col) {
		this.user_col = user_col;
	}

	private void re_requestInput() {
		System.out.println("Invalid target. Please enter a corrdinate such as D10.");
		
	}
}
