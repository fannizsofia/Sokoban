package sokoban;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;

public class MenuPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private String[] mapNames = {"easy", "medium", "hard"};
	
	private JLabel chooseLabel = new JLabel("Choose a map!");
	private JComboBox<String> chooseComboBox = new JComboBox<String>(mapNames);
	private JButton newGameButton = new JButton("New Game");
	private JButton exitButton = new JButton("Exit");
	private JButton resultsButton = new JButton("Results");
	private JToggleButton musicButton = new JToggleButton("Music");
	private Window window;
	
	public MenuPanel(Window window) {
		this.window = window;
		newGameButton.addActionListener(this);
		exitButton.addActionListener(this);
		resultsButton.addActionListener(this);
		musicButton.addActionListener(this);
		add(chooseLabel);
		add(chooseComboBox);
		add(newGameButton);
		add(resultsButton);
		add(exitButton);
		add(musicButton);
		setFocusable(false);
		for (Component c : getComponents()) {
		    c.setFont(Window.font);
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(new ImageIcon("images/cherry.png").getImage(), 0, 0, 750, 614, null);
	}
	
	public String getMapName() {
		return (String) chooseComboBox.getSelectedItem();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == newGameButton) {
			window.replaceToMap();
		} else if (e.getSource() == exitButton) {
			System.exit(0);
		} else if (e.getSource() == resultsButton) {
			JFrame results = new JFrame();
			results.setTitle("Results");
			results.setSize(200, 250);
			results.setResizable(false);
			results.setLocation(500, 250);
			JScrollPane scrollPane = new JScrollPane(Serializer.getInstance().showScores());
			results.add(scrollPane);
			results.setVisible(true);
		} else if (e.getActionCommand().equals("Music")) {
			Music.getInstance();
			Music.musicPlay();
			musicButton.setActionCommand("MusicStop");
		} else if (e.getActionCommand().equals("MusicStop")) {
			Music.getInstance();
			Music.musicStop();
			musicButton.setActionCommand("Music");
		}
	}
	
}
