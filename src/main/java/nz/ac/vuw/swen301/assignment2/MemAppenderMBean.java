package nz.ac.vuw.swen301.assignment2;

import java.util.List;

public interface MemAppenderMBean {
    String[] getTopLogs();

    long getLogCount();

    long getDiscardedLogCount();


}
