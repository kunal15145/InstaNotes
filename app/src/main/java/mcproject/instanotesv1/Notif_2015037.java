package mcproject.instanotesv1;


public class Notif_2015037 {

    private String title;
    private String shortdesc;
    private int image;

    //Default constructor
    public Notif_2015037(String title, String shortdesc, int image) {
        this.title = title;
        this.shortdesc = shortdesc;
        this.image = image;
    }
    // Getter functions
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
