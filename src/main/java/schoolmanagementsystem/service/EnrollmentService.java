package schoolmanagementsystem.service;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import schoolmanagementsystem.dto.ResponseDTO;
import schoolmanagementsystem.entity.Enrollment;
import schoolmanagementsystem.exception.BadRequestServiceAlertException;
import schoolmanagementsystem.repository.EnrollmentRepository;
import schoolmanagementsystem.util.Constant;

import java.util.List;

@Service
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public ResponseDTO create(final Enrollment enrollment) {
        final Enrollment savedEnrollment = this.enrollmentRepository.save(enrollment);
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.CREATED);
        responseDTO.setStatusCode(HttpStatus.CREATED.value());
        responseDTO.setData(savedEnrollment);
        return responseDTO;
    }

    public ResponseDTO retrieveAll() {
        final List<Enrollment> enrollment = this.enrollmentRepository.findAll();
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.RETRIEVED);
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(enrollment);
        return responseDTO;
    }

    public ResponseDTO retrieveById(final Long id) {
        final Enrollment enrollment = this.enrollmentRepository.findById(id)
                .orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.RETRIEVED);
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(enrollment);
        return responseDTO;
    }

    @Transactional
    public ResponseDTO updateById(final Long id, final Enrollment enrollment) {
        final Enrollment existingEnrollment = this.enrollmentRepository.findById(id)
                .orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));

        if (enrollment.isFeesPaid() != null) {
            existingEnrollment.setFeesPaid(enrollment.isFeesPaid());
        }
        if (enrollment.getEnrollmentStatus() != null) {
            existingEnrollment.setEnrollmentStatus(enrollment.getEnrollmentStatus());
        }

        if (enrollment.getStudent() != null) {
            existingEnrollment.setStudent(enrollment.getStudent());
        }

        if (enrollment.getCourse() != null) {
            existingEnrollment.setCourse(enrollment.getCourse());
        }

        this.enrollmentRepository.save(existingEnrollment);
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.UPDATED);
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(existingEnrollment);
        return responseDTO;
    }

    public ResponseDTO remove(final Long id) {
        final boolean exist = this.enrollmentRepository.existsById(id);
        if (exist) {
            this.enrollmentRepository.deleteById(id);
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