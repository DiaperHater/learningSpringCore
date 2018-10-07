package loggers;

import beans.Event;

import java.util.List;

public class CombinedEventLogger implements EventLogger {

    List<EventLogger> loggers;

    public CombinedEventLogger(List<EventLogger> loggers) {
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event event) {
        for (EventLogger evLo : loggers){
            evLo.logEvent(event);
        }
    }
}
