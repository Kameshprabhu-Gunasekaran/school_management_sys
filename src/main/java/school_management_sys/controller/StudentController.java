package school_management_sys.controller;

import school_management_sys.dto.ResponseDTO;
import school_management_sys.entity.Student;
import school_management_sys.service.StudentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public ResponseDTO createStudent(@RequestBody final Student student) {
        return this.studentService.createStudent(student);
    }

    @GetMapping("/retrieve-all")
    public ResponseDTO retrieveAllStudents() {
        return this.studentService.retrieveAllStudents();
    }

    @GetMapping("/retrieve-id/{id}")
    public ResponseDTO retrieveStudentById(@PathVariable("id") final Long id) {
        return this.studentService.retrieveStudentById(id);
    }

    @PutMapping("/update-id/{id}")
    public ResponseDTO updateStudentById(@PathVariable("id") final Long id, @RequestBody Student student) {
        return this.studentService.updateStudentById(id, student);
    }

    @DeleteMapping("/delete-id/{id}")
    public ResponseDTO deleteStudentById(@PathVariable("id") final Long id) {
        return this.studentService.deleteStudentById(id);
    }
}
