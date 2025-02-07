package schoolmanagementsystem.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import schoolmanagementsystem.dto.PaginatedResponseDTO;
import schoolmanagementsystem.dto.ResponseDTO;
import schoolmanagementsystem.dto.StudentDTO;
import schoolmanagementsystem.entity.Student;
import schoolmanagementsystem.service.StudentService;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(final StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public ResponseDTO create(@RequestBody final StudentDTO studentDTO) {
        return this.studentService.create(studentDTO);
    }

    @GetMapping("/retrieve")
    public PaginatedResponseDTO<Student> retrieveAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long dob) {
        return this.studentService.retrieveAll(page, size, sortBy, sortDir, name, dob);
    }

    @GetMapping("/retrieve/{id}")
    public ResponseDTO retrieveById(@PathVariable("id") final Long id) {
        return this.studentService.retrieveById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseDTO updateById(@PathVariable("id") final Long id, @RequestBody final Student student) {
        return this.studentService.updateById(id, student);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseDTO deleteById(@PathVariable("id") final Long id) {
        return this.studentService.remove(id);
    }

    @GetMapping("/retrieve/course/{courseId}/school/{schoolId}")
    public ResponseDTO getStudentsByCourseAndSchool(@PathVariable("courseId") Long courseId,
                                                    @PathVariable("schoolId") Long schoolId) {
        return this.studentService.getStudentsByCourseAndSchool(courseId, schoolId);
    }

    @GetMapping("/retrieve/students/{tutorId}")
    public ResponseDTO getStudentsByTutorId(@PathVariable("tutorId") Long tutorId) {
        return this.studentService.getStudentsByTutorId(tutorId);
    }
}
