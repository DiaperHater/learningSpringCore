package loggers;

import beans.Event;
import helper.Contexts;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileEventLogger implements EventLogger {

    Path file;

    public FileEventLogger(String fileName) {
        file = Paths.get(fileName);
    }

    private void init() throws IOException {
        if(Files.exists(file)) {
            if(!Files.isWritable(file)) {
                throw new IOException();
            }
        }
    }

    @Override
    public void logEvent(Event event) {
        try {
            String formattedEvent = String.format("%s%n", event);
            Files.write(file, formattedEvent.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

}
