package test.nz.ac.vuw.swen301.assignment2;

import nz.ac.vuw.swen301.assignment2.MemAppender;
import org.apache.log4j.*;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.Test;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.util.Date;

public class MemAppenderStressTest {

    @Test
    public void test1Min(){
        //Logger logger = Logger.getLogger("test1");
        Layout layout = new PatternLayout();
        MemAppender memAppender = new MemAppender(layout, 1000);
        //logger.addAppender(memAppender);

        //MBEAN
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        try {
            ObjectName name = new ObjectName("nz.ac.vuw.swen301.assignment2:type=MemAppender");
            mbs.registerMBean(memAppender, name);

        } catch (Exception e){

        }

        //Pre create event
        Date now=new Date();
        String msg="debug message";
        LoggingEvent event = new LoggingEvent("test",null,now.getTime(),Level.DEBUG,msg,Thread.currentThread().getName(),null,null,null,null);



        final long NANOSEC_PER_SEC = 1000l*1000*1000;
        long startTime = System.nanoTime();
        while ((System.nanoTime()-startTime)< 60*NANOSEC_PER_SEC){
            memAppender.doAppend(event);
        }
        System.out.println("==========\n" +
                "Stress test\n" +
                "===========\n" +
                "Total logs: " + (memAppender.getCurrentLogs().size() + memAppender.getDiscardedLogCount()));
    }
}