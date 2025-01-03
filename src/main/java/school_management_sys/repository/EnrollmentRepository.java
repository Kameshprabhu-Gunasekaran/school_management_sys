package school_management_sys.repository;

import school_management_sys.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository <Enrollment, Long> {
}
