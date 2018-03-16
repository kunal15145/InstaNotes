package mcproject.instanotesv1;

/**
 * Created by Harshit Verma on 15-03-2018.
 */

public class Dates {
    private String title,dateperson;

    private String shortdesc;
    private int image,datep1,datep2,datep3;

    public Dates(String title, String dateperson, String shortdesc, int image, int datep1, int datep2, int datep3) {
        this.title = title;
        this.dateperson = dateperson;
        this.shortdesc = shortdesc;
        this.image = image;
        this.datep1 = datep1;
        this.datep2 = datep2;
        this.datep3 = datep3;
    }

    public String getDateperson() {
        return dateperson;
    }

    public int getDatep1() {
        return datep1;
    }

    public int getDatep2() {
        return datep2;
    }

    public int getDatep3() {
        return datep3;
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
