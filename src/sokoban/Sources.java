package sokoban;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

import javax.imageio.ImageIO;

public class Sources { 
	private static HashMap<String, Image> images = new HashMap<String, Image>();
	
	public static Bit[][] loadMap(String mapName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("map/" + mapName + ".txt"));
		Vector<String> lines = new Vector<String>();
		String s;
		while((s = br.readLine()) != null) {
			lines.add(s);
		}
		br.close();
		
		Bit[][] map = new Bit[lines.size()][];
		for (int i = 0; i < lines.size(); i++) {
			s = lines.get(i);
			map[i] = new Bit[s.length()];
			for (int j = 0; j < s.length(); j++) {
				int value = Character.getNumericValue(s.charAt(j));
				if (value == 4 || value == 3) {
					map[i][j] = new Bit(0, value);
				} else {
					map[i][j] = new Bit(value);
				}
			}
		}
		return map;	
	}
	
	public static Image loadImage(String imageName) throws IOException {
		Image image = images.get(imageName);
		
		if (image == null) {
			image = ImageIO.read(new File("images/" + imageName + ".png"));
			images.put(imageName, image);
		}
		
		return image;
	}
	
}
