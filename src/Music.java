import javax.sound.sampled.*;
import java.io.File;

public class Music {
    private Clip clip;
   public Music(String path) {

        try {
            File musicPath = new File(path);
            if (musicPath.exists()) {

                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);


            } else {
                System.out.println("Couldn't find Music file");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);

    }
    public void play() {
        if (clip != null && !clip.isRunning()) {
            clip.setFramePosition(0); // rewind to the beginning

            clip.start();
        }
    }

    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public boolean isPlaying() {
        return clip != null && clip.isRunning();
    }
}