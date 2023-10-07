package se331.lab.rest.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se331.lab.rest.entity.Event;
import se331.lab.rest.entity.Organiser;
import se331.lab.rest.service.OrganiserService;

import java.util.List;

@Controller
public class OrganiserController {
    final OrganiserService organiserService;

    public OrganiserController(OrganiserService organiserService) {

        this.organiserService = organiserService;
    }

    @GetMapping("organisers")
    public ResponseEntity<?> getOrganiserLists(
            @RequestParam(value = "_limit", required = false) Integer perPage,
            @RequestParam(value = "_page", required = false) Integer page) {

        Page<Organiser> pageOutput = organiserService.getOrganisers(perPage, page);
        HttpHeaders responseHeader = new HttpHeaders();

        responseHeader.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
        return new ResponseEntity<>(pageOutput.getContent(),responseHeader,HttpStatus.OK);

    }
    @GetMapping("organiser/{id}")
    public ResponseEntity<?> getOrganiser(@PathVariable("id") Long id) {
        Organiser output = organiserService.getOrganiser(id);
        if (output != null){
            return ResponseEntity.ok(output);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"The given id is not found");
        }
    }

    @PostMapping("/organisers")
    public ResponseEntity<?> addOrganiser(@RequestBody Organiser organiser){
        Organiser output = organiserService.save(organiser);
        return ResponseEntity.ok(output);
    }

}
