package test.nz.ac.vuw.swen301.assignment2;
import nz.ac.vuw.swen301.assignment2.MemAppender;
import org.apache.log4j.*;
import org.junit.*;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class MemAppenderTest {

    @Test
    public void testAppend1(){
        Logger logger = Logger.getLogger("test1");
        Layout layout = new PatternLayout();
        MemAppender memAppender = new MemAppender(layout, 1000);
        logger.addAppender(memAppender);
        logger.error("Message");
        assert(memAppender.getCurrentLogs().size() == 1);
    }

    @Test
    public void testAppend2(){
        Logger logger = Logger.getLogger("test1");
        Layout layout = new SimpleLayout();
        MemAppender memAppender = new MemAppender(layout, 1000);
        logger.addAppender(memAppender);
        logger.error("Message");
        assert(memAppender.getCurrentLogs().size() == 1);
    }

    @Test
    public void testMaxSize(){
        Logger logger = Logger.getLogger("test1");
        Layout layout = new PatternLayout("%m");
        MemAppender memAppender = new MemAppender(layout, 10);
        logger.addAppender(memAppender);
        for(int i = 0; i < 11; i++){
            logger.error("" + i);
        }
        assert(memAppender.getDiscardedLogCount() == 1);
        assert(!memAppender.getCurrentLogs().contains("0"));
        assert(memAppender.getLogCount() == 10);
    }

    @Test
    public void testClose(){
        Logger logger = Logger.getLogger("test1");
        Layout layout = new PatternLayout();
        MemAppender memAppender = new MemAppender(layout, 1000);
        logger.addAppender(memAppender);
        logger.error("Message");
        assert(memAppender.getCurrentLogs().size() == 1);
        memAppender.close();
        assert (memAppender.getLogCount() == 0);
        assert (memAppender.getDiscardedLogCount() == 0);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUnmodifiableList(){
        Logger logger = Logger.getLogger("test1");
        Layout layout = new PatternLayout();
        MemAppender memAppender = new MemAppender(layout, 1000);
        logger.addAppender(memAppender);
        logger.error("Message");
        List<String> logs = memAppender.getCurrentLogs();
        logs.add("test");
        assertEquals(1, logs.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRequiresLayout(){
        MemAppender memAppender = new MemAppender(null, 1000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void changeLayoutNull(){
        MemAppender memAppender = new MemAppender(new PatternLayout(), 1000);
        assert(memAppender.requiresLayout());
        memAppender.setLayout(null);
    }

    @Test
    public void testTop10Logs(){
        Logger logger = Logger.getLogger("test1");
        Layout layout = new PatternLayout();
        MemAppender memAppender = new MemAppender(layout, 1000);
        logger.addAppender(memAppender);
        for(int i = 0; i <20; i++){
            logger.error("" + i);
        }
        assertEquals(10, memAppender.getTopLogs().size());
        for(int i = 10; i < 20; i++) {
            assert (!memAppender.getCurrentLogs().contains("" + i));
        }
    }


    @Test
    public void testERROR(){
        Logger logger = Logger.getLogger("test1");
        Layout layout = new PatternLayout();
        MemAppender memAppender = new MemAppender(layout, 1000);
        logger.addAppender(memAppender);
        logger.error("Message");
        assertEquals(memAppender.getCurrentLogs().size(), 1);
    }

    @Test
    public void testDEBUG(){
        Logger logger = Logger.getLogger("test1");
        Layout layout = new PatternLayout();
        MemAppender memAppender = new MemAppender(layout, 1000);
        logger.addAppender(memAppender);
        logger.debug("message");
        assertEquals(memAppender.getCurrentLogs().size(), 1);
    }

    @Test
    public void testINFO(){
        Logger logger = Logger.getLogger("test1");
        Layout layout = new PatternLayout();
        MemAppender memAppender = new MemAppender(layout, 1000);
        logger.addAppender(memAppender);
        logger.info("message");
        assertEquals(memAppender.getCurrentLogs().size(), 1);
    }

    @Test
    public void testWARN(){
        Logger logger = Logger.getLogger("test1");
        Layout layout = new PatternLayout();
        MemAppender memAppender = new MemAppender(layout, 1000);
        logger.addAppender(memAppender);
        logger.warn("message");
        assertEquals(memAppender.getCurrentLogs().size(), 1);
    }

    @Test
    public void testFATAL(){
        Logger logger = Logger.getLogger("test1");
        Layout layout = new PatternLayout();
        MemAppender memAppender = new MemAppender(layout, 1000);
        logger.addAppender(memAppender);
        logger.fatal("Message");
        assertEquals(memAppender.getCurrentLogs().size(), 1);
    }

    @Test
    public void testTrace(){
        Logger logger = Logger.getLogger("test1");
        logger.setLevel(Level.TRACE);
        Layout layout = new PatternLayout();
        MemAppender memAppender = new MemAppender(layout, 1000);
        logger.addAppender(memAppender);
        logger.trace("Message");
        assertEquals(memAppender.getCurrentLogs().size(), 1);
    }
}
