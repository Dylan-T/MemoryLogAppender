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
 * Variables that must be supported are:
 * c (category) -> categoryName -> logger.toString() ????
 * d (using the default toString() representation or any fixed dataformat is acceptable)
 * m (message)
 * p (priority) -> level.toString()
 */
public class T1Layout extends Layout {
    public static final String DEFAULT_PATTERN = "${m}";
    private DateFormat date = new SimpleDateFormat();
    private Configuration cfg;

    public T1Layout(String pattern){
        if(pattern == null){
            return;
        }

        cfg = new Configuration(Configuration.VERSION_2_3_28);


        StringTemplateLoader sLoader = new StringTemplateLoader();
        sLoader.putTemplate("pattern", pattern);
        cfg.setTemplateLoader(sLoader);
    }

    public void activateOptions() {
    }

    public boolean ignoresThrowable() {
        return true;
    }


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
            return null;
        }
    }
}
