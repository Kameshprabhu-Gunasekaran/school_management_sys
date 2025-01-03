package school_management_sys.service;

import school_management_sys.dto.ResponseDTO;
import school_management_sys.entity.School;
import school_management_sys.exception.BadRequestServiceAlertException;
import school_management_sys.repository.SchoolRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import school_management_sys.util.Constant;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolService {
    private final SchoolRepository schoolRepository;

    public SchoolService (final SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @Transactional
    public ResponseDTO createSchool(final School school) {
        School savedSchool = this.schoolRepository.save(school);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("School created successfully");
        responseDTO.setStatusCode(HttpStatus.CREATED.value());
        responseDTO.setData(savedSchool);
        return responseDTO;
    }
    public ResponseDTO retrieveAllSchools() {
        List<School> schools = this.schoolRepository.findAll();
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Schools retrieved successfully");
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(schools);
        return responseDTO;
    }
    public ResponseDTO retrieveSchoolById(Long id) {
        Optional<School> school = schoolRepository.findById(id);
        ResponseDTO responseDTO = new ResponseDTO();

        if (school.isPresent()) {
            responseDTO.setMessage("School retrieved successfully");
            responseDTO.setStatusCode(HttpStatus.OK.value());
            responseDTO.setData(school.get());
            return responseDTO;
        }
        else {
            throw new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST);
        }
    }

    @Transactional
    public ResponseDTO updateSchoolById(Long id, School school) {
        Optional<School> existingSchoolOptional = schoolRepository.findById(id);
        ResponseDTO responseDTO = new ResponseDTO();

        if (existingSchoolOptional.isPresent()) {
            School existingSchool = existingSchoolOptional.get();

            if (school.getName() != null) {
                existingSchool.setName(school.getName());
            }

            if (school.getAddress() != null) {
                existingSchool.setAddress(school.getAddress());
            }

            if (school.getContactNumber() != 0) {
                existingSchool.setContactNumber(school.getContactNumber());
            }

            schoolRepository.save(existingSchool);
            responseDTO.setMessage("School updated successfully");
            responseDTO.setStatusCode(HttpStatus.OK.value());
            responseDTO.setData(existingSchool);
            return responseDTO;
        }else {
            throw new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST);
        }
    }

    public ResponseDTO deleteSchoolById(Long id) {
        ResponseDTO responseDTO = new ResponseDTO();

        if (this.schoolRepository.existsById(id)) {
            this.schoolRepository.deleteById(id);
            responseDTO.setMessage("School deleted successfully");
            responseDTO.setStatusCode(HttpStatus.OK.value());
            responseDTO.setData(null);
            return responseDTO;
        }
        else {
            throw new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST);
        }
    }
}
