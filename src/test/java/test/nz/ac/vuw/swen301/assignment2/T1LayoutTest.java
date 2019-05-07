package test.nz.ac.vuw.swen301.assignment2;
import nz.ac.vuw.swen301.assignment2.MemAppender;
import nz.ac.vuw.swen301.assignment2.T1Layout;
import org.apache.log4j.*;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.*;

public class T1LayoutTest {
    @Before
    public void TestSetup(){

    }

    @After
    public void TestCleanup(){

    }

    @Test
    public void testAppend(){
        Layout layout = new T1Layout(null);

        MemAppender appender=new MemAppender();
        String msg="debug message";
        Logger logger = null; //Logger.getLogger();
        LoggingEvent event=new LoggingEvent("com.chililog.server.engine",logger,1, Level.DEBUG ,msg,Thread.currentThread().getName(),null,null,null,null);
    }
}
