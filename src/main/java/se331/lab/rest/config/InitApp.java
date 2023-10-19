package se331.lab.rest.config;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import se331.lab.rest.entity.Event;
import se331.lab.rest.entity.Organiser;
import se331.lab.rest.entity.Participant;
import se331.lab.rest.repository.EventRepository;
import se331.lab.rest.repository.OrganiserRepository;
import se331.lab.rest.repository.ParticipantRepository;
import se331.lab.rest.security.user.Role;
import se331.lab.rest.security.user.User;
import se331.lab.rest.security.user.UserRepository;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;


@Component
@RequiredArgsConstructor
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    final EventRepository eventRepository;
    final OrganiserRepository organiserRepository;
    final ParticipantRepository participantRepository;
    final UserRepository userRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        Organiser org1,org2,org3;
        org1 = organiserRepository.save(Organiser.builder().name("CAMT").build());
        org2 = organiserRepository.save(Organiser.builder().name("CMU").build());
        org3 = organiserRepository.save(Organiser.builder().name("ChiangMai").build());

        Participant pat1,pat2,pat3;
        pat1 = participantRepository.save(Participant.builder().name("Kale").telNo("123-134-2345").build());
        pat2 = participantRepository.save(Participant.builder().name("Maly").telNo("123-134-2345").build());
        pat3 = participantRepository.save(Participant.builder().name("Kopo").telNo("123-134-2345").build());

        Event tempEvent;
        tempEvent = eventRepository.save(Event.builder()
                                .category("Academic")
                                .title("Midterm Exam")
                                .description("A time for taking the exam")
                                .location("CAMT Building")
                                .date("3rd Sept")
                                .time("3.00-4.00 pm.")
                                .petAllowed(false)
                                .build());
        tempEvent.setOrganiser(org1);
        tempEvent.getParticipants().add(pat1);
        tempEvent.getParticipants().add(pat2);
        tempEvent.getParticipants().add(pat3);

        org1.getOwnEvents().add(tempEvent);
        tempEvent = eventRepository.save(Event.builder()
                                .category("Academic")
                                .title("Commencement Day")
                                .description("A time for celebration")
                                .location("CMU Convention hall")
                                .date("21th Jan")
                                .time("8.00am-4.00 pm.")
                                .petAllowed(false)
                                .build());
        tempEvent.setOrganiser(org1);
        tempEvent.getParticipants().add(pat1);
        tempEvent.getParticipants().add(pat2);
        tempEvent.getParticipants().add(pat3);

        org1.getOwnEvents().add(tempEvent);
        tempEvent = eventRepository.save(Event.builder()
                                .category("Cultural")
                                .title("Loy Krathong")
                                .description("A time for Krathong")
                                .location("Ping River")
                                .date("21th Nov")
                                .time("8.00-10.00 pm.")
                                .petAllowed(false)
                                .build());
        tempEvent.setOrganiser(org2);
        tempEvent.getParticipants().add(pat1);
        tempEvent.getParticipants().add(pat2);
        tempEvent.getParticipants().add(pat3);

        org2.getOwnEvents().add(tempEvent);
        tempEvent = eventRepository.save(Event.builder()
                                .category("Cultural")
                                .title("Songkran")
                                .description("Let's Play Water")
                                .location("Chiang Mai Moat")
                                .date("13th April")
                                .time("10.00am - 6.00 pm.")
                                .petAllowed(true)
                                .build());
        tempEvent.setOrganiser(org3);

        tempEvent.getParticipants().add(pat1);
        tempEvent.getParticipants().add(pat2);
        tempEvent.getParticipants().add(pat3);

        org3.getOwnEvents().add(tempEvent);

        addUser();
        org1.setUser(user1);
        user1.setOrganiser(org1);
        org2.setUser(user2);
        user2.setOrganiser(org2);
        org3.setUser(user3);
        user3.setOrganiser(org3);
    }
    User user1, user2, user3;
    private void addUser() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        user1 = User.builder()
                .username("admin")
                .password(encoder.encode("admin"))
                .firstname("admin")
                .lastname("admin")
                .email("admin@admin.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        user2 = User.builder()
                .username("user")
                .password(encoder.encode("user"))
                .firstname("user")
                .lastname("user")
                .email("enabled@user.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        user3 = User.builder()
                .username("disableUser")
                .password(encoder.encode("disableUser"))
                .firstname("disableUser")
                .lastname("disableUser")
                .email("disableUser@user.com")
                .enabled(false)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();


        user1.getRoles().add(Role.ROLE_ADMIN);
        user2.getRoles().add(Role.ROLE_DISTRIBUTOR);
        user3.getRoles().add(Role.ROLE_DISTRIBUTOR);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);


    }
}

