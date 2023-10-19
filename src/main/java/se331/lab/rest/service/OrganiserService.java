package se331.lab.rest.service;

import org.springframework.data.domain.Page;
import se331.lab.rest.entity.Organiser;

import java.util.List;

public interface OrganiserService {
    List<Organiser> getAllOrganiser();
    Page<Organiser> getOrganiser(Integer page, Integer pageSize);
    Organiser getOrganiserById (Long id);

    Organiser save(Organiser organiser);

}
