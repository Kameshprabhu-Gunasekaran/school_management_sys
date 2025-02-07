package schoolmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import schoolmanagementsystem.entity.Salary;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long> {

//    @Query("SELECT s FROM Salary s WHERE" +
//           "(:salaryPaid IS NULL or LOWER (s.salaryPaid) LIKE LOWER  ('%', :salary, '%'))) AND " +
//            "(:id IS NULL OR s.id = :id)")
//    Page<Enrollment> searchSalary(@Param("salaryPaid") String enrollmentStatus,
//                                      @Param("id") Long id,
//                                      Pageable pageable);
}
