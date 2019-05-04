package nz.ac.vuw.swen301.assignment2;

import org.apache.log4j.*;
import org.apache.log4j.spi.LoggingEvent;
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
public class MemAppender extends AppenderSkeleton{

    private final long maxSize = 1000;
    private int discardedLogs = 0;
    private List<String> currentLogs;

    /**
     * Construct a new MemAppender object
     */
    public MemAppender(){

    }

    /**
     *
     * @return list containing the current logs, must not be modifiable
     */
    public List<String> getCurrentLogs() {
        return currentLogs;
    }

    /**
     *
     * @return the amount of logs that have been discarded
     */
    public long getDiscardedLogCount(){
        return discardedLogs;
    }

    protected void append(LoggingEvent loggingEvent) {

    }

    public void close() {

    }

    public boolean requiresLayout() {
        return false;
    }
}
