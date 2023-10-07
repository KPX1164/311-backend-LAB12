package se331.lab.rest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import se331.lab.rest.dao.OrganiserDao;
import se331.lab.rest.entity.Organiser;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganiserServiceImpl implements OrganiserService{
    final OrganiserDao organiserDao;
    @Override
    public Integer getOrganiserSize(){
        return organiserDao.getOrganiserSize();
    }

    @Override
    public Page<Organiser> getOrganisers(Integer pageSize, Integer page) {
        return organiserDao.getOrganisers(pageSize, page);
    }

    @Override
    public Organiser getOrganiser(Long id){
        return organiserDao.getOrganiser(id);
    }

    @Override
    public Organiser save(Organiser organiser) {
        return organiserDao.save(organiser);
    }
}
