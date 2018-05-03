package mcproject.instanotesv1;

import java.util.Date;

public class Notification_send_2015037 {

    private String user;
    private String date;
    private String courseName;
    private int count;

    public Notification_send_2015037(int count, String user, String date, String courseName){
        this.count = count;
        this.user = user;
        this.date = date;
        this.courseName = courseName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
