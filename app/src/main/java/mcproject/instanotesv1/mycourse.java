package mcproject.instanotesv1;


public class mycourse {

    private String CourseName;
    private String Semester;
    private int Thumbnail;

    public mycourse(String courseName, String semester, int thumbnail) {
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
}
