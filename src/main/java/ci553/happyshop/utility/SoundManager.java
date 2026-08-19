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
    // The method for button clicks
    public static void playClick() {
        try {
            URL resource = SoundManager.class.getResource("/click_button.mp3");
            if (resource != null) {
                // We create a one-time player so music doesn't stop
                MediaPlayer clickPlayer = new MediaPlayer(new Media(resource.toExternalForm()));
                clickPlayer.setVolume(0.5);
                clickPlayer.play();
            }
        } catch (Exception e) {
            System.out.println("Click Sound Error: " + e.getMessage());
        }
    }
    // The method for the delete/trash button sound effect
    public static void playTrashClick() {
        try {
            URL resource = SoundManager.class.getResource("/trash_click.mp3");
            if (resource != null) {
                // We create a one-time player so music doesn't stop
                MediaPlayer clickPlayer = new MediaPlayer(new Media(resource.toExternalForm()));
                clickPlayer.setVolume(0.5);
                clickPlayer.play();
            }
        } catch (Exception e) {
            System.out.println("Click Sound Error: " + e.getMessage());
        }
    }
    // method for the exit sound button
    public static void playExitSound() {
        try {
            URL resource = SoundManager.class.getResource("/exit_sound.mp3");
            if (resource != null) {
                MediaPlayer exitPlayer = new MediaPlayer(new Media(resource.toExternalForm()));
                exitPlayer.setVolume(0.8);

                // Stop background music immediately
                if (SoundManager.backgroundMusic != null) {
                    SoundManager.backgroundMusic.stop();
                }

                // wait until the player is fully loaded and ready
                exitPlayer.setOnReady(() -> {
                    exitPlayer.play();
                });

                // Shut down only when the audio file finishes playing completely
                exitPlayer.setOnEndOfMedia(() -> {
                    javafx.application.Platform.exit();
                    System.exit(0);
                });

            } else {
                System.out.println("Audio Error: exit_sound.mp3 file not found!");
                javafx.application.Platform.exit();
                System.exit(0);
            }
        } catch (Exception e) {
            System.out.println("Exit Sound Playback Exception: " + e.getMessage());
            javafx.application.Platform.exit();
            System.exit(0);
        }
    }
}
