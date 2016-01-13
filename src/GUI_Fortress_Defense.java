import game_logic.Board;
import game_ui.Graphical_UI;

//main class of the game using GUI
public class GUI_Fortress_Defense {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final int ROW = 10;
		final int COLUMN = 10;
		final int FORTRESS_HP = 1500;
		final int NUMBER_TANKS = 5;
		final int TANK_CELLS = 4;
		Board game = new Board(ROW, COLUMN, FORTRESS_HP, 
				NUMBER_TANKS, TANK_CELLS);
		Graphical_UI ui = new Graphical_UI("Fortress_Defense", game);
		ui.playgame();
	}

}
