package javagame;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Timer{
   private final static Date StartedTime = new Date();   
   private static Date TempDate;
   
  // public long timeForChange = 0 ;
   
   public static void setNow(){
       TempDate =  new Date();
   }
   
   public static Date getNow(){
       return new Date();
   }
   
   public static void setStartedTime(Date tempDate){
       TempDate = tempDate;
   }
   
   public static Date getStartedTime(){
       return StartedTime;
   }
   
   public static long DifferenceTime(Date currentTime){
       return (currentTime.getTime()-StartedTime.getTime())/1000;
   }
   
   public static long DifferenceTime(){
       return (new Date().getTime()-StartedTime.getTime())/1000;
   }

   
   public String getTimeForChange()
   {
        Date nowTime = new Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("ss"); 
        return timeFormat.format(nowTime);
   }
}
