package javagame;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Timer implements Runnable{
   private final static Date StartedTime = new Date();   
   private static Date TempDate;
   
   public long timeForChange = 0 ;
   
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

    @Override
    public void run() {
        
       while(true)
            {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Timer.class.getName()).log(Level.SEVERE, null, ex);
            }
            timeForChange += 1;
       }
       
    }
   public long getTimeForChange()
   {
       return timeForChange;
   }
}
