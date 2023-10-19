package se331.lab.rest.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import se331.lab.rest.entity.*;
import java.util.List;

@Mapper
public interface LabMapper {
    LabMapper INSTANCE = Mappers.getMapper(LabMapper.class);
    EventDTO getEventDto(Event event);
    List<EventDTO> getEventDto(List<Event> events);
    OrganiserDTO getOrganiserDTO(Organiser organiser);
    List<OrganiserDTO> getOrganiserDTO(List<Organiser> organisers);

    @Mapping(target="roles", source="user.roles")
    OrganiserAuthDTO getOrganiserAuthDTO(Organiser organiser);

    ParticipantDTO getParticipantDTO(Participant participant);
    List<ParticipantDTO> getParticipantDTO(List<Participant> participant);

}
