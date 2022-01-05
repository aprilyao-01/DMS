package setting;

import javax.sound.sampled.Clip;

/** <h1> Class: {@link BGMEffect} </h1>
 *
 *<p> This class implements the SINGLETON design pattern.
 * Defined the BGM states when program first launch.
 *
 * @see
 * @version 1.2
 * @since 1.0
 * @author Siyu Yao
 * */

public class BGMEffect{

    private SoundEffect m_effect;
    private Clip m_clip;

    // accessor methods
    public boolean isPlay(){
        return m_clip.isRunning();
    }
    public void stop(){
        m_clip.stop();
    }


    private static BGMEffect m_newBgm = null;

    /** get the current BGM states, if it is first use, create a new instance
	 * @return the current BGM states
	 * */
    public static BGMEffect getBGM(){
        if (m_newBgm == null){
            m_newBgm = new BGMEffect();
        }
        return m_newBgm;
    }

    private BGMEffect(){
        m_effect = SoundEffect.BGM;
        m_clip = m_effect.getClip();
    }

    /** Play the BGM */
    public void play(){
        new Thread(() -> {
            m_clip.loop(m_clip.LOOP_CONTINUOUSLY);
            m_clip.setFramePosition(0);
            m_clip.start();
        }).start();
    }
}
