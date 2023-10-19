package se331.lab.rest.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import se331.lab.rest.entity.Organiser;
import se331.lab.rest.repository.OrganiserRepository;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrganiserDaoImpl implements OrganiserDao {
    final OrganiserRepository organiserRepository;

    @Override
    public Page<Organiser> getOrganiser(Pageable pageRequest) {

        return organiserRepository.findAll(pageRequest);
    }

    @Override
    public Optional<Organiser> findById(Long id) {

        return organiserRepository.findById(id);
    }

    @Override
    public Organiser save(Organiser organiser) {
        return organiserRepository.save(organiser);
    }
}
