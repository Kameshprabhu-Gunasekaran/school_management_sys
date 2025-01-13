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
import schoolmanagementsystem.entity.Course;
import schoolmanagementsystem.service.CourseService;

@RestController
@RequestMapping("/api/v1/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/create")
    public ResponseDTO create(@RequestBody final Course course) {
        return this.courseService.create(course);
    }

    @GetMapping("/retrieve")
    public ResponseDTO retrieveAll() {
        return this.courseService.retrieveAll();
    }

    @GetMapping("/retrieve/{id}")
    public ResponseDTO retrieveById(@PathVariable("id") final Long id) {
        return this.courseService.retrieveById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseDTO updateById(@PathVariable("id") final Long id, @RequestBody final Course course) {
        return this.courseService.updateById(id, course);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDTO deleteById(@PathVariable("id") final Long id) {
        return this.courseService.remove(id);
    }
}
