package school_management_sys.controller;

import school_management_sys.dto.ResponseDTO;
import school_management_sys.entity.Total_Salary;
import school_management_sys.service.Total_SalaryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/total-salary")
public class Total_SalaryController {

    private final Total_SalaryService totalSalaryService;

    public Total_SalaryController(Total_SalaryService totalSalaryService) {
        this.totalSalaryService = totalSalaryService;
    }

    @PostMapping("/create")
    public ResponseDTO createTotalSalary(@RequestBody final Total_Salary totalSalary) {
        return this.totalSalaryService.createTotalSalary(totalSalary);
    }

    @GetMapping("/retrieve-all")
    public ResponseDTO retrieveAllSalaries() {
        return this.totalSalaryService.retrieveAllSalary();
    }

    @GetMapping("/retrieve-id/{id}")
    public ResponseDTO retrieveSalaryById(@PathVariable("id") final Long id) {
        return this.totalSalaryService.retrieveSalaryById(id);
    }

    @PutMapping("/update-id/{id}")
    public ResponseDTO updateTotalSalaryById(@PathVariable("id") final Long id, @RequestBody Total_Salary totalSalary) {
        return this.totalSalaryService.updateTotalSalaryById(id, totalSalary);
    }

    @DeleteMapping("/delete-id/{id}")
    public ResponseDTO deleteTotalSalaryById(@PathVariable("id") final Long id) {
        return this.totalSalaryService.deleteTotalSalaryById(id);
    }
}
