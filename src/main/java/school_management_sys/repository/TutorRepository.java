package school_management_sys.repository;

import school_management_sys.entity.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorRepository extends JpaRepository <Tutor, Long> {
}
