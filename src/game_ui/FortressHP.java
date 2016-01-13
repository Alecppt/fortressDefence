package game_ui;
import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import game_logic.Board;


//show Fortress's HP at the top of GUI
@SuppressWarnings("serial")
public class FortressHP extends JPanel {
	private Board game;
	
	public FortressHP(Board game) {
		this.game = game;
		displayHP();
	}

	private void displayHP() {
		setLayout(new BorderLayout());
		String stuff = "Fortress HP" + game.getFortress().getHp();
		final JLabel HPtext = new JLabel(stuff);
		Board.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0){
				updateStuff(HPtext);
			}
		});
		HPtext.setFocusable(false);
		add(HPtext);
	}

	private void updateStuff(JLabel HPlabel){
		HPlabel.setText("Fortress HP" + game.getFortress().getHp());
	}
}
