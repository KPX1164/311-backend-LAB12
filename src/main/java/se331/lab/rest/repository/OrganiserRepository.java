package se331.lab.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se331.lab.rest.entity.Organiser;

import java.util.List;

public interface OrganiserRepository extends JpaRepository<Organiser,Long> {
    List<Organiser> findAll();
}
