package ci553.happyshop.utility;

import java.util.ArrayList;
import java.util.List;
/**
 * UIStyle is a centralized Java record that holds all JavaFX UI-related style and size constants
 * used across all client views in the system.
 *
 * These values are grouped here rather than being hardcoded throughout the codebase:
 * - improves maintainability, ensures style consistency,
 * - avoids hardcoded values scattered across the codebase.
 *
 * Example usages:
 * - UIStyle.HistoryWinHeight for setting the height of the order history window
 * - UIStyle.labelStyle for applying consistent styling to labels
 *
 * UIStyle is deliberately defined as a `record` instead of a normal class for several reasons:
 *  - Lightweight and memory-efficient: Records are designed to be compact data carriers
 *    with minimal memory overhead compared to traditional classes.
 *  - No instance needed: Since this holds only static constants, using a record clearly
 *    communicates that no state or behavior is expected.
 *  - Final and immutable by default: Records cannot be extended and implicitly prevent misuse.
 *  - Cleaner syntax: Avoids unnecessary boilerplate (constructors, getters, etc.).
 */

public record UIStyle() {

    public static boolean isDarkMode = false;

    public static final int customerWinWidth = 600;
    public static final int customerWinHeight = 300;
    public static final int removeProNotifierWinWidth = customerWinWidth/2 +160;
    public static final int removeProNotifierWinHeight = 230;

    public static final int pickerWinWidth = 310;
    public static final int pickerWinHeight = 300;

    public static final int trackerWinWidth = 210;
    public static final int trackerWinHeight = 300;

    public static final int warehouseWinWidth = 630;
    public static final int warehouseWinHeight = 300;
    public static final int AlertSimWinWidth = 300;
    public static final int AlertSimWinHeight = 170;
    public static final int HistoryWinWidth = 310;
    public static final int HistoryWinHeight = 300;

    public static final int EmergencyExitWinWidth = 200;
    public static final int EmergencyExitWinHeight = 325;

    public static String rootStyle;
    public static String rootStyleHistory;
    public static String labelTitleStyle;
    public static String labelStyle;
    public static String labelShutdown;
    public static String labelMulLineStyle;
    public static String textFiledStyle;
    public static String historyTextFiledStyle;
    public static String textAreaStyle;
    public static String buttonStyle;
    public static String comboBoxStyle;
    public static String comboBoxPopupStyle;
    public static String listViewStyle;
    public static String receiptPageStyle;
    public static String greenFillBtnStyle;
    public static String redFillBtnStyle;
    public static String greenFillBtnStyle2;
    public static String redFillBtnStyle2;
    public static String blueFillBtnStyle;
    public static String grayFillBtnStyle;
    public static String buttonFillBtnStyle;
    public static String exitBtnStyle;
    public static String manageStockChildStyle;
    public static String manageStockChildStyle1;
    public static String cardStyle;
    public static String alertTitleLabelStyle;
    public static String alertContentTextAreaStyle;
    public static String alertContentUserActionStyle;
    public static String alertBtnStyle;
    public static String lineStyle;
    public static String detailAreaStyle;

    // Corner Radius Variable
    private static final String ROUND_CORNER = " -fx-background-radius: 25; -fx-border-radius: 25; ";
    private static final String BTN_PADDING = " -fx-padding: 8 22 8 22; ";
    // Font Style Variable
    private static final String FONT_MAIN = "-fx-font-family: 'Muli Black'; ";

    // A list of actions to run when the theme changes
    private static final List<Runnable> themeListeners = new ArrayList<>();
    public static void addThemeListener(Runnable r) {
        if (!themeListeners.contains(r)) {
            themeListeners.add(r);
        }
    }

    static {
        setDarkMode(false); // Default to Light
    }

    public static void setDarkMode(boolean dark) {
        isDarkMode = dark;
        if (dark) {
            //DARK MODE
            rootStyle = "-fx-background-color: #121212; -fx-padding: 8px;";
            rootStyleHistory = "-fx-background-color: #1A1A1A";
            labelTitleStyle = FONT_MAIN + "-fx-font-weight: bold; -fx-font-size: 18px; -fx-text-fill: #E2B327;";
            labelStyle = FONT_MAIN + "-fx-text-fill: #E2B327; -fx-font-weight: bold; -fx-font-size: 14px;";
            labelShutdown = "-fx-text-fill: #F44236; " + "-fx-font-family: 'Muli Black'; " + "-fx-font-weight: bold; " + "-fx-font-size: 20px; " + "-fx-letter-spacing: 2px;";
            labelMulLineStyle = FONT_MAIN + "-fx-font-size: 12px; -fx-text-fill: #FFFFFF;";
            textFiledStyle = "-fx-pref-height: 30;" + "-fx-background-color: #1E1E1E; -fx-text-fill: white; -fx-border-color: white; -fx-border-width: 1;" + ROUND_CORNER;
            historyTextFiledStyle = "-fx-font-family: 'Muli Black'; " + "-fx-font-size: 13px; " + "-fx-control-inner-background: #1E1E1E; " + "-fx-background-color: #1E1E1E ; " + "-fx-background-insets: 0, 2; " + "-fx-background-radius: 0; " + "-fx-border-radius:0;" + "-fx-border-color: white; " + "-fx-border-width: 0.5; " + "-fx-padding: 0;";
            textAreaStyle = "-fx-font-size: 14px; -fx-background-color: #2b2b2b; -fx-text-fill: white; -fx-border-color: #888888;";
            buttonStyle = FONT_MAIN + "-fx-pref-height: 30; -fx-background-color: #E2B327; -fx-text-fill: black; -fx-font-weight: bold;" + ROUND_CORNER + BTN_PADDING;
            comboBoxStyle = "-fx-pref-height: 30; -fx-font-weight: bold; -fx-font-size: 14px; -fx-text-fill: #FFFFFF; -fx-prompt-text-fill: #FFFFFF; -fx-faint-focus-color: transparent; -fx-focus-color: transparent; -fx-mark-color: black; -fx-background-color:#E2B327; -fx-border-color:#E2B327; -fx-border-width: 1;" + ROUND_CORNER;
            comboBoxPopupStyle =  "data:text/css," + (".combo-box-popup { -fx-background-color: transparent; }" + ".combo-box-popup > .list-view {" + "-fx-background-color: #2b2b2b;" + "-fx-background-radius: 15;" + "-fx-border-radius: 15;" + "-fx-border-color: #444444;" + "-fx-border-width: 1;" + "}" +".combo-box-popup > .list-view > .virtual-flow > .clipped-container > .sheet > .list-cell {" +"-fx-text-fill: white;" +"-fx-background-color: transparent;" +"}" +".combo-box-popup > .list-view > .virtual-flow > .clipped-container > .sheet > .list-cell:hover {" +" -fx-background-color: #3d3d3d;" +"}").replace(" ", "%20");
            listViewStyle = "-fx-font-family: 'Muli Black'; " + "-fx-font-size: 13px; " + "-fx-control-inner-background: #1E1E1E; " + "-fx-background-color: #1E1E1E ; " + "-fx-background-insets: 0, 2; " + "-fx-background-radius: 0; " + "-fx-border-radius:0;" + "-fx-border-color: white; " + "-fx-border-width: 0.5; " + "-fx-padding: 0;";
            receiptPageStyle = "-fx-background-color: #2D2D2D; -fx-border-color: white; -fx-border-width: 2; -fx-background-radius: 15; -fx-border-radius: 15; -fx-padding: 15;";
            greenFillBtnStyle = FONT_MAIN + "-fx-pref-height: 30;" + " -fx-padding: 8 15 8 15; " + "-fx-font-weight: bold;" + "-fx-background-color: #22943C; -fx-text-fill: white;" + ROUND_CORNER;
            greenFillBtnStyle2 = FONT_MAIN + "-fx-pref-height: 30;" + "-fx-padding: 8 15 8 15;" + "-fx-background-color: #22943C; -fx-font-weight: bold; -fx-font-size: 18px;" + "-fx-text-fill: #FFFFFF;" + ROUND_CORNER;
            grayFillBtnStyle = FONT_MAIN + "-fx-pref-height: 30;" + "-fx-padding: 0 60 0 60; " + "-fx-background-color: #F44236; -fx-text-fill: white; -fx-font-weight: bold;" + ROUND_CORNER + BTN_PADDING;
            blueFillBtnStyle = FONT_MAIN + "-fx-pref-height: 30;" + "-fx-background-color: #136ECF; -fx-text-fill: white; -fx-font-weight: bold;" + ROUND_CORNER + BTN_PADDING;
            redFillBtnStyle2 = FONT_MAIN + "-fx-pref-height: 30;" + "-fx-padding: 8 15 8 15;" + "-fx-background-color: #D12F26; -fx-font-weight: bold; -fx-font-size: 18px;" + "-fx-text-fill: #FFFFFF;" + ROUND_CORNER;
            redFillBtnStyle = FONT_MAIN + "-fx-pref-height: 30;" + "-fx-background-color: #D12F26; -fx-text-fill: white; -fx-font-weight: bold;" + ROUND_CORNER + BTN_PADDING;
            buttonFillBtnStyle = FONT_MAIN + "-fx-background-color: #E2B327;" + "-fx-border-color: #E2B327;" + "-fx-background-radius: 50;" + "-fx-border-radius: 55;" + "-fx-text-fill: #121212;" + "-fx-font-weight: bold;";
            exitBtnStyle = "-fx-background-color: #1E1E1E; " + "-fx-background-radius: 80; " + "-fx-padding: 20; -fx-border-radius: 80; -fx-border-color: #F44236; -fx-border-width: 4 ";
            manageStockChildStyle = "-fx-background-color:  #1E1E1E;  -fx-background-radius: 15; -fx-padding: 10;";
            manageStockChildStyle1 = "-fx-background-color: #332B00; -fx-border-color: #554400; -fx-padding: 5px;";
            cardStyle = "-fx-background-color: #2D2D2D; -fx-background-radius: 15; -fx-padding: 10;";
            alertTitleLabelStyle = "-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #FF5252";
            alertContentTextAreaStyle = "-fx-font-size: 14px; -fx-control-inner-background: #252525; -fx-text-fill: #FFFFFF; -fx-border-color: #444;";
            alertContentUserActionStyle = "-fx-font-size: 14px; -fx-text-fill: #FFFFFF;";
            alertBtnStyle = FONT_MAIN + "-fx-background-color: #E2B327; -fx-text-fill: #121212; -fx-font-weight: bold;" + ROUND_CORNER + BTN_PADDING; lineStyle = "-fx-background-color: #121212; -fx-min-width: 2px; -fx-max-width: 2px;";
        } else {
            //LIGHT MODE (Originals)
            rootStyle = "-fx-background-color: #E2B327; -fx-padding: 8px;";
            rootStyleHistory = "-fx-background-color: #E2B327";
            labelTitleStyle = FONT_MAIN + "-fx-font-weight: bold; -fx-font-size: 18px; -fx-text-fill: #121212;";
            labelStyle = FONT_MAIN + "-fx-text-fill: #121212; -fx-font-weight: bold; -fx-font-size: 14px;";
            labelShutdown = "-fx-text-fill: #D12F26; " + "-fx-font-family: 'Muli Black'; " + "-fx-font-weight: bold; " + "-fx-font-size: 20px; " + "-fx-letter-spacing: 2px;";
            labelMulLineStyle = FONT_MAIN + "-fx-font-size: 12px; -fx-text-fill: #333333;";
            textFiledStyle = "-fx-pref-height: 30;" + "-fx-background-color: white; -fx-text-fill: #121212; -fx-border-color: #121212; -fx-border-width: 1;" + ROUND_CORNER;
            historyTextFiledStyle = "-fx-pref-height: 30;" + "-fx-background-color: white; -fx-text-fill: #121212; -fx-border-color: #121212; -fx-border-width: 1;";
            textAreaStyle = "-fx-font-size: 14px; -fx-background-color: white; -fx-text-fill: black; -fx-border-color: #121212;";
            buttonStyle = FONT_MAIN + "-fx-pref-height: 30;" + "-fx-background-color: #121212; -fx-text-fill: #E2B327; -fx-font-weight: bold; " + ROUND_CORNER + BTN_PADDING;
            comboBoxStyle = "-fx-pref-height: 30; -fx-font-weight: bold; -fx-text-fill: #121212; -fx-font-size: 14px; -fx-background-color: #FFFFFF;" + ROUND_CORNER;
            comboBoxPopupStyle =  "data:text/css," + (".combo-box-popup { -fx-background-color: transparent; }" + ".combo-box-popup > .list-view {" + "-fx-background-color: white;" + "-fx-background-radius: 15;" + "-fx-border-radius: 15;" + "-fx-border-color: #cccccc;" + "-fx-border-width: 1;" +  "}").replace(" ", "%20");
            listViewStyle = "-fx-font-family: 'Muli Black'; " + "-fx-font-size: 13px; " + "-fx-control-inner-background: white; " + "-fx-background-color: #121212, white; " + "-fx-background-insets: 0, 2; " + "-fx-background-radius: 0; " + "-fx-border-radius:0;" + "-fx-border-color: #121212; " + "-fx-border-width: 0.5; " + "-fx-padding: 0;";
            receiptPageStyle = "-fx-background-color: white; -fx-border-color: #121212; -fx-border-width: 2; -fx-background-radius: 15; -fx-border-radius: 15; -fx-padding: 15;";
            greenFillBtnStyle = FONT_MAIN + "-fx-pref-height: 30;" + " -fx-padding: 8 15 8 15; " + "-fx-font-weight: bold;" + "-fx-background-color: #28a745; -fx-text-fill: white;" + ROUND_CORNER;
            greenFillBtnStyle2 = FONT_MAIN + "-fx-pref-height: 30;" + "-fx-padding: 8 15 8 15;" + "-fx-background-color: #28a745; -fx-font-weight: bold; -fx-font-size: 18px;" + "-fx-text-fill: #121212;" + ROUND_CORNER;
            grayFillBtnStyle = FONT_MAIN + "-fx-pref-height: 30;" + "-fx-padding: 0 60 0 60; " + "-fx-background-color: #F44236; -fx-text-fill: white; -fx-font-weight: bold;" + ROUND_CORNER + BTN_PADDING;
            blueFillBtnStyle = FONT_MAIN + "-fx-pref-height: 30;" + "-fx-background-color: #007BFF; -fx-text-fill: white; -fx-font-weight: bold;" + ROUND_CORNER + BTN_PADDING;
            redFillBtnStyle = FONT_MAIN + "-fx-pref-height: 30;" + "-fx-background-color: #F44236; -fx-text-fill: white; -fx-font-weight: bold;" + ROUND_CORNER + BTN_PADDING;
            redFillBtnStyle2 = FONT_MAIN + "-fx-pref-height: 30;" + "-fx-padding: 8 15 8 15;" + "-fx-background-color: #F44236; -fx-font-weight: bold; -fx-font-size: 18px;" + "-fx-text-fill: #121212;" + ROUND_CORNER;
            buttonFillBtnStyle = FONT_MAIN + "-fx-background-color: #121212;" + "-fx-border-color: #121212;" + "-fx-background-radius: 50;" + "-fx-border-radius: 55;" + "-fx-text-fill: #E2B327;" + "-fx-font-weight: bold;";
            exitBtnStyle = "-fx-background-color: #F44236; " + "-fx-background-radius: 80; " + "-fx-padding: 20; -fx-border-radius: 80; -fx-border-color: #D12F26; -fx-border-width: 4";
            manageStockChildStyle = "-fx-background-color: white;  -fx-background-radius: 15; -fx-padding: 10;";
            manageStockChildStyle1 = "-fx-background-color: white; -fx-padding: 5px; -fx-border-radius: 15;";
            cardStyle = "-fx-background-color: white; -fx-background-radius: 15; -fx-padding: 10;";
            alertTitleLabelStyle = "-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #C00000;";
            alertContentTextAreaStyle = "-fx-font-size: 14px; -fx-control-inner-background: #FFFFFF; -fx-text-fill: #333333; -fx-border-color: #121212;";
            alertContentUserActionStyle = "-fx-font-size: 14px; -fx-text-fill: #E2B327; -fx-font-weight: bold;";
            alertBtnStyle = FONT_MAIN + "-fx-background-color: #121212; -fx-text-fill: #B48A00; -fx-font-weight: bold;" + ROUND_CORNER + BTN_PADDING; lineStyle = "-fx-background-color: #121212; -fx-min-width: 2px; -fx-max-width: 2px;";
            detailAreaStyle = "-fx-font-family: 'Muli Black'; " + "-fx-font-size: 14px; " + "-fx-text-fill: #121212; " + "-fx-control-inner-background: #FFFFFF; " + "-fx-border-color: #121212; " + "-fx-border-width: 2;";
        }

        for (Runnable listener : themeListeners) {
            listener.run();
        }
    }
}
