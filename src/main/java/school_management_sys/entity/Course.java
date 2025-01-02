package school_management_sys.entity;

import jakarta.persistence.*;

@Entity
@Table (name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private Long fees;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getFees() {
        return fees;
    }

    public void setFees(Long fees) {
        this.fees = fees;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}
