package schoolmanagementsystem.dto;

public class StudentCourseDTO {

    private String studentName;
    private Long studentDob;
    private String studentContact;
    private String courseName;
    private String schoolName;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Long getStudentDob() {
        return studentDob;
    }

    public void setStudentDob(Long studentDob) {
        this.studentDob = studentDob;
    }

    public String getStudentContact() {
        return studentContact;
    }

    public void setStudentContact(String studentContact) {
        this.studentContact = studentContact;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}


