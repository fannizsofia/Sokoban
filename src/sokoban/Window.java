package sokoban;

import java.awt.Container;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Window extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	public static Font font = new Font("Kristen ITC", Font.BOLD, 18);
	private MenuPanel menuPanel = new MenuPanel(this);
	
	@Override
	public void run() {
		try {
			Serializer.getInstance().load();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Hahó, a rendszer nem tudja betölteni az eredmények fájlt!", "Warning", JOptionPane.WARNING_MESSAGE);
		}
		replaceToMenu();
		setSize(750, 614);
		setLocation(230, 40);
		setTitle("Sokoban");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}

	
	public void replaceToMap() {
		try {
			MapPanel mapPanel = new MapPanel(this, menuPanel.getMapName());
			Container container = getContentPane();
			container.removeAll();
			container.add(mapPanel);
			container.validate();
			mapPanel.requestFocus();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Hahó, a rendszer nem találja a megadott fájlt!", "Warning", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void replaceToMenu() {
		Container container = getContentPane();
		container.removeAll();
		container.add(menuPanel);
		container.validate();
		container.repaint();
	}
	
	public static void main(String[] args) {
		new Thread(new Window()).start();
	}
	
}
