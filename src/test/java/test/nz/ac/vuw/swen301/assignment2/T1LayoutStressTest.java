package test.nz.ac.vuw.swen301.assignment2;

import nz.ac.vuw.swen301.assignment2.MemAppender;
import nz.ac.vuw.swen301.assignment2.T1Layout;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.Test;

import java.util.Date;

public class T1LayoutStressTest {
    @Test
    public void test1Min(){
        long numFormatted = 0;
        Layout layout = new T1Layout("${p} - ${c} - ${d} - ${m}");
        Logger logger = Logger.getLogger("Stress2");

        //Pre create event
        Date now=new Date();
        String msg="debug message";
        LoggingEvent event = new LoggingEvent("test",logger,now.getTime(), Level.DEBUG,msg,Thread.currentThread().getName(),null,null,null,null);

        final long NANOSEC_PER_SEC = 1000l*1000*1000;
        long startTime = System.nanoTime();
        while ((System.nanoTime()-startTime)< 60*NANOSEC_PER_SEC){
            layout.format(event);
            numFormatted++;
        }

        System.out.println("==========\n" +
                "T1Layout Stress test\n" +
                "===========\n" +
                "Total logs: " + numFormatted);
    }

}
