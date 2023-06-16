package com.example.andoidday;

import java.util.Calendar;

public class Function {
    public static String wishMe(){
    String s = "";
    Calendar c = Calendar.getInstance();
    int time = c.get(Calendar.HOUR_OF_DAY);

        if (time >= 0 && time < 12) {
            s = "Good Morning Sir!";
        }else if (time >= 12 && time < 16) {
                s = "Good Afternoon Master!";
        }else if (time >= 16 && time < 20) {
                s = "Good Evening Master!";
        }else if (time >= 20 && time < 22){
                s = "Good Night Master";
        }else if (time >= 22 && time < 24){
            s = "You need to take rest Master... it's too late :";
        }
        return s;
    }
}