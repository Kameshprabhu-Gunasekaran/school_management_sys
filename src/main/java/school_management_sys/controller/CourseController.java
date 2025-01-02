package school_management_sys.controller;

import school_management_sys.dto.ResponseDTO;
import school_management_sys.entity.Course;
import school_management_sys.service.CourseService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
//udbiufdf
    @PostMapping("/create")
    public ResponseDTO createCourse(@RequestBody Course course) {
        return this.courseService.createCourse(course);
    }

    @GetMapping("/retrieve-all")
    public ResponseDTO retrieveAllCourses() {
        return this.courseService.retrieveAllCourses();
    }

    @GetMapping("/retrieve-id/{id}")
    public ResponseDTO retrieveCourseById(@PathVariable("id") final Long id) {
        return this.courseService.retrieveCourseById(id);
    }

    @PutMapping("/update-id/{id}")
    public ResponseDTO updateCourseById(@PathVariable("id") final Long id, @RequestBody Course course) {
        return this.courseService.updateCourseById(id, course);
    }

    @DeleteMapping("/delete-id/{id}")
    public ResponseDTO deleteCourseById(@PathVariable("id") final Long id) {
        return this.courseService.deleteCourseById(id);
    }
}
