package se331.lab.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import se331.lab.rest.entity.Participant;
import se331.lab.rest.service.OrganiserService;
import se331.lab.rest.service.ParticipantService;
import se331.lab.rest.util.LabMapper;

@RestController
@RequiredArgsConstructor
public class ParticipantController {
    final ParticipantService participantService;

    @GetMapping("/participants")
    ResponseEntity<?> getParticipants() {
        return ResponseEntity.ok(LabMapper.INSTANCE.getParticipantDTO(participantService.getAllParticipant()));
    }

    @GetMapping("participants/{id}")
    public ResponseEntity<?> getEvent(@PathVariable("id") Long id) {
        Participant output = participantService.getParticipantById(id);
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getParticipantDTO(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }

}
