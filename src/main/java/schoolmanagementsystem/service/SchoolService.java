package schoolmanagementsystem.service;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import schoolmanagementsystem.dto.ResponseDTO;
import schoolmanagementsystem.entity.School;
import schoolmanagementsystem.exception.BadRequestServiceAlertException;
import schoolmanagementsystem.repository.SchoolRepository;
import schoolmanagementsystem.util.Constant;

import java.util.List;

@Service
public class SchoolService {
    private final SchoolRepository schoolRepository;

    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @Transactional
    public ResponseDTO createSchool(final School school) {
        final School savedSchool = this.schoolRepository.save(school);
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.CREATED);
        responseDTO.setStatusCode(HttpStatus.CREATED.value());
        responseDTO.setData(savedSchool);
        return responseDTO;
    }

    public ResponseDTO retrieveAll() {
        final List<School> schools = this.schoolRepository.findAll();
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.RETRIEVED);
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(schools);
        return responseDTO;
    }

    public ResponseDTO retrieveById(final Long id) {
        final School school = this.schoolRepository.findById(id)
                .orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.RETRIEVED);
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(school);
        return responseDTO;
    }

    @Transactional
    public ResponseDTO updateById(final Long id, final School school) {
        final School existingSchool = this.schoolRepository.findById(id)
                .orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));

        if (school.getName() != null) {
            existingSchool.setName(school.getName());
        }

        if (school.getAddress() != null) {
            existingSchool.setAddress(school.getAddress());
        }

        if (school.getContactNumber() != null) {
            existingSchool.setContactNumber(school.getContactNumber());
        }

        this.schoolRepository.save(existingSchool);
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.UPDATED);
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(existingSchool);
        return responseDTO;
    }


    @Transactional
    public ResponseDTO remove(final Long id) {
        final ResponseDTO responseDTO = new ResponseDTO();
        final boolean exist = this.schoolRepository.existsById(id);
        if (exist) {
            this.schoolRepository.deleteById(id);
            responseDTO.setMessage(Constant.DELETED);
            responseDTO.setStatusCode(HttpStatus.OK.value());
            responseDTO.setData(null);
            return responseDTO;
        } else {
            throw new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST);
        }
    }
}
