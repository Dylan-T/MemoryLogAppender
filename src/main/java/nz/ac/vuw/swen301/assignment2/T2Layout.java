package nz.ac.vuw.swen301.assignment2;

import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.RuntimeSingleton;
import org.apache.velocity.runtime.parser.node.SimpleNode;

import java.io.StringReader;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Variables that must be supported are:
 * c (category)
 * d (using the default toString() representation or any fixed dataformat is acceptable)
 * m (message)
 * p (priority)
 */
public class T2Layout extends Layout {

    VelocityContext context;
    Template template;
    private DateFormat date = new SimpleDateFormat();

    public T2Layout(String pattern){


        Velocity.init();

        context = new VelocityContext();

        context.put("name", "Velocity");

        try
        {
            RuntimeServices rs = RuntimeSingleton.getRuntimeServices();
            StringReader sr = new StringReader(pattern);
            SimpleNode sn = rs.parse(sr, "User Information");

            template = new Template();
            template.setRuntimeServices(rs);
            template.setData(sn);
            template.initDocument();

            StringWriter sw = new StringWriter();

            template.merge( context, sw );
        }
        catch( ResourceNotFoundException rnfe )
        {
            // couldn't find the template
        }
        catch( ParseErrorException pee )
        {
            // syntax error: problem parsing the template
        }
        catch( MethodInvocationException mie )
        {
            // something invoked in the template
            // threw an exception
        }
        catch( Exception e )
        {}


    }

    public String format(LoggingEvent event) {
        context.put("c", event.getLoggerName());
        context.put("d", date.format(event.getTimeStamp()));
        context.put("m", event.getMessage());
        context.put("p", event.getLevel().toString());

        StringWriter sw = new StringWriter();
        template.merge(context, sw);
        return sw.toString();
    }

    public boolean ignoresThrowable() {
        return false;
    }

    public void activateOptions() {

    }
}
