package mcproject.instanotesv1;

/**
 * Created by Harshit Verma on 15-03-2018.
 */

public class DatesPRIVATE {
    private String title,dateperson;

    private String shortdesc;
    private int image;

    public DatesPRIVATE(String title, String shortdesc, int image) {
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
