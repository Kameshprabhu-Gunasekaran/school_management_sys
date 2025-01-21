package schoolmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import schoolmanagementsystem.dto.StudentCourseDTO;
import schoolmanagementsystem.entity.Student;

import java.util.List;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT st.name, st.dob, st.contactNumber, c.name, sc.name " + "FROM Student st " + "JOIN Enrollment e ON st.id = e.student.id " + "JOIN Course c ON e.course.id = c.id " + "JOIN School sc ON st.school.id = sc.id " + "WHERE c.id = :courseId AND st.school.id = :schoolId")
    Object findStudentsByCourseAndSchool(Long courseId, Long schoolId);
}
