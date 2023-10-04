package se331.lab.rest.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
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
        List<Organiser> output = null;
        Integer organiserSize = organiserService.getOrganiserSize();
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("x-total-count",String.valueOf(organiserSize));
        try{
            output = organiserService.getOrganisers(perPage, page);
            return new ResponseEntity<>(output, responseHeader, HttpStatus.OK );
        }catch (IndexOutOfBoundsException event){
            return new ResponseEntity<>(output, responseHeader,HttpStatus.OK );
        }

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
}
