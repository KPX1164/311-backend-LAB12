package se331.lab.rest.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.lab.rest.entity.Organiser;
import java.util.Optional;

public interface OrganiserDao {
    Page<Organiser> getOrganiser(Pageable pageRequest);
    Optional<Organiser> findById(Long id);
    Organiser save(Organiser organiser);

}
