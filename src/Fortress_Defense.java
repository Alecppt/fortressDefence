import game_logic.Board;
import game_ui.TextUI;

/*Fortress_Defense class , where the main() is 
 * to invoke game logic and ui object. 
 * also, control game process.  
 * 
 */
public class Fortress_Defense {

	public static void main(String[] args) {
		final int ROW = 10;
		final int COLUMN = 10;
		final int FORTRESS_HP = 1500;
		final int NUMBER_TANKS = 5;
		final int TANK_CELLS = 4;
		boolean isGameOver = false;
		Board game = new Board(ROW, COLUMN, FORTRESS_HP, 
				NUMBER_TANKS, TANK_CELLS);
		TextUI ui = new TextUI(game);
		while(!isGameOver){
			//ui.showTanks();
			ui.printBoard();
			if(game.isWon() == 0 ){
				if(ui.getUserInput()){

					ui.doesFortressHit(game.isFortressHit(ui.getUser_row(), ui.getUser_col()));
					ui.turnToTank(game.getEachTankDamage());
				}
			}
			else {
				isGameOver = true;
				ui.gameOver(game.isWon());
			}
		}
	}
}
