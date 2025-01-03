package school_management_sys.service;

import school_management_sys.dto.ResponseDTO;
import school_management_sys.entity.Enrollment;
import school_management_sys.exception.BadRequestServiceAlertException;
import school_management_sys.repository.EnrollmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import school_management_sys.util.Constant;

import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentService(final EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public ResponseDTO createEnrollment(final Enrollment enrollment) {
        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Enrollment created successfully");
        responseDTO.setStatusCode(HttpStatus.CREATED.value());
        responseDTO.setData(savedEnrollment);
        return responseDTO;
    }

    public ResponseDTO retrieveAllEnrollments() {
        List<Enrollment> enrollments = enrollmentRepository.findAll();
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Enrollments retrieved successfully");
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(enrollments);
        return responseDTO;
    }

    public ResponseDTO retrieveById(Long id) {
        Optional<Enrollment> enrollmentOptional = enrollmentRepository.findById(id);
        ResponseDTO responseDTO = new ResponseDTO();

        if (enrollmentOptional.isPresent()) {
            responseDTO.setMessage("Enrollment retrieved successfully");
            responseDTO.setStatusCode(HttpStatus.OK.value());
            responseDTO.setData(enrollmentOptional.get());
            return responseDTO;
        }
        else {
            throw new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST);
        }
    }

    @Transactional
    public ResponseDTO updateEnrollmentById(Long id, Enrollment enrollment) {
        Optional<Enrollment> existingEnrollmentOptional = enrollmentRepository.findById(id);
        ResponseDTO responseDTO = new ResponseDTO();

        if (existingEnrollmentOptional.isPresent()) {
            Enrollment existingEnrollment = existingEnrollmentOptional.get();

            if (enrollment.getFees_paid() != null) {
                existingEnrollment.setFees_paid(enrollment.getFees_paid());
            }
            if (enrollment.getEnrollment_status() != null) {
                existingEnrollment.setEnrollment_status(enrollment.getEnrollment_status());
            }

            enrollmentRepository.save(existingEnrollment);
            responseDTO.setMessage("Enrollment updated successfully");
            responseDTO.setStatusCode(HttpStatus.OK.value());
            responseDTO.setData(existingEnrollment);
            return responseDTO;
        }
        else {
            throw new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST);
        }
    }

    public ResponseDTO deleteEnrollmentById(Long id) {
        ResponseDTO responseDTO = new ResponseDTO();

        if (enrollmentRepository.existsById(id)) {
            enrollmentRepository.deleteById(id);
            responseDTO.setMessage("Enrollment deleted successfully");
            responseDTO.setStatusCode(HttpStatus.OK.value());
            responseDTO.setData(null);
            return responseDTO;
        } else {
            throw new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST);
        }
    }
}