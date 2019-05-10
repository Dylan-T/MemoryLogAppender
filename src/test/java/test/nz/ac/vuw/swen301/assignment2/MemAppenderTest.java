package test.nz.ac.vuw.swen301.assignment2;
import nz.ac.vuw.swen301.assignment2.MemAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.junit.*;

public class MemAppenderTest {

    @Test
    public void testAppend(){
        Logger logger = Logger.getLogger("test1");
        Layout layout = new PatternLayout();
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

    @Test
    public void testRequiresLayout(){
        Logger logger = Logger.getLogger("test1");
        MemAppender memAppender = new MemAppender(null, 1000);
        logger.addAppender(memAppender);
        logger.error("Message");
        assert (memAppender.requiresLayout());
        assert(memAppender.getCurrentLogs().size() == 0);
    }
}
