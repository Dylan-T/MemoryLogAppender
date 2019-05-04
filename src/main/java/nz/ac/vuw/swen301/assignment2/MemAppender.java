package nz.ac.vuw.swen301.assignment2;

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
public class MemAppender {
}
