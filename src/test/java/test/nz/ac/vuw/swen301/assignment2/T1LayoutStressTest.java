package test.nz.ac.vuw.swen301.assignment2;
import nz.ac.vuw.swen301.assignment2.MemAppender;
import nz.ac.vuw.swen301.assignment2.T1Layout;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.junit.*;

public class T1LayoutStressTest {
    @Test
    public void test1Min(){
        Logger logger = Logger.getLogger("test1");
        Layout layout = new T1Layout("${p} - ${c} - ${d} - ${m}");
        MemAppender memAppender = new MemAppender(layout, 1000);
        logger.addAppender(memAppender);

        final long NANOSEC_PER_SEC = 1000l*1000*1000;
        long startTime = System.nanoTime();
        while ((System.nanoTime()-startTime)< 60*NANOSEC_PER_SEC){
            logger.error("test");
        }
        System.out.println("==========\n" +
                "Stress test\n" +
                "===========\n" +
                "Total logs: " + (memAppender.getCurrentLogs().size() + memAppender.getDiscardedLogCount()));
    }

}
