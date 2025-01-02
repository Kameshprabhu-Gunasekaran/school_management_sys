package school_management_sys.controller;

import school_management_sys.dto.ResponseDTO;
import school_management_sys.entity.Tutor;
import school_management_sys.service.TutorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tutors")
public class TutorController {

    private final TutorService tutorService;

    public TutorController(TutorService tutorService) {
        this.tutorService = tutorService;
    }

    @PostMapping("/create")
    public ResponseDTO createTutor(@RequestBody final Tutor tutor) {
        return this.tutorService.createTutor(tutor);
    }

    @GetMapping("/retrieve-all")
    public ResponseDTO retrieveAllTutors() {
        return this.tutorService.retrieveAllTutors();
    }

    @GetMapping("/retrieve-id/{id}")
    public ResponseDTO retrieveTutorById(@PathVariable("id") final Long id) {
        return this.tutorService.retrieveTutorById(id);
    }

    @PutMapping("/update-id/{id}")
    public ResponseDTO updateTutorById(@PathVariable("id") final Long id, @RequestBody Tutor tutor) {
        return this.tutorService.updateTutorById(id, tutor);
    }

    @DeleteMapping("/delete-id/{id}")
    public ResponseDTO deleteTutorById(@PathVariable("id") final Long id) {
        return this.tutorService.deleteTutorById(id);
    }
}
