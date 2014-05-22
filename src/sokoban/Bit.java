package sokoban;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

public class Bit {
	public static final int SIDE = 65;
	public int value, extensionValue;
	
	public Bit(int value) { //Konstruktor: út/fal/ládahely beállítása
		this.value = value;
		this.extensionValue = -1;
	}
	
	public Bit(int value, int extensionvalue) { //Konstruktor: út/fal/ládahely, játékos/láda beállítása
		this.value = value;
		this.extensionValue = extensionvalue;
	}
	
	public void swap(Bit other) { //Lépünk, vagy toljuk a ládát -> két mezõ extensionValue értékét megcseréljük.
		int tempValue = extensionValue;
		extensionValue = other.extensionValue;
		other.extensionValue = tempValue;
	}

	public boolean canBeWalkedOn() { //Tudunk-e lépni? Ha nem fal, igen.
		return value != 1; 
	}
	
	public boolean isBox() { //Van-e láda a mezõn?
		return extensionValue == 3;
	}
	
	public void paint(Graphics g, int x, int y) { //Kirajzoljuk a mezõt.
		try {
			g.drawImage(Sources.loadImage(imageChooser(value)), y*SIDE, x*SIDE, SIDE, SIDE, null);
			if(extensionValue != -1) {
				g.drawImage(Sources.loadImage(imageChooser(extensionValue)), y*SIDE, x*SIDE, SIDE, SIDE, null);
			}
		} catch (IOException e) {
			e.printStackTrace();
			g.setColor(colorChooser(value));
			g.fillRect(y*SIDE, x*SIDE, SIDE, SIDE);
			if(extensionValue != -1) {
				g.setColor(colorChooser(extensionValue));
				g.fillOval(y*SIDE, x*SIDE, SIDE, SIDE);
			}
		}
	}
	
	private static Color colorChooser(int value) {
		Color color = null;
		switch (value) {
		case 0:
			color = Color.WHITE; //út
			break;
		case 2:
			color = Color.CYAN; //ládahely
			break;
		case 3:
			color = Color.GREEN; //láda
			break;
		case 4:
			color = Color.RED; //játékos
			break;
		default:
			color = Color.BLACK; //fal
			break;
		}
		return color;
	}
	
	public static String imageChooser(int value) {
		String image;
		switch (value) {
		case 0:
			image = "grass"; //út
			break;
		case 2:
			image = "place"; //ládahely
			break;
		case 3:
			image = "box"; //láda
			break;
		case 4:
			image = "player"; //játékos
			break;
		default:
			image = "wall"; //fal
			break;
		}
		return image;
	}

}
