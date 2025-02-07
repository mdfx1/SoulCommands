package de.elia.utils;

import org.jetbrains.annotations.NotNull;

public class MessageUtils {

  @NotNull
  public static String shortInteger(@NotNull int duration) {
    String string = "";
    int hours = 0;
    int minutes = 0;
    int seconds = 0;
    if(duration / 60 / 60 >=1) {
      hours = duration / 60 /60;
      duration = duration - ((duration / 60 / 60) * 60 * 60);
    }
    if(duration / 60 >= 1) {
      minutes = duration / 60;
      duration = duration - ((duration /60)*60);
    }
    if(duration >=1) {
      seconds = duration;
    }
    if(hours!=0) {
      if (hours <= 9) {
        string = string + "0" + hours + "h ";
      } else {
        string = string + hours + "h ";
      }
    }
    if(minutes!=0) {
      if (minutes <= 9) {
        string = string + "0" + minutes + "m ";
      } else {
        string = string + minutes + "m ";
      }
    }
    if(seconds <=9) {
      string= string+"0"+seconds+"s";
    }else{
      string= string+seconds+"s";
    }
    return string;
  }
}
