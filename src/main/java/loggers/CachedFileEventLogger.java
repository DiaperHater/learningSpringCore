package loggers;

import beans.Event;

import java.util.ArrayList;
import java.util.List;

public class CachedFileEventLogger extends FileEventLogger {

    private int cacheSize;
    private List<Event> eventList;

    public CachedFileEventLogger(String fileName, int cacheSize) {
        super(fileName);
        this.cacheSize = cacheSize;
        eventList = new ArrayList<>(cacheSize);
    }


    private void destroy() {
        writeEventsToFile();
        eventList.clear();
    }
    @Override
    public void logEvent(Event event) {
        eventList.add(event);
        if (eventList.size() == cacheSize){
            writeEventsToFile();
            eventList.clear();
        }
    }

    private void writeEventsToFile() {
        eventList.stream().forEach(super::logEvent);
    }
}
