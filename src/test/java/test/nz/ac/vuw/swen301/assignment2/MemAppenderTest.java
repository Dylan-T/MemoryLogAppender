package test.nz.ac.vuw.swen301.assignment2;
import nz.ac.vuw.swen301.assignment2.MemAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.junit.*;

public class MemAppenderTest {

    @Before
    public void TestSetup(){

    }

    @After
    public void TestCleanup(){

    }

    @Test
    public void testAppend(){
        Logger logger = Logger.getLogger("test1");
        Layout layout = new PatternLayout();
        MemAppender memAppender = new MemAppender(layout, 1000);
        logger.addAppender(memAppender);
        logger.error("Message");
        assert(memAppender.getCurrentLogs().size() == 1);
    }
}
