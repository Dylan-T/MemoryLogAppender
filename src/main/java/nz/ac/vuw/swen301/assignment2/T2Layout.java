package nz.ac.vuw.swen301.assignment2;

import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.velocity.*;

/**
 * Variables that must be supported are:
 * c (category)
 * d (using the default toString() representation or any fixed dataformat is acceptable)
 * m (message)
 * p (priority)
 */
public class T2Layout extends Layout {

    public T2Layout(){
    }

    public String format(LoggingEvent loggingEvent) {
        return null;
    }

    public boolean ignoresThrowable() {
        return false;
    }

    public void activateOptions() {

    }
}
