import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

	public static synchronized void playSound(final String url) {
		new Thread(new Runnable() {
		  public void run() {
		    try {
		      Clip sound = AudioSystem.getClip();
		      AudioInputStream inputStream = AudioSystem.getAudioInputStream(Sound.class.getResourceAsStream("sounds/" + url + ".wav"));
		      sound.open(inputStream);
		      sound.start(); 
		    } catch (Exception e) {
		      System.err.println(e.getMessage());
		    }
		  }
		}).start();
	
	}
	
}
