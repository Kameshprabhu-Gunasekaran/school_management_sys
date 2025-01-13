package schoolmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import schoolmanagementsystem.entity.School;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {
}
