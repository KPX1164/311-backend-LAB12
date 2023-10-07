package se331.lab.rest.dao;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import se331.lab.rest.entity.Organiser;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Profile("manual")
public class OrganiserDaoImpl implements OrganiserDao{
    List<Organiser> organiserList;

    @PostConstruct
    public void init() {
        organiserList = new ArrayList<>();
        organiserList.add(Organiser.builder()
                .id(123L)
                .name("Kat Laydee")
                .address("Building Number : 37881 Street Name : Eichmann Union Street Address : 4297 Jasper Hill Apt. 638 State : Nevada City : Zulaland Post Code : 19489")
                .build()
        );

        organiserList.add(Organiser.builder()
                .id(124L)
                .name("Helen Ban")
                .address("Building Number : 9318 Street Name : O'Connell Camp Street Address : 2577 Ebba Well Suite 229 State : Kentucky City : Maximoland Post Code : 56181-1511")
                .build()
        );
        organiserList.add(Organiser.builder()
                .id(125L)
                .name("Kat Sa")
                .address("Building Number : 9408 Street Name : Tillman Fall Street Address : 12965 Wuckert Rapid Suite 969 State : Wisconsin City : New Alize Post Code : 06945")
                .build()
        );

        organiserList.add(Organiser.builder()
                .id(126L)
                .name("Nisa Ban")
                .address("Building Number : 167 Street Name : Batz Motorway Street Address : 24811 Susan Square State : Oregon City : North Precious Post Code : 85095")
                .build()
        );
        organiserList.add(Organiser.builder()
                .id(127L)
                .name("Lali Sama")
                .address("Building Number : 615 Street Name : Schiller Haven Street Address : 7115 Pagac Trace State : Washington City : Stanleyshire Post Code : 52203-8818")
                .build()
        );
    }
    @Override
    public Integer getOrganiserSize() {
        return organiserList.size();
    }

    @Override
    public Page<Organiser> getOrganisers(Integer pageSize, Integer page) {
        pageSize = pageSize == null ? organiserList.size() : pageSize;
        page = page == null ? 1 : page;

        int firstIndex = (page - 1) * pageSize;
        return new PageImpl<Organiser>(organiserList.subList(firstIndex,firstIndex+pageSize), PageRequest.of(page,pageSize),organiserList.size());

    }

    @Override
    public Organiser getOrganiser(Long id) {
        return organiserList.stream().filter(organiser ->
                        organiser.getId()
                                .equals(id))
                .findFirst()
                .orElse(null);
    }
    @Override
    public Organiser save(Organiser organiser) {
                organiser.setId(organiserList.get(organiserList.size()-1).getId()+1);
                organiserList.add(organiser);
                return organiser;
            }

}
