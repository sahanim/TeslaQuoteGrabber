/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stocksapi;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.util.concurrent.*;
import java.time.*;

public class StocksAPI { 
	
    
    public static void main(String[] args) throws IOException { 
        /*
        LocalDateTime present = LocalDateTime.now();

        if(present.getDayOfWeek() == DayOfWeek.MONDAY || present.getDayOfWeek() == DayOfWeek.TUESDAY || present.getDayOfWeek() == DayOfWeek.WEDNESDAY || present.getDayOfWeek() == DayOfWeek.THURSDAY || present.getDayOfWeek() == DayOfWeek.FRIDAY) {
            if(present.getHour() > 8 && present.getHour() < 20){
                final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
                executorService.scheduleAtFixedRate(new Runnable() {

                public void run() {
                myTask();
                }
            }, 0, 10, TimeUnit.SECONDS);

            }
        }

        */

        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(new Runnable() {
            public void run() {
            myTask();
            }
        }, 0, 10, TimeUnit.SECONDS);
    }   

    private static void myTask(){
        Stock tesla = StockFetcher.getStock("TSLA");
        LocalDateTime present = LocalDateTime.now();
        String printdate = Integer.toString(present.getYear()) + "-" + Integer.toString(present.getMonthValue()) + "-" + Integer.toString(present.getDayOfMonth()) ;
        //System.out.println(printdate);
        System.out.println(present.getYear() + " " + present.getMonthValue() + " " + present.getDayOfMonth() + " " + present.getHour() + " " + present.getMinute() + " " + present.getSecond() + " " + tesla.getPrice() + " " + tesla.getVolume());
        if(present.getDayOfWeek() == DayOfWeek.MONDAY || present.getDayOfWeek() == DayOfWeek.TUESDAY || present.getDayOfWeek() == DayOfWeek.WEDNESDAY || present.getDayOfWeek() == DayOfWeek.THURSDAY || present.getDayOfWeek() == DayOfWeek.FRIDAY || present.getDayOfWeek() == DayOfWeek.SATURDAY) {
            if((present.getHour() > 9 && present.getHour() < 20) || (present.getHour() == 9 && present.getMinute() > 29)){
                try(FileWriter fw = new FileWriter("Tesla-" + printdate + ".txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)){
                    out.println(present.getYear() + " " + present.getMonthValue() + " " + present.getDayOfMonth() + " " + present.getHour() + " " + present.getMinute() + " " + present.getSecond() + " " + tesla.getPrice() + " " + tesla.getVolume());
                } catch (IOException e){System.out.println("file error");}
            } else{}
        }


    }
}