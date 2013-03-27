package ca.ulaval.glo4002.centralServer.user;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Alarm {

    public enum Type {
        FIRE, INTRUSION
    };

    private Type type;
    private Date date;

    public Alarm(Type type, Date date) {
        this.type = type;
        this.date = date;
    }

    public String getInformationForLogPurpose() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd,yyyy HH:mm");
        String formatedDate = dateFormat.format(date);

        String log = "Type=" + type.toString() + ", date=" + formatedDate + "\n";

        return log;
    }

}
