package game_ui;
import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import game_logic.Board;


// show tanks attack at the bottom of GUI
@SuppressWarnings("serial")
public class TanksAttack extends JPanel{
	
	private Board game;
	private boolean isFirstGame = true;

	public TanksAttack(Board game) {
		this.game = game;
		displayDamages();
	}

	private void displayDamages() {
		setLayout(new BorderLayout());
		String output_text = getLinesDamages();
		final JLabel TankAttacks = new JLabel(output_text);
		Board.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				updateStuff(TankAttacks);
				
			}
				
		});
		TankAttacks.setFocusable(false);
		add(TankAttacks);
	}

	private String getLinesDamages() {
		String stuff = "";
		ArrayList<Integer> damages = game.getTankDamage_notFire();  
		for (int d : damages){
			if (d > 0) {
				stuff = stuff + "You were shot for " + d + "!<br>";
			}
		}
		String output_text = "<html><body>" + stuff + "</body></html>";
		if (isFirstGame){
			isFirstGame = false;
			return " ";
		}
		else{
			
			return output_text;
		}
	}
	
	private void updateStuff(JLabel TanksShit){
		TanksShit.setText(getLinesDamages());
	}

}
