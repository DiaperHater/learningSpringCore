package helper;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Contexts {
    private static ClassPathXmlApplicationContext context =
            new ClassPathXmlApplicationContext("spring.xml", "loggers.xml");

    public static ApplicationContext getApplicationContext() {
        return context;
    }

    public static ConfigurableApplicationContext getConfigurableApplicationContext() {
        return context;
    }
}
