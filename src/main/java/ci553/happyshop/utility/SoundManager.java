package ci553.happyshop.utility;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.net.URL;

public class SoundManager {
    public static MediaPlayer backgroundMusic;

    // The method to play the background music
    public static void playBackgroundMusic(String fileName) {
        try {
            // Using SoundManager.class instead of Main.class is CRITICAL
            URL resource = SoundManager.class.getResource("/background_music.mp3");
            if (resource != null) {
                backgroundMusic = new MediaPlayer(new Media(resource.toExternalForm()));
                backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);
                backgroundMusic.setVolume(0.3);
                backgroundMusic.play();
            }
        } catch (Exception e) {
            System.out.println("Audio Error: " + e.getMessage());
        }
    }
}
