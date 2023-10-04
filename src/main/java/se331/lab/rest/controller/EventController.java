package se331.lab.rest.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.RequestParam;
import se331.lab.rest.entity. Event;
import se331.lab.rest.service.EventService;

import java.util.List;

@Controller
public class EventController {
    final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("events")
    public ResponseEntity<?> getEventLists(
            @RequestParam(value = "_limit", required = false) Integer perPage,
            @RequestParam(value = "_page", required = false) Integer page) {

//        perPage = perPage == null ? eventList.size() : perPage;
//        page = page == null ? 1 : page;
//        Integer firstIndex = (page - 1) * perPage;
//        int lastIndex = Math.min(firstIndex + perPage, eventList.size()); // Calculate lastIndex to prevent IndexOutOfBoundsException

        List<Event> output = null;
        Integer eventSize = eventService.getEventSize();
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("x-total-count",String.valueOf(eventSize));
        try{
//            for (int i = firstIndex; i < lastIndex; i++) {
//                output.add(eventList.get(i));
//            }
            output = eventService.getEvents(perPage, page);
            return new ResponseEntity<>(output, responseHeader,HttpStatus.OK );
        }catch (IndexOutOfBoundsException event){
            return new ResponseEntity<>(output, responseHeader,HttpStatus.OK );
        }

    }

    @GetMapping("events/{id}")
    public ResponseEntity<?> getEvent(@PathVariable("id") Long id) {
//        Event output = null;
//        for (Event event: eventList){
//            if(event.getId().equals(id)){
//                output = event;
//                break;
//            }
//        }
        Event output = eventService.getEvent(id);
        if (output != null){
            return ResponseEntity.ok(output);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"The given id is not found");
        }
    }


}
