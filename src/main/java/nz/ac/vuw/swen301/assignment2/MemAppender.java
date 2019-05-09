package nz.ac.vuw.swen301.assignment2;

import org.apache.log4j.*;
import org.apache.log4j.spi.LoggingEvent;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * a. MemAppender stores all log entries converted to strings using a layout in a list (they are not printed to the console!)
 *
 * b. logs can be accessed using the following non-static method:
 *    java.util.List<String> getCurrentLogs()
 *
 * c. the list returned by getCurrentLogs() must not be modifiable
 *
 * d. MemAppender has a property maxSize, if the number of logs reaches maxSize, the oldest logs are discarded.
 *    The number of discarded logs is counted, and this count can be accessed using the getDiscardedLogCount() method in MemAppender that returns this count as a long.
 */
public class MemAppender extends AppenderSkeleton implements MemAppenderMBean {

    private final long maxSize;
    private long discardedLogs = 0;
    private List<String> currentLogs = new ArrayList<String>();
    private Layout layout;

    /**
     * Construct a new MemAppender object
     */
    public MemAppender(Layout layout, long maxSize) {
        //Adds this to the MBean server
        this.layout = layout;
        this.maxSize = maxSize;
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        try {
            mbs.registerMBean(this, new ObjectName("nz.ac.vuw.swen301.assignment2:type=MemAppender:"));
        } catch(Exception e){

        }
    }
    /**
     *
     * @return list containing the current logs, must not be modifiable
     */
    public List<String> getCurrentLogs() {
        return Collections.unmodifiableList(this.currentLogs);
    }

    public long getLogCount() {
        return currentLogs.size();
    }

    /**
     *
     * @return the amount of logs that have been discarded
     */
    public long getDiscardedLogCount(){
        return discardedLogs;
    }

    protected void append(LoggingEvent loggingEvent) {
        if (layout == null) return;
        if(currentLogs.size() == maxSize){
            currentLogs.remove(0);
            discardedLogs++;
        }
        currentLogs.add(layout.format(loggingEvent));
    }

    public void close() {
//        currentLogs.clear();
//        discardedLogs = 0;
//        layout = null;
    }

    public boolean requiresLayout() {
        return true;
    }
}
