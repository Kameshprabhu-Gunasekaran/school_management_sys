package school_management_sys.controller;

import school_management_sys.dto.ResponseDTO;
import school_management_sys.entity.School;
import school_management_sys.service.SchoolService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/schools")
public class SchoolController {

    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping("/create")
    public ResponseDTO createSchool(@RequestBody final School school) {
        return this.schoolService.createSchool(school);
    }

    @GetMapping("/retrieve-all")
    public ResponseDTO retrieveAllSchools() {
        return this.schoolService.retrieveAllSchools();
    }

    @GetMapping("/retrieve-id/{id}")
    public ResponseDTO retrieveSchoolById(@PathVariable("id") final Long id) {
        return this.schoolService.retrieveSchoolById(id);
    }

    @PutMapping("/update-id/{id}")
    public ResponseDTO updateSchoolById(@PathVariable("id") final Long id, @RequestBody School school) {
        return this.schoolService.updateSchoolById(id, school);
    }

    @DeleteMapping("/delete-id/{id}")
    public ResponseDTO deleteSchoolById(@PathVariable("id") final Long id) {
        return this.schoolService.deleteSchoolById(id);
    }
}
