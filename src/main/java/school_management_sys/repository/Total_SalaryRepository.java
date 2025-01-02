package school_management_sys.repository;

import school_management_sys.entity.Total_Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Total_SalaryRepository extends JpaRepository <Total_Salary, Long> {
}
