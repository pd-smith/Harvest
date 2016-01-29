package com.harvest.hud_elements;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Patty on 1/29/16.
 */
public class DayCycle {

    int hours, minutes;
    Vector2 position;
    BitmapFont font;

    public DayCycle(){
        hours = 12;
        minutes = 0;
        position = new Vector2(950,90); //needs to be mathematical later on, not just 2 'random' numbers
        font = new BitmapFont();
    }

    public DayCycle(int currentHours, int currentMinutes){
        hours = currentHours;
        minutes = currentMinutes;
        font = new BitmapFont();
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

        return hourString + ":" + minuteString + " " + timeOfDay;
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
        }
    }
}
