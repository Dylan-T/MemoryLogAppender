package nz.ac.vuw.swen301.assignment2;

import java.util.List;

public interface MemAppenderMBean {
    List<String> getCurrentLogs();

    long getLogCount();

    long getDiscardedLogCount();


}
