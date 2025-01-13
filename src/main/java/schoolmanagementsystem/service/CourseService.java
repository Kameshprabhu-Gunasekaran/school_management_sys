package schoolmanagementsystem.service;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import schoolmanagementsystem.dto.ResponseDTO;
import schoolmanagementsystem.entity.Course;
import schoolmanagementsystem.exception.BadRequestServiceAlertException;
import schoolmanagementsystem.repository.CourseRepository;
import schoolmanagementsystem.util.Constant;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Transactional
    public ResponseDTO create(final Course course) {
        final Course savedCourse = this.courseRepository.save(course);
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.CREATED);
        responseDTO.setStatusCode(HttpStatus.CREATED.value());
        responseDTO.setData(savedCourse);
        return responseDTO;
    }

    public ResponseDTO retrieveAll() {
        final List<Course> courses = this.courseRepository.findAll();
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.RETRIEVED);
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(courses);
        return responseDTO;
    }

    public ResponseDTO retrieveById(final Long id) {
        final Course course = this.courseRepository.findById(id)
                .orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.RETRIEVED);
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(course);
        return responseDTO;

    }

    @Transactional
    public ResponseDTO updateById(final Long id, final Course course) {
        final Course existingCourse = this.courseRepository.findById(id)
                .orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));

        if (course.getName() != null) {
            existingCourse.setName(course.getName());
        }

        if (course.getFees() != null) {
            existingCourse.setFees(course.getFees());
        }

        if (course.getSchool() != null) {
            existingCourse.setSchool(course.getSchool());
        }

        this.courseRepository.save(existingCourse);
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.UPDATED);
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(existingCourse);
        return responseDTO;
    }

    public ResponseDTO remove(final Long id) {
        final boolean exist = this.courseRepository.existsById(id);
        if (exist) {
            this.courseRepository.deleteById(id);
            final ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage(Constant.DELETED);
            responseDTO.setStatusCode(HttpStatus.OK.value());
            responseDTO.setData(null);
            return responseDTO;
        } else {
            throw new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST);
        }
    }
}
