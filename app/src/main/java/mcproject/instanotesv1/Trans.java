package mcproject.instanotesv1;


public class Trans {
    private String title;
    private String shortdesc;
    private int image;

    public Trans(String title, String shortdesc, int image) {
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
