package se331.lab.rest.service;

import se331.lab.rest.entity.Organiser;

import java.util.List;

public interface OrganiserService {
    Integer getOrganiserSize();
    List<Organiser> getOrganisers(Integer pageSize, Integer page);
    Organiser getOrganiser(Long id);
}
