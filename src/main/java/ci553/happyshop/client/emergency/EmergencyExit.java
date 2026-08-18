package ci553.happyshop.client.emergency;

import ci553.happyshop.utility.UIStyle;
import ci553.happyshop.utility.WinPosManager;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
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
        // The Vertical Layout (VBox)
        VBox container = new VBox(20);
        container.setAlignment(Pos.CENTER); // This centers children vertically and horizontally
        container.getChildren().addAll(btnExit, lblShutdown);

        BorderPane borderPane = new BorderPane();

        borderPane.setStyle(UIStyle.rootStyle);
        // Setting the VBox as the CENTER of the BorderPane
        // effectively centers the entire group in the window.
        borderPane.setCenter(container);

         // Inside EmergencyExit constructor (Dark Mode)
        Runnable refreshExitStyles = () -> {
            // This updates the background color of the exit window
            borderPane.setStyle(UIStyle.rootStyle);
            // Update the label text color
            lblShutdown.setStyle(UIStyle.labelShutdown);
            // Update the red button
            btnExit.setStyle(UIStyle.exitBtnStyle);
            //Update Shutdown Image
            if (UIStyle.isDarkMode) {
                ivExit.setImage(new Image("PowerOffButtonDarkMode.png"));
            } else {
                ivExit.setImage(new Image("PowerOffButton.png"));
            }
            };

        UIStyle.addThemeListener(refreshExitStyles);
        refreshExitStyles.run();

        Scene scene = new Scene(borderPane, WIDTH, HEIGHT);
        Stage window = new Stage();
        window.setScene(scene);
        window.setTitle("EXIT");
        WinPosManager.registerWindow(window,WIDTH,HEIGHT); //calculate position x and y for this window
        window.show();
    }

}
