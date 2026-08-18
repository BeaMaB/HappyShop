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

    public static final String rootStyleYellow = "-fx-padding: 8px; " +
            "-fx-background-color: lightyellow";

    public static final String textFiledStyle = "-fx-font-size: 16";

    public static final String labelMulLineStyle= "-fx-font-size: 16px; " +
            "-fx-background-color: lightpink";

    public static final String listViewStyle = "-fx-border-color: #ccc; " +
            "-fx-border-width: 1px; -fx-background-color: white; -fx-font-size: 14px;";

    public static final String manageStockChildStyle = "-fx-background-color: lightgrey; " +
            "-fx-border-color: lightgrey; " +
            "-fx-border-width: 1px; " +
            "-fx-padding: 5px;";

    public static final String manageStockChildStyle1 = "-fx-background-color: lightyellow; " +
            "-fx-border-color: lightyellow; " +
            "-fx-border-width: 1px; " +
            "-fx-padding: 5px;";

    public static final String greenFillBtnStyle = "-fx-background-color: green; " +
            "-fx-text-fill: white; -fx-font-size: 14px;";
    public static final String redFillBtnStyle ="-fx-background-color: red; " +
            "-fx-text-fill: white; -fx-font-size: 14px; ";

    public static final String grayFillBtnStyle = "-fx-background-color: gray; " +
            "-fx-text-fill: white; -fx-font-size: 14px; ";

    public static final String blueFillBtnStyle ="-fx-background-color: blue; " +
            "-fx-text-fill: white; -fx-font-size: 14px;";

    public static final String alertBtnStyle ="-fx-background-color: green; " +
            "-fx-text-fill: white; -fx-font-size: 12px; -fx-font-weight: bold;";

    public static final String alertTitleLabelStyle = "-fx-font-size: 16px; " +
            "-fx-font-weight: bold; " +
            "-fx-text-fill: red; " + "-fx-background-color: lightblue;";

    public static final String alertContentTextAreaStyle = "-fx-font-size: 14px;" +
            "-fx-font-weight: normal;-fx-control-inner-background: lightyellow; -fx-text-fill: darkblue;";

    public static final String alertContentUserActionStyle = "-fx-font-size: 14px;" +
            "-fx-font-weight: normal; -fx-text-fill: green;";

}
