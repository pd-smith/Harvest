package com.harvest.hud_elements;

/**
 * Created by Patty on 1/29/16.
 */
public class HUDVars {
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
