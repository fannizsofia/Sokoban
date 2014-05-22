package sokoban;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MapPanel extends JPanel implements KeyListener {
	
	private static final long serialVersionUID = 1L;
	
	private Bit[][] map;
	private Window window;
	private int playerX, playerY;
	
	private String playerName = "unnamed";
	private String mapName = "unnamed";
	private int steps = 0;
	
	public MapPanel(Window window, String mapName) throws IOException {
		this.window = window;
		this.map = Sources.loadMap(mapName);
		this.mapName = mapName;
		findPlayer();
		addKeyListener(this);
	}

	// KIRAJZOL
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j].paint(g, i, j);
			}
		}
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(4));
		g.setColor(Color.BLACK);
		g.drawRect(600, 80, 80, 80);
		g.setFont(Window.font);
		g.drawString("Steps:", 610, 110);
		g.drawString(String.valueOf(steps), 632, 140);
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		switch(e.getKeyChar()) {
		case 'w':
			up();
			break;
		case 's':
			down();
			break;
		case 'a':
			left();
			break;
		case 'd':
			right();
			break;
		case 27:
			window.replaceToMenu();
			break;
		}
		repaint();
		handleWinning();
	}
	
	private void findPlayer() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if(map[i][j].extensionValue == 4) {
					playerX = i;
					playerY = j;
					return;
				} 
			}
		}
	}
	
	private void up() {
		if (playerX >= 2 && interact(map[playerX][playerY], map[playerX - 1][playerY], map[playerX - 2][playerY])) {
			playerX -= 1;
			steps++;
		}
	}
	
	private void down() {
		if (playerX < map.length - 2 && interact(map[playerX][playerY], map[playerX + 1][playerY], map[playerX + 2][playerY])) {
			playerX += 1;
			steps++;
		}
	}
	
	private void left() {
		if (playerY >= 2 && interact(map[playerX][playerY], map[playerX][playerY  - 1], map[playerX][playerY - 2])) {
			playerY -= 1;
			steps++;
		}
	}
	
	private void right() {
		if (playerY < map[playerX].length - 2 && interact(map[playerX][playerY], map[playerX][playerY + 1], map[playerX][playerY + 2])) {
			playerY += 1;
			steps++;
		}	
	}
	
	private boolean interact(Bit player, Bit box, Bit next) {
		if(box.canBeWalkedOn() && !box.isBox()) {
			box.swap(player);
			return true;
		} else if(box.isBox() && next.canBeWalkedOn() && !next.isBox()) {
			next.swap(box);
			box.swap(player);
			return true;
		} else {
			return false;
		}
	}
	
	private boolean isWinning() {
		int boxCount = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if(map[i][j].isBox()) {
					boxCount += 1;
					if(map[i][j].value != 2)
						return false;
				}
			}
		}
		return boxCount > 0;
	}
	
	private void handleWinning() {
		try {
			if (isWinning()) {
				playerName = JOptionPane.showInputDialog(this, "Nyertél! Gratulálunk, hogy hívnak?");
				if (!(playerName != null && playerName.trim().length() > 0)) {
					playerName = "unnamed";
				}
				Serializer.getInstance().save(mapName, playerName, steps);
				window.replaceToMenu();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
