package se331.lab.rest.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import se331.lab.rest.service.OrganiserService;
import se331.lab.rest.service.ParticipantService;
import se331.lab.rest.util.LabMapper;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    String name;
    String telNo;

    @ManyToMany()
    @JoinTable(name = "event_participants", joinColumns = @JoinColumn(name = "participants_id"), inverseJoinColumns = @JoinColumn(name = "event_id"))
    @Builder.Default
    List<Event> eventHistory = new ArrayList<>();
}
