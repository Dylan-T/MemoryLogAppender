package nz.ac.vuw.swen301.assignment2;

import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.RuntimeSingleton;
import org.apache.velocity.runtime.parser.node.SimpleNode;

import java.io.StringReader;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * This is a layout that uses the velocity template engine
 */
public class T2Layout extends Layout {

    private VelocityContext context;
    private Template template;
    private DateFormat date = new SimpleDateFormat();

    /**
     * Creates a layout that formats events according to the pattern
     * @param pattern
     */
    public T2Layout(String pattern){
        Velocity.init();
        context = new VelocityContext();
        setPattern(pattern);
    }

    /**
     * Creates a layout using the default pattern
     */
    public T2Layout(){
        Velocity.init();
        context = new VelocityContext();
        setPattern("$m");
    }

    @Override
    public String format(LoggingEvent event) {
        context.put("c", event.getLoggerName());
        context.put("d", date.format(event.getTimeStamp()));
        context.put("m", event.getMessage());
        context.put("p", event.getLevel().toString());

        StringWriter sw = new StringWriter();
        template.merge(context, sw);
        return sw.toString();
    }

    /**
     * Sets the layouts pattern that it formats events into
     * @param pattern
     */
    public void setPattern(String pattern){
        try
        {
            RuntimeServices rs = RuntimeSingleton.getRuntimeServices();
            StringReader sr = new StringReader(pattern);
            SimpleNode sn = rs.parse(sr, "User Information");

            template = new Template();
            template.setRuntimeServices(rs);
            template.setData(sn);
            template.initDocument();

        }catch( Exception e ) {
            //Something went wrong
        }
    }

    @Override
    public boolean ignoresThrowable() {
        return true;
    }


    public void activateOptions() { }
}
