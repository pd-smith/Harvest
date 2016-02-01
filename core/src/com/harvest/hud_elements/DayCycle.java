package com.harvest.hud_elements;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Patty on 1/29/16.
 */
public class DayCycle {

    int hours, minutes, day, month, weekDayID;
    Vector2 position;
    BitmapFont font;

    public DayCycle(){
        hours = 12;
        minutes = 0;
        month = 12;
        day = 31;
        weekDayID = 0;
        position = new Vector2(900,25); //needs to be mathematical later on, not just 2 'random' numbers
        font = new BitmapFont();
    }

    public DayCycle(int currentHours, int currentMinutes, int currentMonth, int currentDay, int currentDayOfWeek){
        hours = currentHours;
        minutes = currentMinutes;
        month = currentMonth;
        day = currentDay;
        weekDayID = currentDayOfWeek;
        //weekday id should be 0-6. Going to put Sys err for invalid inputs next time


        font = new BitmapFont();
        font.getData().setScale(2);
    }

    public String getCurrentTime(){
        String hourString = hours%12 + "";
        if(hours == 12 || hours == 0){
            hourString = "12";
        }
        String minuteString = minutes + "";

        if(hourString.length() < 2){
            hourString = "0" + hourString;
        }
        if(minuteString.length() < 2){
            minuteString = "0" + minuteString;
        }

        String timeOfDay = "AM";
        if(hours/12 == 1){
            timeOfDay = "PM";
        }

        return timeOfDay + " " + hourString + ":" + minuteString;
    }

    public BitmapFont getFont(){
        return font;
    }

    public Vector2 getPosition(){
        return position;
    }

    public void update(){
        addMinute();
    }

    public void addMinute(){
        if(++minutes > 59){
            minutes = 0;
            hours ++;
        }
        if(hours > 23){
            hours = 0;
            advanceDay();
        }
    }
    public boolean isMorning(){
        return hours >= HUDVars.MORNING_START && hours < HUDVars.AFTERNOON_START;
    }

    public boolean isAfternoon(){
        return hours >= HUDVars.AFTERNOON_START && hours < HUDVars.EVENING_START;
    }

    public boolean isEvening(){
        return hours >= HUDVars.EVENING_START && hours < HUDVars.NIGHT_START;
    }

    public boolean isNight(){
        return !isMorning() && !isAfternoon() && !isEvening();
    }

    public String getTimeOfDay(){
        if(isMorning()){
            return "Morning";
        }
        if(isAfternoon()){
            return "Afternoon";
        }
        if(isEvening()){
            return "Evening";
        }
        return "Night";
    }

    public String getMonthName(){
        return HUDVars.MONTH_NAMES[month-1];
    }

    public int getDay(){
        return day;
    }

    public int getMonth(){
        return month;
    }

    public void advanceDay(){
        //NOT TESTED YET
        if(++day > HUDVars.DAYS_IN_MONTH[month-1]){
            if(++month > 12){
                month = 1;
            }
            day = 1;
        }

        if(++weekDayID > 6){
            weekDayID = 0;
        }
    }

    public String getDayOfWeek(){
        return HUDVars.WEEK_DAY_NAME[weekDayID];
    }

}
