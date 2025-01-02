package school_management_sys.repository;

import school_management_sys.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository <Course, Long> {
}
