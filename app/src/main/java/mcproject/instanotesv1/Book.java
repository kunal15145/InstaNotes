package mcproject.instanotesv1;

/**
 * Created by Harshit Verma on 11-03-2018.
 */

public class Book {

    private String CourseName;
    private String Semester;
    private int Thumbnail;

    public Book(String courseName, String semester, int thumbnail) {
        CourseName = courseName;
        Semester = semester;
        Thumbnail = thumbnail;
    }

    public String getCourseName() {
        return CourseName;
    }

    public String getSemester() {
        return Semester;
    }

    public int getThumbnail() {
        return Thumbnail;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public void setSemester(String semester) {
        Semester = semester;
    }

    public void setThumbnail(int thumbnail) {
        Thumbnail = thumbnail;
    }
}
