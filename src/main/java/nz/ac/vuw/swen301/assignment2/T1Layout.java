package nz.ac.vuw.swen301.assignment2;

import org.apache.log4j.Layout;
import org.apache.log4j.helpers.PatternConverter;
import org.apache.log4j.helpers.PatternParser;
import org.apache.log4j.spi.LoggingEvent;
import freemarker.template.*;
import java.io.*;
import java.util.*;
import freemarker.log.Logger;
import org.apache.log4j.BasicConfigurator;

/**
 * Variables that must be supported are:
 * c (category)
 * d (using the default toString() representation or any fixed dataformat is acceptable)
 * m (message)
 * p (priority)
 */
public class T1Layout extends Layout {

    public static final String DEFAULT_PATTERN = "%c%d%m%p";
    private String pattern;
    private Template temp;

    public T1Layout(String pattern){
        /* ------------------------------------------------------------------------ */
        /* You should do this ONLY ONCE in the whole application life-cycle:        */

        /* Create and adjust the configuration singleton */
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_27);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);

        if(pattern == null){
            pattern = DEFAULT_PATTERN;
        }
        this.pattern = pattern;


        /* Get the template */
        try {
            temp = new Template("pattern", new StringReader(pattern), cfg);
        } catch (Exception e){

        }

    }

    public void activateOptions() {
    }

    public boolean ignoresThrowable() {
        return true;
    }


    public String format(LoggingEvent event) {
        try {
            /* Merge data-model with template */
            Writer out = new StringWriter();
            temp.process(event, out);
            String s = out.toString();
            out.close();
            return s;
        }catch (Exception e){
            return null;
        }
    }
}
