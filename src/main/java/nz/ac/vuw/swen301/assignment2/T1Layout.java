package nz.ac.vuw.swen301.assignment2;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;

import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * This a layout class that uses the freemarker template engine.
 */
public class T1Layout extends Layout {
    private DateFormat date = new SimpleDateFormat();
    private Configuration cfg;

    /**
     * Create a layout that formats events using the pattern
     * @param pattern
     */
    public T1Layout(String pattern){
        cfg = new Configuration(Configuration.VERSION_2_3_28);
        setPattern(pattern);
    }

    public void activateOptions() {
        //PATTERN LAYOUT DIDNT IMPLEMENT THIS SO NEITHER HAVE I
    }

    @Override
    public boolean ignoresThrowable() {
        return true;
    }

    /**
     * Sets the pattern for events to be formatted with
     * @param pattern
     */
    public void setPattern(String pattern){
        StringTemplateLoader sLoader = new StringTemplateLoader();
        sLoader.putTemplate("pattern", pattern);
        cfg.setTemplateLoader(sLoader);
    }

    @Override
    public String format(LoggingEvent event) {
        try {
            Writer out = new StringWriter();

            Map<String, Object> vars = new HashMap<String, Object>();
            vars.put("c", event.getLoggerName());
            vars.put("d", date.format(event.getTimeStamp()));
            vars.put("m", event.getMessage());
            vars.put("p", event.getLevel().toString());
            cfg.getTemplate("pattern").process(vars, out);
            String s = out.toString();
            out.close();
            return s;
        }catch (Exception e){
            return "";
        }
    }
}
