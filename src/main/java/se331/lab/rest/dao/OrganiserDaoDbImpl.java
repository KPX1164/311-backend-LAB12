package se331.lab.rest.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import se331.lab.rest.entity.Organiser;
import se331.lab.rest.repository.OrganiserRepository;

@Repository
@RequiredArgsConstructor
@Profile("db")
public class OrganiserDaoDbImpl implements OrganiserDao {
    final OrganiserRepository organiserRepository;
    @Override
    public Integer getOrganiserSize() {

        return Math.toIntExact(organiserRepository.count());
    }

    @Override
    public Page<Organiser> getOrganisers(Integer pageSize, Integer page) {
        return organiserRepository.findAll(PageRequest.of(page - 1, pageSize));
    }


    @Override
    public Organiser getOrganiser(Long id) {

        return organiserRepository.findById(id).orElse(null);
    }
    @Override
    public Organiser save(Organiser organiser) {
        return organiserRepository.save(organiser);
    }

}
