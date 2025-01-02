package school_management_sys.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "total_salary")
public class Total_Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long month;
    @Column
    private String salary_paid;

    @ManyToOne
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMonth() {
        return month;
    }

    public void setMonth(Long month) {
        this.month = month;
    }

    public String getSalary_paid() {
        return salary_paid;
    }

    public void setSalary_paid(String salary_paid) {
        this.salary_paid = salary_paid;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }
}
