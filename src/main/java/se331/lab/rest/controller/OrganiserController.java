package se331.lab.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se331.lab.rest.entity.Event;
import se331.lab.rest.entity.Organiser;
import se331.lab.rest.service.OrganiserService;
import se331.lab.rest.util.LabMapper;

@RestController
@RequiredArgsConstructor
public class OrganiserController {
    final OrganiserService organiserService;

    @GetMapping("/organisers")
    ResponseEntity<?> getOrganisers() {
        System.out.println(LabMapper.INSTANCE.getOrganiserDTO(organiserService.getAllOrganiser()));
        return ResponseEntity.ok(LabMapper.INSTANCE.getOrganiserDTO(organiserService.getAllOrganiser()));

    }

    @GetMapping("organisers/{id}")
    public ResponseEntity<?> getOrganizer(@PathVariable("id") Long id) {
        Organiser output = organiserService.getOrganiserById(id);
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getOrganiserDTO(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }
    @PostMapping("/organisers")
    public ResponseEntity<?> addOrganizer (@RequestBody Organiser organizer) {
        Organiser output = organiserService.save(organizer);
        return ResponseEntity.ok(LabMapper.INSTANCE.getOrganiserDTO(output));
    }


}
