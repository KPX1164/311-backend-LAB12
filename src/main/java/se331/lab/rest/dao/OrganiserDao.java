package se331.lab.rest.dao;

import se331.lab.rest.entity.Organiser;

import java.util.List;

public interface OrganiserDao {
    Integer getOrganiserSize();
    List<Organiser> getOrganisers(Integer pageSize, Integer page);
    Organiser getOrganiser(Long id);
}
