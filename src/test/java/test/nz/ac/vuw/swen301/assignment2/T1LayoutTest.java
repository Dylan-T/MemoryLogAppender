package test.nz.ac.vuw.swen301.assignment2;
import nz.ac.vuw.swen301.assignment2.MemAppender;
import nz.ac.vuw.swen301.assignment2.T1Layout;
import org.apache.log4j.*;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.*;
import org.junit.Assert;

import static org.junit.Assert.assertEquals;

public class T1LayoutTest {
    @Before
    public void TestSetup(){

    }

    @After
    public void TestCleanup(){

    }

    @Test
    public void test1(){
        Logger logger = Logger.getLogger("test1");
        Layout layout = new T1Layout("${m}");
        MemAppender memAppender = new MemAppender(layout, 1000);
        logger.addAppender(memAppender);
        logger.error("Message");
        assertEquals("Message", memAppender.getCurrentLogs().get(0));
    }
}
