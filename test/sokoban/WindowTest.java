package sokoban;

import static org.junit.Assert.*;

import org.junit.Test;

public class WindowTest {

	@Test
	public void replaceToMapTest() {
		Throwable caught = null;
		try {
		   new Window().replaceToMap();;
		} catch (Throwable t) {
		   caught = t;
		}
		assertNull(caught);
	}

}
