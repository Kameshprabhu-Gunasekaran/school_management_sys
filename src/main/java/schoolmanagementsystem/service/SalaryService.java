package schoolmanagementsystem.service;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import schoolmanagementsystem.dto.ResponseDTO;
import schoolmanagementsystem.entity.Salary;
import schoolmanagementsystem.exception.BadRequestServiceAlertException;
import schoolmanagementsystem.repository.SalaryRepository;
import schoolmanagementsystem.util.Constant;

import java.util.List;

@Service
public class SalaryService {
    private final SalaryRepository salaryRepository;

    public SalaryService(SalaryRepository salaryRepository) {
        this.salaryRepository = salaryRepository;
    }

    @Transactional
    public ResponseDTO create(final Salary salary) {
        final Salary savedSalary = this.salaryRepository.save(salary);
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.CREATED);
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(savedSalary);
        return responseDTO;
    }

    public ResponseDTO retrieveAll() {
        final List<Salary> totalSalaries = this.salaryRepository.findAll();
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.RETRIEVED);
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(totalSalaries);
        return responseDTO;
    }

    public ResponseDTO retrieveById(final Long id) {
        final Salary salary = this.salaryRepository.findById(id)
                .orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.RETRIEVED);
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(salary);
        return responseDTO;
    }

    @Transactional
    public ResponseDTO updateById(final Long id, final Salary salary) {
        final Salary existingSalary = this.salaryRepository.findById(id)
                .orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));

        if (salary.getMonth() != 0) {
            existingSalary.setMonth(salary.getMonth());
        }
        if (salary.getSalaryPaid() != null) {
            existingSalary.setSalaryPaid(salary.getSalaryPaid());
        }
        if (salary.getTutor() != null) {
            existingSalary.setTutor(salary.getTutor());
        }

        this.salaryRepository.save(existingSalary);
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.UPDATED);
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(existingSalary);
        return responseDTO;
    }

    @Transactional
    public ResponseDTO remove(final Long id) {
        final ResponseDTO responseDTO = new ResponseDTO();
        final boolean exist = this.salaryRepository.existsById(id);
        if (exist) {
            this.salaryRepository.deleteById(id);
            responseDTO.setMessage(Constant.DELETED);
            responseDTO.setStatusCode(HttpStatus.OK.value());
            responseDTO.setData(null);
            return responseDTO;
        } else {
            throw new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST);
        }
    }
}
