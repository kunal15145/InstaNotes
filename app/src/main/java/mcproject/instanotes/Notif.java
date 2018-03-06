package mcproject.instanotes;

/**
 * Created by Harshit Verma on 06-03-2018.
 */

public class Notif {

    private String title;
    private String shortdesc;
    private int image;

    public Notif(String title, String shortdesc, int image) {
        this.title = title;
        this.shortdesc = shortdesc;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    public int getImage() {
        return image;
    }
}
