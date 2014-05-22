package sokoban;

import java.io.Serializable;

public class Player implements Serializable, Comparable<Player> {
	
	private static final long serialVersionUID = 1L;
	
	public String mapName;
	public String playerName;
	public int steps;
	
	public Player(String mapName, String playerName, int steps) {
		this.mapName = mapName;
		this.playerName = playerName;
		this.steps = steps;
	}

	@Override
	public String toString() {
		return mapName + " " + playerName + " " + steps;
	}

	@Override
	public int compareTo(Player player) {
		if (player.mapName.equals(mapName)) {
			if (player.steps == steps) {
				return -(player.playerName.compareTo(playerName));
			} else {
				return new Integer(steps).compareTo(player.steps);
			}
		} else {
			return (mapName.equals("easy") || player.mapName.equals("hard"))?1:-1;
		}
	}
}