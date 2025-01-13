package schoolmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import schoolmanagementsystem.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
