package exceptions;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import java.io.File;
import java.io.IOException;

public class ErrorSoundPlayer {

    public static void playErrorSound() {
        try {
            // Load the sound file
            File soundFile = new File("/exceptions/error-126627.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            // Play the sound
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}