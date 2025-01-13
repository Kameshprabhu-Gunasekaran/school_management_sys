package schoolmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import schoolmanagementsystem.entity.Tutor;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long> {
}
