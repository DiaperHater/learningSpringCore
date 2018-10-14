import beans.Client;
import beans.Event;
import beans.EventType;
import loggers.EventLogger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class App {

    private Client client;
    private EventLogger defaultLogger;
    private Map<EventType, EventLogger> loggers;

    static final ConfigurableApplicationContext context =
            new ClassPathXmlApplicationContext("spring.xml");

    public App(Client client, EventLogger defaultLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.defaultLogger = defaultLogger;
        this.loggers = loggers;
    }

    public static void main(String[] args) {

        App app = (App) context.getBean("app");

        app.logEvent(EventType.INFO, (Event) context.getBean("event"), "Hello 0");
        app.logEvent(EventType.ERROR, (Event) context.getBean("event"), "fuck 0");
        app.logEvent(EventType.INFO, (Event) context.getBean("event"), "suck 0");
        app.logEvent(null, (Event) context.getBean("event"), "buy 0");

        context.registerShutdownHook();

    }


    public void logEvent(EventType type, Event event, String msg) {
        String processedMessage = msg.replace(String.valueOf(client.getId()), client.getFullName());
        event.setMsg(processedMessage + client.getGreeting());

        EventLogger logger = loggers.get(type);
        if (logger == null) {
            logger = defaultLogger;
        }

        logger.logEvent(event);
    }

}
