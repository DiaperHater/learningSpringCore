package beans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.DateFormat;
import java.util.Date;

public class Event {
    private static int counter = 0;
    private final int id = counter++;
    private String msg;
    private Date date;
    private DateFormat dateFormat;

    public Event(Date date, DateFormat dateFormat) {
        this.date = date;
        this.dateFormat = dateFormat;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getId() {
        return id;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "beans.Event{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", date=" + dateFormat.format(date) +
                '}';
    }

    public static void main(String[] args) {
        ApplicationContext cntxt = new ClassPathXmlApplicationContext("spring.xml");

        Event e = (Event) cntxt.getBean("event");

        e.setMsg("hello from spring");
        System.out.println(e);

    }
}
