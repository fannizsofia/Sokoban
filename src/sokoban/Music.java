package sokoban;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Music {
	
	private static Clip clip;
	
	private static final Music INSTANCE = new Music();
	
	private Music() {
		try{
			clip = AudioSystem.getClip();
	        clip.open(AudioSystem.getAudioInputStream(new File("music/tetris.wav")));
		}
		catch (Exception e){
			System.out.println("baj");
		}
	}
	
	public static Music getInstance() {
		return INSTANCE;
	}

	public static void musicPlay() {
		    try {
		        clip.start();
		        clip.loop(Clip.LOOP_CONTINUOUSLY);
		    } catch (Exception exc) {
		        exc.printStackTrace(System.out);
		    }
	}
	
	public static void musicStop() {
	    try {
	    	if(clip != null) {
	    		clip.stop();
	    	}
	    } catch (Exception exc){
	        exc.printStackTrace(System.out);
	    }
	}
	
}
