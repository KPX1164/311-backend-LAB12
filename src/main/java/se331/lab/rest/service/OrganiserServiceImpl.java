package se331.lab.rest.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se331.lab.rest.dao.OrganiserDao;
import se331.lab.rest.entity.Organiser;
import java.util.List;

@Service
@RequiredArgsConstructor

public class OrganiserServiceImpl implements OrganiserService{
    final OrganiserDao organiserDao;
    @Override
    public List<Organiser> getAllOrganiser() {

        return organiserDao.getOrganiser(Pageable.unpaged()).getContent();
    }
    @Override
    public Page<Organiser> getOrganiser(Integer page, Integer pageSize) {
        return organiserDao.getOrganiser(PageRequest.of(page,pageSize));
    }
    @Override
    public Organiser getOrganiserById(Long id) {
        return organiserDao.findById(id).orElse(null);
    }
    @Override
    @Transactional
    public Organiser save(Organiser organiser) {
        return organiserDao.save(organiser);
    }
}
