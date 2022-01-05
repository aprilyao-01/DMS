package setting;


import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/** <h1> Enum Class: {@link SoundEffect} </h1>
 *
 *<p> This enum class define the sound effect of the game.<br>
 * When different actions occur, different sound will be play.
 *
 * @see Settings
 * @version 1.0
 * @since 1.0
 * @author Siyu Yao
 * */
public enum SoundEffect {
	/** define the file path*/
	BRICK_HIT("src/main/resources/audio/brick_hit.wav"),
	WIN("src/main/resources/audio/win.wav"),
	LOSE("src/main/resources/audio/lose.wav"),
	BGM("src/main/resources/audio/BGM.wav"),
	PADDLE_HIT("src/main/resources/audio/paddle_hit.wav"),
	BORDER_HIT("src/main/resources/audio/hit.wav"),

	;

	/** define the sound m_volume*/
	public  enum Volume {
		MUTE, LOW, MEDIUM, HIGH
	}
	
	public static Volume m_volume = Volume.LOW;

	private Clip m_clip;

	// accessor methods
	public Clip getClip() {return m_clip;}


	/** define the sound effect
	 * @param soundFileName the name of the sound should be played
	 * */
	SoundEffect(String soundFileName) {
		try {
			File file = new File(soundFileName);
			URL url = file.toURL();
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
			m_clip = AudioSystem.getClip();
			m_clip.open(audioInputStream);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	/** plays the sound effect */
	public void play() {
		if (m_volume != Volume.MUTE) {
				//stop this m_clip thread and new m_clip start
				m_clip.flush();
				m_clip.start();

			m_clip.setFramePosition(0);		// set to begin
		}
	}

	/** plays the sound effect */
	public void start() {
		if (m_volume != Volume.MUTE) {
			m_clip.start();
			m_clip.setFramePosition(0);
		}
	}

}
