import beans.Client;
import beans.Event;
import loggers.EventLogger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    private Client client;
    private EventLogger logger;

    static final ConfigurableApplicationContext context =
            new ClassPathXmlApplicationContext("spring.xml");

    public App(Client client, EventLogger logger) {
        this.client = client;
        this.logger = logger;
    }

    public static void main(String[] args) {

        App app = (App) context.getBean("app");

        app.logEvent("hello console");
        context.registerShutdownHook();

    }

    public void logEvent(String msg) {
        String processedMessage = msg.replace(String.valueOf(client.getId()), client.getFullName());
        Event e = (Event) context.getBean("event");
        e.setMsg(processedMessage);

        logger.logEvent(e);
    }
}
