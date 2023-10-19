package se331.lab.rest.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se331.lab.rest.dao.EventDao;
import se331.lab.rest.dao.OrganiserDao;
import se331.lab.rest.dao.ParticipantDao;
import se331.lab.rest.entity.Event;
import se331.lab.rest.entity.Organiser;

import java.util.List;


@Service
@RequiredArgsConstructor
public class EventServiceImpl  implements  EventService{
//    @Autowired
    final EventDao eventDao;
    final OrganiserDao organiserDao;
    final ParticipantDao participantDao;


    @Override
    public Integer getEventSize(){
        return eventDao.getEventSize();
    }

    @Override
    public Page<Event> getEvents(Integer pageSize, Integer page) {
        return eventDao.getEvents(pageSize, page);
    }

    @Override
    public Event getEvent(Long id){
        return eventDao.getEvent(id);
    }
    @Transactional
    @Override
    public Event save(Event event) {
        Organiser organiser = organiserDao.findById(event.getOrganiser().getId()).orElse(null);
        event.setOrganiser(organiser);
        organiser.getOwnEvents().add(event);
        return eventDao.save(event);
    }
    @Override
    public Page<Event> getEvents(String title, String description, String organizerName, Pageable pageRequest)
    {
        return eventDao.getEvents(title, description, organizerName, pageRequest);
    }

}
