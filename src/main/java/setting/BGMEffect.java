package setting;

import javax.sound.sampled.Clip;

public class BGMEffect{

    private SoundEffect effect;

    private Clip clip;


    public BGMEffect(){
        effect = SoundEffect.BGM;
        clip = effect.getClip();
    }

    public void play(){
        new Thread(() -> {
            clip.loop(clip.LOOP_CONTINUOUSLY);
            clip.setFramePosition(0);
            clip.start();
        }).start();
    }

    public boolean isPlay(){
        return clip.isRunning();
    }

    public void stop(){
        clip.stop();
    }
}
