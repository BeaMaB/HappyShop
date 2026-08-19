package ci553.happyshop.client.warehouse;

import ci553.happyshop.utility.UIStyle;
import ci553.happyshop.utility.WindowBounds;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * while an alert is showing,
 * the JavaFX application is blocked by the modal nature of the alert,
 * which prevents any further actions from being executed until the alert is dismissed.
 */

/**
 * We simulate the alert window instead of using the built-in Alert to meet three critical requirements:
 * 1. Customizable Appearance and Behavior: The built-in alert lacks flexibility in styling and positioning. By simulating the alert using a custom Stage, we can fully control the visual appearance (like adding emojis, custom text styles, and button positioning) to meet our design specifications.
 * 2. Stage Decoration Control: By using a custom Stage with `StageStyle.UNDECORATED`, we prevent the user from minimizing, resizing, or closing the alert using the standard window controls. This ensures that the alert is always visible and that the user cannot ignore it, forcing them to interact with the alert before proceeding.
 * 3. Emergency Shutdown (ESD): The built-in Alert blocks interaction and doesn't allow for forceful closure or shutdown. Using a custom Stage, we can ensure that the alert can be forcibly closed during ESD.
 * This solution gives us the necessary control over the user interaction flow, visual presentation, and prevents any way of bypassing the error handling process, which isn't possible with the standard Alert class.
 */

/**
 * This class provides a simple alert simulation window to display error messages.
 *
 * - The scene is created only once to avoid unnecessary recreation of the same layout.
 * - The window is created and shown only when needed. If the window is already open, it will be brought to the front.
 * - The `window` and `scene` are managed separately, allowing the scene to be reused while creating the window as needed.
 * - The window is closed and reset when the "Ok" button is clicked.
 * - The window is also closed when cancel or submit was clicked even the window is showing
 * This design ensures that the alert window is efficient by not recreating the scene multiple times, and the window only exists when necessary.
 */

public class AlertSimulator {
    private static int WIDTH = UIStyle.AlertSimWinWidth;
    private static int HEIGHT = UIStyle.AlertSimWinHeight;

    public WarehouseView warehouseView;
    private Stage window; // window for AlertSimulator
    private Scene scene; // Scene for AlertSimulator
    private Label laTitle; // Label for alert header
    private TextArea taErrorMsg; // TextArea to display error messages
    private Button btnOk; // Dismiss button
    private VBox vbRoot; // Container layout

    // Create the Scene (only once)
    private void createScene() {
        laTitle = new Label("\u26A0 Please fix input errors..."); // for emoji ⚠️
        laTitle.setStyle(UIStyle.alertTitleLabelStyle);
        laTitle.setMaxWidth(Double.MAX_VALUE); // Allow label to stretch full width
        laTitle.setAlignment(Pos.CENTER);     // Center text inside the label

        taErrorMsg = new TextArea();
        taErrorMsg.setEditable(false);
        taErrorMsg.setWrapText(true); // text wraps if long
        taErrorMsg.setStyle(UIStyle.alertContentTextAreaStyle);

        VBox vbLaTaMsg = new VBox(3, laTitle, taErrorMsg);
        vbLaTaMsg.setAlignment(Pos.CENTER); // Align children inside the VBox to the center

        btnOk = new Button("Ok");
        btnOk.setStyle(UIStyle.alertBtnStyle);
        HBox hbBtnOk = new HBox(btnOk);
        hbBtnOk.setAlignment(Pos.CENTER);
        btnOk.setOnAction(e -> {
            window.close();
        });

        vbRoot = new VBox(8, vbLaTaMsg, hbBtnOk);
        vbRoot.setAlignment(Pos.CENTER);
        vbRoot.setStyle(UIStyle.rootStyle);

        scene = new Scene(vbRoot, WIDTH, HEIGHT);

        // Register theme listener to handle light/dark mode changes dynamically
        Runnable refreshThemeStyles = () -> {
            vbRoot.setStyle(UIStyle.rootStyle);
            laTitle.setStyle(UIStyle.alertTitleLabelStyle);
            taErrorMsg.setStyle(UIStyle.alertContentTextAreaStyle);
            btnOk.setStyle(UIStyle.alertBtnStyle);
        };

        UIStyle.addThemeListener(refreshThemeStyles);
        refreshThemeStyles.run(); // Apply initial styles immediately
    }

    // Create the window if not exists
    // also recreate a window if the user closed it with error message in it
    private void createWindow() {
        if (scene == null) {
            createScene(); // create scene if not exists
        }

        window = new Stage();
        window.initModality(Modality.NONE); // Optional: explicitly set as non-blocking
        window.initStyle(StageStyle.UNDECORATED); // No title bar
        window.setScene(scene);

        // get bounds of warehouse window which triggers the alertSimulator
        // so that we can put the alertSimulator on top of it and at a suitable position
        WindowBounds bounds = warehouseView.getWindowBounds();
        window.setX(bounds.x + bounds.width - 10);
        window.setY(bounds.y + UIStyle.HistoryWinHeight + 30);
        window.show();
    }

    // Show error message in the alert window
    public void showErrorMsg(String errorMsg) {
        if (window == null || !window.isShowing()) {
            createWindow(); // create window if not exists
        }

        taErrorMsg.setText(errorMsg); // Update the error message
        window.toFront(); // Bring the window to the front if it's already open
    }

    /**
     * Closes the alert window.
     *
     * The purpose of this method is to provide a way to close the alert window from outside the AlertSimulator class,
     * when it is no longer needed (e.g., after canceling or submitting an action while the alert window is still showing
     * from a previous error).
     */
    public void closeAlertSimulatorWindow() {
        if (window != null && window.isShowing()) {
            window.close();
        }
    }
}