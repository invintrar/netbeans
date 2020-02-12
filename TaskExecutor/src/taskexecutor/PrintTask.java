/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskexecutor;

//Fig. 23.3: PrintTask.java
//PrintTask class sleeps for a random time from 0 to 5 seconds
import java.security.SecureRandom;

/**
 *
 * @author Darwin
 */
public class PrintTask implements Runnable {

    private static final SecureRandom generator = new SecureRandom();
    private final int sleepTime;    //random sleep time for thread
    private final String taskName;

    //constructor
    public PrintTask(String taskName) {
        this.taskName = taskName;

        //pick random sleep time between 0 and 5 second
        sleepTime = generator.nextInt(10000);

    }

    //method run contains the code that a thread will execute
    public void run() {
        //put thread to sleep for sleepTime amount of time
        try 
        {
            System.out.printf("%s going to sleep for %d milliseconds.%n",
                    taskName,sleepTime);
            Thread.sleep(sleepTime);//put thread to sleep 
       }
        catch(InterruptedException exception){
            exception.printStackTrace();
            Thread.currentThread().interrupt();//re interrupt the thread
        }
        //print task name
        System.out.printf("%s done sleeping%n",taskName);
    }//end method run
}//fin clase PrintTask
