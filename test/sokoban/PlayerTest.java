package sokoban;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest {

	Player player1;
	Player player2;
	
	@Before
	public void setUp() {
		player1 = new Player("easy", "Anna", 15);
		player2 = new Player("easy", "Anna", 15);
	}
	
	@Test
	public void compareToTest() {
		int result = player1.compareTo(player2);
		Assert.assertEquals(0, result);
	}
	
	@Test
	public void toStringTest() {
		String result = player1.toString();
		Assert.assertEquals("easy Anna 15", result);
	}

}
