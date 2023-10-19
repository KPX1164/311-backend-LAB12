package se331.lab.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganiserDTO {
    Long id;
    String name;
    List<OrganiserOwnEventsDTO> ownEvents = new ArrayList<>();
    List<String> images;

}
