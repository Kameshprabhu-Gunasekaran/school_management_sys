package school_management_sys.controller;

import school_management_sys.dto.ResponseDTO;
import school_management_sys.entity.Enrollment;
import school_management_sys.service.EnrollmentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping("/create")
    public ResponseDTO createEnrollment(@RequestBody final Enrollment enrollment) {
        return this.enrollmentService.createEnrollment(enrollment);
    }

    @GetMapping("/retrieve-all")
    public ResponseDTO retrieveAllEnrollments() {
        return this.enrollmentService.retrieveAllEnrollments();
    }

    @GetMapping("/retrieve-id/{id}")
    public ResponseDTO retrieveEnrollmentById(@PathVariable("id") final Long id) {
        return this.enrollmentService.retrieveById(id);
    }

    @PutMapping("/update-id/{id}")
    public ResponseDTO updateEnrollmentById(@PathVariable("id") final Long id, @RequestBody Enrollment enrollment) {
        return this.enrollmentService.updateEnrollmentById(id, enrollment);
    }

    @DeleteMapping("/delete-id/{id}")
    public ResponseDTO deleteEnrollmentById(@PathVariable("id") final Long id) {
        return this.enrollmentService.deleteEnrollmentById(id);
    }
}
