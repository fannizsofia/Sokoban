package sokoban;

import java.awt.Image;
import java.io.IOException;

import org.junit.Test;

public class SourcesTest {

	@SuppressWarnings("unused")
	@Test(expected=IOException.class)
	public void loadImageTest() throws Exception {
		@SuppressWarnings("static-access")
		Image image = new Sources().loadImage("alma.png");
	}

}
