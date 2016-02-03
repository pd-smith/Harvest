package com.harvest.hud_elements;

/**
 * Created by Patty on 1/29/16.
 */
public class HUDVars {
    public static final String STATS_OVERLAY_PATH = "UI/Status/Stats_HUD.png";
    public static final String PAUSE_GREY_PATH = "UI/Pause/Pause_Grey.png";
    public static final String PAUSE_ITEM_MAIN_MENU_PATH = "UI/Pause/Pause_Menu_Item_Main_Menu.png";
    public static final String PAUSE_ITEM_OPTIONS_PATH = "UI/Pause/Pause_Menu_Item_Options.png";
    public static final String PAUSE_ITEM_SAVE_PATH = "UI/Pause/Pause_Menu_Item_Save.png";
    public static final String PAUSE_ITEM_HIGHLIGHT_PATH = "UI/Pause/Pause_Menu_Item_Highlight.png";
    public static final String OVERLAY_CLOCK_BACKGROUND_PATH = "UI/HUD/hud_bar.png";

    public static final String OVERLAY_MORNING_PATH = "UI/HUD/Tints/morning_tint.png";
    public static final String OVERLAY_AFTERNOON_PATH = "UI/HUD/Tints/afternoon_tint.png";
    public static final String OVERLAY_EVENING_PATH = "UI/HUD/Tints/evening_tint.png";
    public static final String OVERLAY_NIGHT_PATH = "UI/HUD/Tints/night_tint.png";

    public static final String FONT_UI_PATH = "Fonts/press_start.ttf";

    public static final int CLOCK_UPDATE = 1;
    public static final int MORNING_START = 6;
    public static final int AFTERNOON_START = 11;
    public static final int EVENING_START = 16;
    public static final int NIGHT_START = 21;

    //index is month number minus 1
    public static final String[] MONTH_NAMES = {
            "JAN",
            "FEB",
            "MAR",
            "APR",
            "MAY",
            "JUN",
            "JUL",
            "AUG",
            "SEP",
            "OCT",
            "NOV",
            "DEC"
    };
    public static final int[] DAYS_IN_MONTH = {
            31,
            28,
            31,
            30,
            31,
            30,
            31,
            31,
            30,
            31,
            30,
            31
    };

    public static final String[] WEEK_DAY_NAME = {
            "Monday",
            "Tuesday",
            "Wednesday",
            "Thursday",
            "Friday",
            "Saturday",
            "Sunday"
    };

}
