package schoolmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import schoolmanagementsystem.dto.ResponseDTO;
import schoolmanagementsystem.entity.Enrollment;
import schoolmanagementsystem.service.EnrollmentService;

@RestController
@RequestMapping("/api/v1/enrollment")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;


    @PostMapping("/create")
    public ResponseDTO create(@RequestBody final Enrollment enrollment) {
        return this.enrollmentService.create(enrollment);
    }

    @GetMapping("/retrieve")
    public ResponseDTO retrieveAll() {
        return this.enrollmentService.retrieveAll();
    }

    @GetMapping("/retrieve/{id}")
    public ResponseDTO retrieveById(@PathVariable("id") final Long id) {
        return this.enrollmentService.retrieveById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseDTO updateById(@PathVariable("id") final Long id, @RequestBody final Enrollment enrollment) {
        return this.enrollmentService.updateById(id, enrollment);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDTO deleteById(@PathVariable("id") final Long id) {
        return this.enrollmentService.remove(id);
    }
}
