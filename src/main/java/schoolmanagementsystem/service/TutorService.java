package schoolmanagementsystem.service;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import schoolmanagementsystem.dto.ResponseDTO;
import schoolmanagementsystem.entity.Tutor;
import schoolmanagementsystem.exception.BadRequestServiceAlertException;
import schoolmanagementsystem.repository.TutorRepository;
import schoolmanagementsystem.util.Constant;

import java.util.List;

@Service
public class TutorService {
    private final TutorRepository tutorRepository;

    public TutorService(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    @Transactional
    public ResponseDTO create(final Tutor tutor) {
        final Tutor savedTutor = this.tutorRepository.save(tutor);
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.CREATED);
        responseDTO.setStatusCode(HttpStatus.CREATED.value());
        responseDTO.setData(savedTutor);
        return responseDTO;
    }

    public ResponseDTO retrieveAll() {
        final List<Tutor> tutors = this.tutorRepository.findAll();
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.RETRIEVED);
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(tutors);
        return responseDTO;
    }

    public ResponseDTO retrieveById(final Long id) {
        final Tutor tutor = this.tutorRepository.findById(id)
                .orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.RETRIEVED);
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(tutor);
        return responseDTO;
    }

    @Transactional
    public ResponseDTO updateById(final Long id, final Tutor tutor) {
        final Tutor existingTutor = this.tutorRepository.findById(id)
                .orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));

            if (tutor.getName() != null) {
                existingTutor.setName(tutor.getName());
            }

            if (tutor.getSubject() != null) {
                existingTutor.setSubject(tutor.getSubject());
            }

            if (tutor.getSalary() != 0) {
                existingTutor.setSalary(tutor.getSalary());
            }

            if (tutor.getSchool() != null) {
                existingTutor.setSchool(tutor.getSchool());
            }

            this.tutorRepository.save(existingTutor);
            final ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage(Constant.UPDATED);
            responseDTO.setStatusCode(HttpStatus.OK.value());
            responseDTO.setData(existingTutor);
            return responseDTO;

    }

    public ResponseDTO remove(final Long id) {
        final ResponseDTO responseDTO = new ResponseDTO();
        final boolean exist = this.tutorRepository.existsById(id);
        if (exist) {
            this.tutorRepository.deleteById(id);
            responseDTO.setMessage(Constant.DELETED);
            responseDTO.setStatusCode(HttpStatus.OK.value());
            responseDTO.setData(null);
            return responseDTO;
        } else {
            throw new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST);
        }
    }
}
