package sokoban;

import org.junit.Assert;
import org.junit.Test;

public class BitTest {

	@Test
	public void imageChooserTest() {
		@SuppressWarnings("static-access")
		String imageName = new Bit(0).imageChooser(2);
		Assert.assertEquals("place", imageName); 
	}

}
