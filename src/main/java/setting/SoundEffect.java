package setting;


import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;


public enum SoundEffect {
	BRICK_HIT("src/main/resources/audio/brick_hit.wav"),
	WIN("src/main/resources/audio/win.wav"),
	LOSE("src/main/resources/audio/lose.wav"),
	BGM("src/main/resources/audio/BGM.wav"),
	PADDLE_HIT("src/main/resources/audio/paddle_hit.wav"),
	BORDER_HIT("src/main/resources/audio/hit.wav"),

	;
	
	public  enum Volume {
		MUTE, LOW, MEDIUM, HIGH
	}
	
	public static Volume volume = Volume.LOW;
	
	private Clip clip;


	SoundEffect(String soundFileName) {
		try {
			 File file = new File(soundFileName);
			URL url = file.toURL();
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}


	public void play() {
		// plays the sound effect
		if (volume != Volume.MUTE) {
				//stop this clip thread and new clip start
				clip.flush();
				clip.start();

			clip.setFramePosition(0);
		}
	}


	public Clip getClip() {
		return clip;
	}

	public void start() {
		// plays the sound effect
		if (volume != Volume.MUTE) {
			clip.start();
			clip.setFramePosition(0);
		}
	}

}
