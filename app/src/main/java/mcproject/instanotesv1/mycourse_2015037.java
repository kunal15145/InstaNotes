package mcproject.instanotesv1;


public class mycourse_2015037 {

    private String CourseName;
    private String Semester;
    private int Thumbnail;

    //Default constructor
    public mycourse_2015037(String courseName, String semester, int thumbnail) {
        CourseName = courseName;
        Semester = semester;
        Thumbnail = thumbnail;
    }

    // Getter functions
    public String getCourseName() {
        return CourseName;
    }

    public String getSemester() {
        return Semester;
    }

    public int getThumbnail() {
        return Thumbnail;
    }
}
