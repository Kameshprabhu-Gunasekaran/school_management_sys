package school_management_sys.service;

import school_management_sys.dto.ResponseDTO;
import school_management_sys.entity.Total_Salary;
import school_management_sys.exception.BadRequestServiceAlertException;
import school_management_sys.repository.Total_SalaryRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import school_management_sys.util.Constant;

import java.util.List;
import java.util.Optional;

@Service
public class Total_SalaryService {
    private final Total_SalaryRepository total_salaryRepository;

    public Total_SalaryService (final Total_SalaryRepository total_salaryRepository) {
        this.total_salaryRepository = total_salaryRepository;
    }

    public ResponseDTO createTotalSalary(final Total_Salary total_salary) {
        Total_Salary savedTotalSalary = total_salaryRepository.save(total_salary);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Total Salary created successfully");
        responseDTO.setStatusCode(HttpStatus.CREATED.value());
        responseDTO.setData(savedTotalSalary);
        return responseDTO;
    }

    public ResponseDTO retrieveAllSalary() {
        List<Total_Salary> totalSalaries = total_salaryRepository.findAll();
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Total Salaries retrieved successfully");
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(totalSalaries);
        return responseDTO;
    }

    public ResponseDTO retrieveSalaryById(Long id) {
        Optional<Total_Salary> totalSalaryOptional = total_salaryRepository.findById(id);
        ResponseDTO responseDTO = new ResponseDTO();

        if (totalSalaryOptional.isPresent()) {
            responseDTO.setMessage("Total Salary retrieved successfully");
            responseDTO.setStatusCode(HttpStatus.OK.value());
            responseDTO.setData(totalSalaryOptional.get());
            return responseDTO;
        } else {
            throw new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST);
        }
    }

    @Transactional
    public ResponseDTO updateTotalSalaryById(Long id, Total_Salary total_salary) {
        Optional<Total_Salary> existingTotalSalaryOptional = total_salaryRepository.findById(id);
        ResponseDTO responseDTO = new ResponseDTO();

        if (existingTotalSalaryOptional.isPresent()) {
            Total_Salary existingTotalSalary = existingTotalSalaryOptional.get();

            if (total_salary.getMonth() != 0) {
                existingTotalSalary.setMonth(total_salary.getMonth());
            }
            if (total_salary.getSalary_paid() != null) {
                existingTotalSalary.setSalary_paid(total_salary.getSalary_paid());
            }

            total_salaryRepository.save(existingTotalSalary);
            responseDTO.setMessage("Total Salary updated successfully");
            responseDTO.setStatusCode(HttpStatus.OK.value());
            responseDTO.setData(existingTotalSalary);
            return responseDTO;
        }
        else {
            throw new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST);
        }
    }

    public ResponseDTO deleteTotalSalaryById(Long id) {
        ResponseDTO responseDTO = new ResponseDTO();

        if (total_salaryRepository.existsById(id)) {
            total_salaryRepository.deleteById(id);
            responseDTO.setMessage("Total Salary deleted successfully");
            responseDTO.setStatusCode(HttpStatus.OK.value());
            responseDTO.setData(null);
            return responseDTO;
        }
        else {
            throw new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST);
        }
    }
}
