package school_management_sys.service;

import school_management_sys.dto.ResponseDTO;
import school_management_sys.entity.Student;
import school_management_sys.exception.BadRequestServiceAlertException;
import school_management_sys.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import school_management_sys.util.Constant;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService (final StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public ResponseDTO createStudent(Student student) {
        Student savedStudent = studentRepository.save(student);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Student created successfully");
        responseDTO.setStatusCode(HttpStatus.CREATED.value());
        responseDTO.setData(savedStudent);
        return responseDTO;
    }

    public ResponseDTO retrieveAllStudents() {
        List<Student> students = studentRepository.findAll();
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Students retrieved successfully");
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(students);
        return responseDTO;
    }

    public ResponseDTO retrieveStudentById(Long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        ResponseDTO responseDTO = new ResponseDTO();

        if (studentOptional.isPresent()) {
            responseDTO.setMessage("Student retrieved successfully");
            responseDTO.setStatusCode(HttpStatus.OK.value());
            responseDTO.setData(studentOptional.get());
            return responseDTO;
        }
        else {
            throw new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST);
        }
    }

    @Transactional
    public ResponseDTO updateStudentById(Long id, Student student) {
        Optional<Student> existingStudentOptional = studentRepository.findById(id);
        ResponseDTO responseDTO = new ResponseDTO();

        if (existingStudentOptional.isPresent()) {
            Student existingStudent = existingStudentOptional.get();

            if (student.getName() != null) {
                existingStudent.setName(student.getName());
            }

            if (student.getDob() != null) {
                existingStudent.setDob(student.getDob());
            }

            if (student.getContactNumber() != null) {
                existingStudent.setContactNumber(student.getContactNumber());
            }

            studentRepository.save(existingStudent);
            responseDTO.setMessage("Student updated successfully");
            responseDTO.setStatusCode(HttpStatus.OK.value());
            responseDTO.setData(existingStudent);
            return responseDTO;
        }
        else {
            throw new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST);
        }
    }
    public ResponseDTO deleteStudentById(Long id) {
        ResponseDTO responseDTO = new ResponseDTO();

        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            responseDTO.setMessage("Student deleted successfully");
            responseDTO.setStatusCode(HttpStatus.OK.value());
            responseDTO.setData(null);
            return responseDTO;
        }
        else {
            throw new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST);
        }

    }
}
