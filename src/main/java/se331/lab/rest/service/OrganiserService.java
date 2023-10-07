package se331.lab.rest.service;

import org.springframework.data.domain.Page;
import se331.lab.rest.entity.Organiser;

import java.util.List;

public interface OrganiserService {
    Integer getOrganiserSize();
    Page<Organiser> getOrganisers(Integer pageSize, Integer page);
    Organiser getOrganiser(Long id);
    Organiser save(Organiser organiser);
}
