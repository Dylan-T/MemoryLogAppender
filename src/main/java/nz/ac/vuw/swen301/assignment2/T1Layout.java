package nz.ac.vuw.swen301.assignment2;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;

import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * This a layout class that uses the freemarker template engine.
 */
public class T1Layout extends Layout {
    private DateFormat date = new SimpleDateFormat();
    private Configuration cfg;
    private String pattern;

    /**
     * Create a layout that formats events using the pattern
     * @param pattern
     */
    public T1Layout(String pattern){
        cfg = new Configuration(Configuration.VERSION_2_3_28);
        cfg.setIncompatibleImprovements(new Version(2, 3, 20));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.US);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        setPattern(pattern);
    }

    /**
     * Creates layout with default pattern of ${m}
     */
    public T1Layout(){
        cfg = new Configuration(Configuration.VERSION_2_3_28);
        cfg.setIncompatibleImprovements(new Version(2, 3, 20));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.US);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        setPattern("${m}");
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
        this.pattern = pattern;
    }

    @Override
    public String format(LoggingEvent event) {
        try {
            Map<String, Object> vars = new HashMap<String, Object>();
            vars.put("c", event.getLoggerName());
            vars.put("d", date.format(event.getTimeStamp()));
            vars.put("m", event.getMessage());
            vars.put("p", event.getLevel().toString());

            Writer out = new StringWriter();
            Template template = new Template("T1Layout.ftl", pattern, cfg);
            template.process(vars, out);
            String s = out.toString();
            out.close();
            return s;
        }catch (Exception e){
            return "";
        }
    }
}