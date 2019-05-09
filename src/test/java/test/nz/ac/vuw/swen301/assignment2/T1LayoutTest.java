package test.nz.ac.vuw.swen301.assignment2;

import nz.ac.vuw.swen301.assignment2.MemAppender;
import nz.ac.vuw.swen301.assignment2.T1Layout;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class T1LayoutTest {
    @Before
    public void TestSetup(){

    }

    @After
    public void TestCleanup(){

    }

    @Test
    public void testVarM(){
        Logger logger = Logger.getLogger("test1");
        Layout layout = new T1Layout("${m}");
        MemAppender memAppender = new MemAppender(layout, 1000);
        logger.addAppender(memAppender);
        logger.error("Message");
        assertEquals("Message", memAppender.getCurrentLogs().get(0));
    }

    @Test
    public void testVarC(){
        Logger logger = Logger.getLogger("category");
        Layout layout = new T1Layout("${c}");
        MemAppender memAppender = new MemAppender(layout, 1000);
        logger.addAppender(memAppender);
        logger.error("Message");
        assertEquals("category", memAppender.getCurrentLogs().get(0));
    }

    @Test
    public void testVarP(){
        Logger logger = Logger.getLogger("category");
        Layout layout = new T1Layout("${p}");
        MemAppender memAppender = new MemAppender(layout, 1000);
        logger.addAppender(memAppender);
        logger.error("Message");
        assertEquals("ERROR", memAppender.getCurrentLogs().get(0));
    }

    @Test
    public void testVarD(){
        Logger logger = Logger.getLogger("category");
        Layout layout = new T1Layout("${d}");
        MemAppender memAppender = new MemAppender(layout, 1000);
        logger.addAppender(memAppender);
        logger.error("Message");
        assertEquals(new SimpleDateFormat().format(new Date().getTime()), memAppender.getCurrentLogs().get(0));
    }
}
