package school_management_sys.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "enrollment")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String fees_paid;
    @Column
    private String enrollment_status;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFees_paid() {
        return fees_paid;
    }

    public void setFees_paid(String fees_paid) {
        this.fees_paid = fees_paid;
    }

    public String getEnrollment_status() {
        return enrollment_status;
    }

    public void setEnrollment_status(String enrollment_status) {
        this.enrollment_status = enrollment_status;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
