package ci553.happyshop.client.emergency;

import ci553.happyshop.utility.UIStyle;
import ci553.happyshop.utility.WinPosManager;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * The class EmergencyExit used to immediately shut down the entire application.
 * It is a singleton with static access, instantiation is restricted.
 */
public class EmergencyExit {
    private final int WIDTH = UIStyle.EmergencyExitWinWidth;
    private final int HEIGHT = UIStyle.EmergencyExitWinHeight;
    private static EmergencyExit emergencyExit;

    //used by Main class to get the single instance
    public static EmergencyExit getEmergencyExit() {
        if (emergencyExit == null)
            emergencyExit = new EmergencyExit();
        return emergencyExit;
    }

    //Private constructor creates a shutdown window.
    //The window displays a single button with a shutdown image,positioned via `WinPosManager`,
    private EmergencyExit() {
        ImageView ivExit = new ImageView("PowerOffButton.png");
        ivExit.setFitWidth(WIDTH-80);
        ivExit.setFitHeight(WIDTH-80);
        ivExit.setPreserveRatio(true);

        Button btnExit = new Button();
        btnExit.setGraphic(ivExit);

        // APPLYING MODERN STYLE: Rounded corners, hover color
        btnExit.setStyle(UIStyle.exitBtnStyle);

        // Simple Hover Effect: Lighten the red when mouse is over it
        btnExit.setOnMouseEntered(e -> btnExit.setStyle("-fx-background-color: #ff7675; -fx-background-radius: 80; -fx-padding: 20; -fx-cursor: hand;"));
        btnExit.setOnMouseExited(e -> btnExit.setStyle("-fx-background-color: #F44236; -fx-background-radius: 80; -fx-padding: 20; -fx-cursor: hand;"));

        btnExit.setOnAction(event -> {
            Platform.exit(); // Gracefully exit JavaFX
            System.exit(0);//forcefully shut down JVM (in case there are non-JavaFX threads)
        });

        // Create the Shutdown Text
        Label lblShutdown = new Label("SHUTDOWN");
        lblShutdown.setStyle(UIStyle.labelShutdown);
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(btnExit);

        borderPane.setStyle(UIStyle.rootStyle);
        Scene scene = new Scene(borderPane, WIDTH, HEIGHT);
        Stage window = new Stage();
        window.setScene(scene);
        window.setTitle("🛒 EXIT");
        WinPosManager.registerWindow(window,WIDTH,HEIGHT); //calculate position x and y for this window
        window.show();
    }

}
