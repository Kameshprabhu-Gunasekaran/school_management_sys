package school_management_sys.service;

import school_management_sys.dto.ResponseDTO;
import school_management_sys.entity.Course;
import school_management_sys.exception.BadRequestServiceAlertException;
import school_management_sys.repository.CourseRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import school_management_sys.util.Constant;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService (final CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Transactional
    public ResponseDTO createCourse(Course course) {
        Course savedCourse = courseRepository.save(course);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Course created successfully");
        responseDTO.setStatusCode(HttpStatus.CREATED.value());
        responseDTO.setData(savedCourse);
        return responseDTO;
    }

    public ResponseDTO retrieveAllCourses() {
        List<Course> courses = courseRepository.findAll();
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Courses retrieved successfully");
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(courses);
        return responseDTO;
    }

    public ResponseDTO retrieveCourseById(Long id) {
        Optional<Course> courseOptional = courseRepository.findById(id);
        ResponseDTO responseDTO = new ResponseDTO();

        if (courseOptional.isPresent()) {
            responseDTO.setMessage("Course retrieved successfully");
            responseDTO.setStatusCode(HttpStatus.OK.value());
            responseDTO.setData(courseOptional.get());
            return responseDTO;
        }
        else {
            throw new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST);
        }
    }

    @Transactional
    public ResponseDTO updateCourseById(Long id, Course course) {
        Optional<Course> existingCourseOptional = courseRepository.findById(id);
        ResponseDTO responseDTO = new ResponseDTO();

        if (existingCourseOptional.isPresent()) {
            Course existingCourse = existingCourseOptional.get();

            if (course.getName() != null) {
                existingCourse.setName(course.getName());
            }

            if (course.getFees() != null) {
                existingCourse.setFees(course.getFees());
            }

            courseRepository.save(existingCourse);
            responseDTO.setMessage("Course updated successfully");
            responseDTO.setStatusCode(HttpStatus.OK.value());
            responseDTO.setData(existingCourse);
            return responseDTO;
        }
        else {
            throw new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST);
        }
    }

    public ResponseDTO deleteCourseById(Long id) {
        ResponseDTO responseDTO = new ResponseDTO();

        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            responseDTO.setMessage("Course deleted successfully");
            responseDTO.setStatusCode(HttpStatus.OK.value());
            responseDTO.setData(null);
            return responseDTO;
        }
        else {
            throw new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST);
        }
    }
}
