package sokoban;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

public class Bit {
	public static final int SIDE = 65;
	public int value, extensionValue;
	
	public Bit(int value) { //Konstruktor: �t/fal/l�dahely be�ll�t�sa
		this.value = value;
		this.extensionValue = -1;
	}
	
	public Bit(int value, int extensionvalue) { //Konstruktor: �t/fal/l�dahely, j�t�kos/l�da be�ll�t�sa
		this.value = value;
		this.extensionValue = extensionvalue;
	}
	
	public void swap(Bit other) { //L�p�nk, vagy toljuk a l�d�t -> k�t mez� extensionValue �rt�k�t megcser�lj�k.
		int tempValue = extensionValue;
		extensionValue = other.extensionValue;
		other.extensionValue = tempValue;
	}

	public boolean canBeWalkedOn() { //Tudunk-e l�pni? Ha nem fal, igen.
		return value != 1; 
	}
	
	public boolean isBox() { //Van-e l�da a mez�n?
		return extensionValue == 3;
	}
	
	public void paint(Graphics g, int x, int y) { //Kirajzoljuk a mez�t.
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
			color = Color.WHITE; //�t
			break;
		case 2:
			color = Color.CYAN; //l�dahely
			break;
		case 3:
			color = Color.GREEN; //l�da
			break;
		case 4:
			color = Color.RED; //j�t�kos
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
			image = "grass"; //�t
			break;
		case 2:
			image = "place"; //l�dahely
			break;
		case 3:
			image = "box"; //l�da
			break;
		case 4:
			image = "player"; //j�t�kos
			break;
		default:
			image = "wall"; //fal
			break;
		}
		return image;
	}

}
