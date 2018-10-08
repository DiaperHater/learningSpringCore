import beans.Client;
import beans.Event;
import loggers.EventLogger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class App {

    private Client client;
    private EventLogger defaultLogger;

    static final ConfigurableApplicationContext context =
            new ClassPathXmlApplicationContext("spring.xml");

    public App(Client client, EventLogger defaultLogger) {
        this.client = client;
        this.defaultLogger = defaultLogger;
    }

    public static void main(String[] args) {

        App app = (App) context.getBean("app");

        app.logEvent((Event) context.getBean("event"), "hello! 0");

        context.registerShutdownHook();

    }

    public void logEvent(Event event, String msg) {
        String processedMsg = msg.replace(String.valueOf(client.getId()), client.getFullName());
        event.setMsg(processedMsg);
        defaultLogger.logEvent(event);
    }
}
