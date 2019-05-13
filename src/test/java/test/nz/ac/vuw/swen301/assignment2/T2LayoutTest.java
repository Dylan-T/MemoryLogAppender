package test.nz.ac.vuw.swen301.assignment2;
import nz.ac.vuw.swen301.assignment2.MemAppender;
import nz.ac.vuw.swen301.assignment2.T2Layout;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.junit.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class T2LayoutTest {

    @Test
    public void testVarM(){
        Logger logger = Logger.getLogger("T2test1");
        Layout layout = new T2Layout("$m");
        MemAppender memAppender = new MemAppender(layout, 1000);
        logger.addAppender(memAppender);
        logger.error("Message");
        assertEquals("Message", memAppender.getCurrentLogs().get(0));
    }

    @Test
    public void testVarC(){
        Logger logger = Logger.getLogger("T2test2");
        Layout layout = new T2Layout("$c");
        MemAppender memAppender = new MemAppender(layout, 1000);
        logger.addAppender(memAppender);
        logger.error("Message");
        assertEquals("T2test2", memAppender.getCurrentLogs().get(0));
    }

    @Test
    public void testVarP(){
        Logger logger = Logger.getLogger("T2test3");
        Layout layout = new T2Layout("$p");
        MemAppender memAppender = new MemAppender(layout, 1000);
        logger.addAppender(memAppender);
        logger.error("Message");
        assertEquals("ERROR", memAppender.getCurrentLogs().get(0));
    }

    @Test
    public void testVarD(){
        Logger logger = Logger.getLogger("T2test4");
        Layout layout = new T2Layout("$d");
        MemAppender memAppender = new MemAppender(layout, 1000);
        logger.addAppender(memAppender);
        logger.error("Message");
        assertEquals(new SimpleDateFormat().format(new Date().getTime()), memAppender.getCurrentLogs().get(0));
    }

    @Test
    public void testIgnoresThrowables(){
        Layout layout = new T2Layout("$d");
        assert(layout.ignoresThrowable());
    }

    @Test
    public void testSetPattern(){
        Logger logger = Logger.getLogger("T2test5");
        T2Layout layout = new T2Layout("$m");
        MemAppender memAppender = new MemAppender(layout, 1000);
        logger.addAppender(memAppender);
        logger.error("Message");
        assertEquals("Message", memAppender.getCurrentLogs().get(0));
        layout.setPattern("${p}");
        logger.error("Message");
        assertEquals("ERROR", memAppender.getCurrentLogs().get(1));
    }

    @Test
    public void testDefaultPattern(){
        Logger logger = Logger.getLogger("T1test6");
        Layout layout = new T2Layout();
        MemAppender memAppender = new MemAppender(layout, 1000);
        logger.addAppender(memAppender);
        logger.error("Message");
        assertEquals("Message", memAppender.getCurrentLogs().get(0));
    }
}
