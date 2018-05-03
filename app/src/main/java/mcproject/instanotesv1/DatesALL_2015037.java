package mcproject.instanotesv1;

/**
 * Created by Harshit Verma on 15-03-2018.
 */

public class DatesALL_2015037 {
    private String title,dateperson;

    private String shortdesc;
    private int image;

    public DatesALL_2015037(String title, String dateperson, String shortdesc, int image) {
        this.title = title;
        this.dateperson = dateperson;
        this.shortdesc = shortdesc;
        this.image = image;
    }

    public String getDateperson() {
        return dateperson;
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
