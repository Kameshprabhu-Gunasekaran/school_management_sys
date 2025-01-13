package schoolmanagementsystem.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import schoolmanagementsystem.dto.ResponseDTO;
import schoolmanagementsystem.entity.Salary;
import schoolmanagementsystem.service.SalaryService;

@RestController
@RequestMapping("/api/v1/total-salary")
public class SalaryController {

    private final SalaryService salaryService;

    public SalaryController(SalaryService salaryService) {
        this.salaryService = salaryService;
    }

    @PostMapping("/create")
    public ResponseDTO create(@RequestBody final Salary salary) {
        return this.salaryService.create(salary);
    }

    @GetMapping("/retrieve")
    public ResponseDTO retrieveAll() {
        return this.salaryService.retrieveAll();
    }

    @GetMapping("/retrieve/{id}")
    public ResponseDTO retrieveById(@PathVariable("id") final Long id) {
        return this.salaryService.retrieveById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseDTO updateById(@PathVariable("id") final Long id, @RequestBody final Salary salary) {
        return this.salaryService.updateById(id, salary);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDTO deleteById(@PathVariable("id") final Long id) {
        return this.salaryService.remove(id);
    }
}
