package sokoban;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Vector;

import javax.swing.JList;

public class Serializer {
	private static final Serializer INSTANCE = new Serializer();
	private File scoresName = new File("results");
	private Vector<Player> scores = new Vector<Player>();
	
	private Serializer() {}
	
	public static Serializer getInstance() {
		return INSTANCE;
	}
	
	public void setLoadFile(String scoresName) {
		this.scoresName = new File(scoresName);
	}

	@SuppressWarnings("unchecked")
	public void load() throws IOException, ClassNotFoundException {
		if (scoresName.exists()) {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(scoresName));
			scores = (Vector<Player>) in.readObject();
			in.close();
		}
	}
	
	public JList<Player> showScores() {
		Collections.sort(scores);
		JList<Player> results = new JList<Player>(scores);
		return results;
	}
	
	public void save(String mapName, String playerName, int steps) throws IOException {
		scores.add(new Player(mapName, playerName, steps));
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(scoresName));
		out.writeObject(scores);
		out.close();
	}
}
