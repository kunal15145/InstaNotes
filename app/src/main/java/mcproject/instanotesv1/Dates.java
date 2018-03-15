package mcproject.instanotesv1;

/**
 * Created by Harshit Verma on 15-03-2018.
 */

public class Dates {
    private String title;
    private String shortdesc;
    private int image;

    //Default Constructor
    public Dates(String title, String shortdesc, int image) {
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
