package loggers;

import beans.Event;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsoleEventLogger implements EventLogger {
    @Override
    public void logEvent(Event event) {
        System.out.println(event);
    }

    public static void main(String[] args) {
        ApplicationContext cntxt = new ClassPathXmlApplicationContext("spring.xml");
        ConsoleEventLogger logger = (ConsoleEventLogger) cntxt.getBean("consoleEventLogger");
        Event e = (Event) cntxt.getBean("event");
        e.setMsg("hello");
        logger.logEvent(e);
    }
}
