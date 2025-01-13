package schoolmanagementsystem.service;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import schoolmanagementsystem.dto.ResponseDTO;
import schoolmanagementsystem.entity.Student;
import schoolmanagementsystem.exception.BadRequestServiceAlertException;
import schoolmanagementsystem.repository.StudentRepository;
import schoolmanagementsystem.util.Constant;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public ResponseDTO create(final Student student) {
        final Student savedStudent = this.studentRepository.save(student);
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.CREATED);
        responseDTO.setStatusCode(HttpStatus.CREATED.value());
        responseDTO.setData(savedStudent);
        return responseDTO;
    }

    public ResponseDTO retrieveAll() {
        final List<Student> students = this.studentRepository.findAll();
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.RETRIEVED);
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(students);
        return responseDTO;
    }

    public ResponseDTO retrieveById(final Long id) {
        final Student student = this.studentRepository.findById(id)
                .orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.RETRIEVED);
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(student);
        return responseDTO;
    }


    @Transactional
    public ResponseDTO updateById(final Long id, final Student student) {
        final Student existingStudent = this.studentRepository.findById(id)
                .orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));

        if (student.getName() != null) {
            existingStudent.setName(student.getName());
        }

        if (student.getDob() != null) {
            existingStudent.setDob(student.getDob());
        }

        if (student.getContactNumber() != null) {
            existingStudent.setContactNumber(student.getContactNumber());
        }

        if (student.getSchool() != null) {
            existingStudent.setSchool(student.getSchool());
        }

        this.studentRepository.save(existingStudent);
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.UPDATED);
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(existingStudent);
        return responseDTO;
    }


    public ResponseDTO remove(final Long id) {
        final ResponseDTO responseDTO = new ResponseDTO();
        final boolean exist = this.studentRepository.existsById(id);
        if (exist) {
            this.studentRepository.deleteById(id);
            responseDTO.setMessage(Constant.DELETED);
            responseDTO.setStatusCode(HttpStatus.OK.value());
            responseDTO.setData(null);
            return responseDTO;
        } else {
            throw new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST);
        }
    }
}
