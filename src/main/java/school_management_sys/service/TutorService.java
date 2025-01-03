package school_management_sys.service;

import school_management_sys.dto.ResponseDTO;
import school_management_sys.entity.Tutor;
import school_management_sys.exception.BadRequestServiceAlertException;
import school_management_sys.repository.TutorRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import school_management_sys.util.Constant;

import java.util.List;
import java.util.Optional;

@Service
public class TutorService {
    private final TutorRepository tutorRepository;

    public TutorService (final TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    @Transactional
    public ResponseDTO createTutor(Tutor tutor) {
        Tutor savedTutor = tutorRepository.save(tutor);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Tutor created successfully");
        responseDTO.setStatusCode(HttpStatus.CREATED.value());
        responseDTO.setData(savedTutor);
        return responseDTO;
    }

    public ResponseDTO retrieveAllTutors() {
        List<Tutor> tutors = tutorRepository.findAll();
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Tutors retrieved successfully");
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(tutors);
        return responseDTO;
    }

    public ResponseDTO retrieveTutorById(Long id) {
        Optional<Tutor> tutorOptional = tutorRepository.findById(id);
        ResponseDTO responseDTO = new ResponseDTO();

        if (tutorOptional.isPresent()) {
            responseDTO.setMessage("Tutor retrieved successfully");
            responseDTO.setStatusCode(HttpStatus.OK.value());
            responseDTO.setData(tutorOptional.get());
            return responseDTO;
        } else {
            throw new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST);
        }
    }

    @Transactional
    public ResponseDTO updateTutorById(Long id, Tutor tutor) {
        Optional<Tutor> existingTutorOptional = tutorRepository.findById(id);
        ResponseDTO responseDTO = new ResponseDTO();

        if (existingTutorOptional.isPresent()) {
            Tutor existingTutor = existingTutorOptional.get();

            if (tutor.getName() != null) {
                existingTutor.setName(tutor.getName());
            }

            if (tutor.getSubject() != null) {
                existingTutor.setSubject(tutor.getSubject());
            }

            if (tutor.getSalary() != 0) {
                existingTutor.setSalary(tutor.getSalary());
            }

            tutorRepository.save(existingTutor);
            responseDTO.setMessage("Tutor updated successfully");
            responseDTO.setStatusCode(HttpStatus.OK.value());
            responseDTO.setData(existingTutor);
            return responseDTO;
        } else {
              throw new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST);
        }
    }

    public ResponseDTO deleteTutorById(Long id) {
        ResponseDTO responseDTO = new ResponseDTO();

        if (tutorRepository.existsById(id)) {
            tutorRepository.deleteById(id);
            responseDTO.setMessage("Tutor deleted successfully");
            responseDTO.setStatusCode(HttpStatus.OK.value());
            responseDTO.setData(null);
            return responseDTO;
        }
        else {
            throw new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST);
        }

    }
}
