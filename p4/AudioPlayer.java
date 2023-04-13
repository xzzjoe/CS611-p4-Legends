import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
public class AudioPlayer {
    Long currentFrame;
    Clip clip;

    // current status of clip
    String status;

    AudioInputStream audioInputStream;
    static String filePath;

    // constructor to initialize streams and clip
    public AudioPlayer(String filePath)
            throws UnsupportedAudioFileException,
            IOException, LineUnavailableException
    {
        // create AudioInputStream object
        this.filePath = filePath;
        audioInputStream =
                AudioSystem.getAudioInputStream(new File(this.filePath).getAbsoluteFile());

        // create clip reference
        clip = AudioSystem.getClip();

        // open audioInputStream to the clip
        clip.open(audioInputStream);

//        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }



    public void play() {
        //start the clip
        clip.start();
        status = "play";
        clip.close();
    }

    public void playInfinite(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();
        status = "play";
    }

    public void pause(){
        if(status.equals("pause")){
            System.out.println("Audio already paused.");
            return;
        }
        this.currentFrame = this.clip.getMicrosecondPosition();
        this.clip.stop();
        status = "pause";
    }

    public void resume() throws UnsupportedAudioFileException,
            IOException, LineUnavailableException {
        if(status.equals("resume")){
            System.out.println("Audio is already playing");
            return;
        }
        clip.close();
        this.resetAudioStream();
        this.clip.setMicrosecondPosition(this.currentFrame);
        this.play();
    }

    public void restart() throws UnsupportedAudioFileException,
            IOException, LineUnavailableException {
        this.clip.stop();
        this.clip.close();
        resetAudioStream();
        this.currentFrame = 0L;
        this.clip.setMicrosecondPosition(0);
        this.play();
    }

    public void stop() throws UnsupportedAudioFileException,
            IOException, LineUnavailableException {
        currentFrame = 0L;
        clip.stop();
        clip.close();
    }


    public void jump()throws UnsupportedAudioFileException, IOException,
            LineUnavailableException {
        //User facing function for jump
        System.out.println("Enter time (" + 0 +
                ", " + clip.getMicrosecondLength() + ")");
        Scanner sc = new Scanner(System.in);
        long c1 = sc.nextLong();
        jumpTo(c1);
    }

    public void jumpTo(long c) throws UnsupportedAudioFileException, IOException,
            LineUnavailableException {
        if (c > 0 && c < clip.getMicrosecondLength())
        {
            clip.stop();
            clip.close();
            resetAudioStream();
            currentFrame = c;
            clip.setMicrosecondPosition(c);
            this.play();
        }
    }


    public void resetAudioStream() throws UnsupportedAudioFileException, IOException,
            LineUnavailableException
    {
        audioInputStream = AudioSystem.getAudioInputStream(new File(this.filePath).getAbsoluteFile());
        clip.open(audioInputStream);
    }

}
