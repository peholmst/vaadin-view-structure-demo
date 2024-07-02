package com.example.application.data;

import com.vaadin.hilla.BrowserCallable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@BrowserCallable
@Service
public class TeamService {

    private final Map<String, Employee> employees = Map.of(
            "dJPtOxV3n", new Employee("dJPtOxV3n", "Esther", "Howard", "Software Development Manager"),
            "XIFloA5M3", new Employee("XIFloA5M3", "Darlene", "Robertson", "Software Development Manager")
    );
    private final List<Team> teams = List.of(
            new Team("hmdCDSmF3", "Research and Development (R&D)", "Spearheading innovation and cutting-edge technology solutions."),
            new Team("AiPhxywOI", "Customer Experience (CX)", "Dedicated to ensuring seamless interactions and satisfaction for clients. The CX team focuses on understanding user needs."),
            new Team("3dCjxWEc3", "Services", "Crafting robust and scalable software solutions to meet client needs efficiently."),
            new Team("PIRrEiAFl", "Data Analytics", "Extracting valuable insights from data to drive informed decision-making and optimize business processes."),
            new Team("BVB6fAqiZ", "Project Management", "Coordinating and overseeing the execution of IT projects, from inception to delivery, ensuring alignment with client objectives and timelines."),
            new Team("Irpn9NHPm", "Artificial Intelligence (AI) and Machine Learning", "Exploring and implementing AI-driven solutions to automate processes, improve efficiency, and drive innovation."),
            new Team("kKj6CzWw9", "Technical Support", "Providing timely assistance and troubleshooting for clients and internal users to resolve technical issues and ensure smooth operations.")
    );
    private final Map<String, TeamDetails> teamDetails = Map.of(
            "hmdCDSmF3", new TeamDetails("hmdCDSmF3", "Research and Development (R&D)", "Spearheading innovation and cutting-edge technology solutions.",
                    List.of(
                            new TeamMember(employees.get("dJPtOxV3n"), true, LocalDate.of(2023, 6, 2)),
                            new TeamMember(employees.get("dJPtOxV3n"), true, LocalDate.of(2023, 6, 3)),
                            new TeamMember(employees.get("dJPtOxV3n"), true, LocalDate.of(2023, 6, 4)),
                            new TeamMember(employees.get("dJPtOxV3n"), true, LocalDate.of(2023, 6, 5)),
                            new TeamMember(employees.get("dJPtOxV3n"), true, LocalDate.of(2023, 6, 6)),
                            new TeamMember(employees.get("dJPtOxV3n"), true, LocalDate.of(2023, 6, 7)),
                            new TeamMember(employees.get("XIFloA5M3"), true, LocalDate.of(2026, 3, 7))
                    )),
            "AiPhxywOI", new TeamDetails("AiPhxywOI", "Customer Experience (CX)", "Dedicated to ensuring seamless interactions and satisfaction for clients. The CX team focuses on understanding user needs.", Collections.emptyList()),
            "3dCjxWEc3", new TeamDetails("3dCjxWEc3", "Services", "Crafting robust and scalable software solutions to meet client needs efficiently.", Collections.emptyList()),
            "PIRrEiAFl", new TeamDetails("PIRrEiAFl", "Data Analytics", "Extracting valuable insights from data to drive informed decision-making and optimize business processes.", Collections.emptyList()),
            "BVB6fAqiZ", new TeamDetails("BVB6fAqiZ", "Project Management", "Coordinating and overseeing the execution of IT projects, from inception to delivery, ensuring alignment with client objectives and timelines.", Collections.emptyList()),
            "Irpn9NHPm", new TeamDetails("Irpn9NHPm", "Artificial Intelligence (AI) and Machine Learning", "Exploring and implementing AI-driven solutions to automate processes, improve efficiency, and drive innovation.", Collections.emptyList()),
            "kKj6CzWw9", new TeamDetails("kKj6CzWw9", "Technical Support", "Providing timely assistance and troubleshooting for clients and internal users to resolve technical issues and ensure smooth operations.", Collections.emptyList())
    );

    public List<Team> findTeams(String searchTerm) {
        if (searchTerm == null || searchTerm.isBlank()) {
            return teams;
        } else {
            return teams.stream().filter(team -> team.name().toLowerCase().contains(searchTerm.toLowerCase())).toList();
        }
    }

    public Optional<Team> findTeamByPublicId(String publicId) {
        return teams.stream().filter(team -> team.publicId().equals(publicId)).findFirst();
    }

    public Optional<TeamDetails> findTeamDetailsByPublicId(String publicId) {
        return Optional.ofNullable(teamDetails.get(publicId));
    }
}
