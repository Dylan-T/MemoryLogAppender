package nz.ac.vuw.swen301.assignment2;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * An appender that appends logs to the systems memory
 */
public class MemAppender extends AppenderSkeleton implements MemAppenderMBean {

    private final long maxSize;
    private long discardedLogs = 0;
    private List<String> currentLogs;
    private Layout layout;

    /**
     * Construct a new MemAppender object
     */
    public MemAppender(Layout layout, long maxSize) {
        if(layout == null){
            throw new IllegalArgumentException();
        }
        currentLogs = new ArrayList<String>();
        this.layout = layout;
        this.maxSize = maxSize;
    }

    /**
     * Returns the logs currently stored
     * @return list containing the current logs, must not be modifiable
     */
    public List<String> getCurrentLogs() {
        return Collections.unmodifiableList(this.currentLogs);
    }

    /**
     * Returns the 10 most recent logs stored
     * @return
     */
    public String[] getTopLogs() {
        //Check if <10 logs stored
        int numLogs = 10;
        if(getCurrentLogs().size() < 10){
            numLogs = getCurrentLogs().size();
        }

        //Add 10 most recent to array
        String[] topLogs = new String[10];
        for(int i = 0; i < numLogs; i++){
            topLogs[i] = currentLogs.get(currentLogs.size()-1-i);
        }
        return topLogs;
    }

    /**
     * Returns the number of logs that are currently stored
     * @return
     */
    public long getLogCount() {
        return currentLogs.size();
    }

    /**
     * Returns the amount of discarded logs
     * @return the amount of logs that have been discarded
     */
    public long getDiscardedLogCount(){
        return discardedLogs;
    }

    @Override
    protected void append(LoggingEvent loggingEvent) {
        if(currentLogs.size() == maxSize){
            currentLogs.remove(0);
            discardedLogs++;
        }
        currentLogs.add(layout.format(loggingEvent));
    }

    /**
     * resets the appender's memory and fields
     */
    public void close() {
        currentLogs.clear();
        discardedLogs = 0;
        this.closed = true;
    }

    /**
     * Returns whether this appender requires a layout
     * @return
     */
    public boolean requiresLayout() {
        return true;
    }

    @Override
    public void setLayout(Layout layout){
        if(layout == null){
            throw new IllegalArgumentException();
        }
        this.layout = layout;
    }
}
