package com.example.application.data;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import com.vaadin.hilla.Nonnull;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@BrowserCallable
@AnonymousAllowed
@Service
public class TeamService {

    // TODO Adjust the titles so that they match their teams
    private final Map<String, Employee> employees = Map.ofEntries(
            Map.entry("dJPtOxV3n", new Employee("dJPtOxV3n", "Esther", "Howard", "Software Development Manager", "+1 (202) 555-0117", "esther.howard@acme.com", "America/New_York", "images/avatars/esther_howard.jpg")),
            Map.entry("XIFloA5M3", new Employee("XIFloA5M3", "Darlene", "Robertson", "Software Development Manager", "+44 20 7946 0958", "darlene.robertson@acme.co.uk", "Europe/London", "images/avatars/darlene_robertson.jpg")),
            Map.entry("A1B2C3D4E", new Employee("A1B2C3D4E", "John", "Doe", "Software Engineer", "+49 30 1234 5678", "john.doe@acme.de", "Europe/Berlin", "images/avatars/john_doe.jpg")),
            Map.entry("F5G6H7I8J", new Employee("F5G6H7I8J", "Jane", "Smith", "Product Manager", "+33 1 2345 6789", "jane.smith@acme.fr", "Europe/Paris", "images/avatars/jane_smith.jpg")),
            Map.entry("K9L0M1N2O", new Employee("K9L0M1N2O", "Alice", "Johnson", "QA Engineer", "+61 2 1234 5678", "alice.johnson@acme.au", "Australia/Sydney", "images/avatars/alice_johnson.jpg")),
            Map.entry("P3Q4R5S6T", new Employee("P3Q4R5S6T", "Bob", "Brown", "DevOps Engineer", "+81 3-1234-5678", "bob.brown@acme.jp", "Asia/Tokyo", "images/avatars/bob_brown.jpg")),
            Map.entry("U7V8W9X0Y", new Employee("U7V8W9X0Y", "Charlie", "Davis", "Data Scientist", "+55 11 1234-5678", "charlie.davis@acme.br", "America/Sao_Paulo", "images/avatars/charlie_davis.jpg")),
            Map.entry("Z1A2B3C4D", new Employee("Z1A2B3C4D", "David", "Miller", "UI/UX Designer", "+34 91 123 4567", "david.miller@acme.es", "Europe/Madrid", "images/avatars/david_miller.jpg")),
            Map.entry("E5F6G7H8I", new Employee("E5F6G7H8I", "Emma", "Wilson", "Backend Developer", "+1 (416) 555-0198", "emma.wilson@acme.ca", "America/Toronto", "images/avatars/emma_wilson.jpg")),
            Map.entry("J9K0L1M2N", new Employee("J9K0L1M2N", "Frank", "Moore", "Frontend Developer", "+27 21 123 4567", "frank.moore@acme.za", "Africa/Johannesburg", "images/avatars/frank_moore.jpg")),
            Map.entry("O3P4Q5R6S", new Employee("O3P4Q5R6S", "Grace", "Taylor", "Database Administrator", "+91 22 1234 5678", "grace.taylor@acme.in", "Asia/Kolkata", "images/avatars/grace_taylor.jpg")),
            Map.entry("T7U8V9W0X", new Employee("T7U8V9W0X", "Hannah", "Anderson", "System Architect", "+86 10 1234 5678", "hannah.anderson@acme.cn", "Asia/Shanghai", "images/avatars/hannah_anderson.jpg")),
            Map.entry("Y1Z2A3B4C", new Employee("Y1Z2A3B4C", "Ian", "Thomas", "Network Engineer", "+39 06 1234 5678", "ian.thomas@acme.it", "Europe/Rome", "images/avatars/ian_thomas.jpg")),
            Map.entry("D5E6F7G8H", new Employee("D5E6F7G8H", "Jack", "Jackson", "Security Analyst", "+7 495 123-4567", "jack.jackson@acme.ru", "Europe/Moscow", "images/avatars/jack_jackson.jpg")),
            Map.entry("I9J0K1L2M", new Employee("I9J0K1L2M", "Karen", "White", "Technical Support Specialist", "+66 2 123 4567", "karen.white@acme.th", "Asia/Bangkok", "images/avatars/karen_white.jpg")),
            Map.entry("N3O4P5Q6R", new Employee("N3O4P5Q6R", "Leo", "Harris", "Business Analyst", "+65 1234 5678", "leo.harris@acme.sg", "Asia/Singapore", "images/avatars/leo_harris.jpg")),
            Map.entry("S7T8U9V0W", new Employee("S7T8U9V0W", "Mia", "Martin", "Scrum Master", "+852 1234 5678", "mia.martin@acme.hk", "Asia/Hong_Kong", "images/avatars/mia_martin.jpg")),
            Map.entry("X1Y2Z3A4B", new Employee("X1Y2Z3A4B", "Nathan", "Clark", "Release Manager", "+64 9 123 4567", "nathan.clark@acme.nz", "Pacific/Auckland", "images/avatars/nathan_clark.jpg")),
            Map.entry("C5D6E7F8G", new Employee("C5D6E7F8G", "Olivia", "Lewis", "Agile Coach", "+358 9 123 4567", "olivia.lewis@acme.fi", "Europe/Helsinki", "images/avatars/olivia_lewis.jpg")),
            Map.entry("H9I0J1K2L", new Employee("H9I0J1K2L", "Paul", "Walker", "Project Coordinator", "+46 8 123 4567", "paul.walker@acme.se", "Europe/Stockholm", "images/avatars/paul_walker.jpg")),
            Map.entry("M3N4O5P6Q", new Employee("M3N4O5P6Q", "Quinn", "Hall", "IT Support Specialist", "+41 44 123 4567", "quinn.hall@acme.ch", "Europe/Zurich", "images/avatars/quinn_hall.jpg")),
            Map.entry("R7S8T9U0V", new Employee("R7S8T9U0V", "Rachel", "Allen", "Solutions Architect", "+32 2 123 4567", "rachel.allen@acme.be", "Europe/Brussels", "images/avatars/rachel_allen.jpg")),
            Map.entry("S9T8U7V6W", new Employee("S9T8U7V6W", "Sophie", "Garcia", "Marketing Specialist", "+52 55 1234 5678", "sophie.garcia@acme.mx", "America/Mexico_City", "images/avatars/sophie_garcia.jpg")),
            Map.entry("Q1R2S3T4U", new Employee("Q1R2S3T4U", "Ben", "Lopez", "Sales Manager", "+351 21 123 4567", "ben.lopez@acme.pt", "Europe/Lisbon", "images/avatars/ben_lopez.jpg")),
            Map.entry("V5W6X7Y8Z", new Employee("V5W6X7Y8Z", "Eva", "Martinez", "Operations Manager", "+54 11 1234 5678", "eva.martinez@acme.ar", "America/Argentina/Buenos_Aires", "images/avatars/eva_martinez.jpg")),
            Map.entry("A2B3C4D5E", new Employee("A2B3C4D5E", "Liam", "Rodriguez", "Financial Analyst", "+60 3-1234 5678", "liam.rodriguez@acme.my", "Asia/Kuala_Lumpur", "images/avatars/liam_rodriguez.jpg")),
            Map.entry("F1G2H3I4J", new Employee("F1G2H3I4J", "Mason", "Lee", "Graphic Designer", "+62 21 1234 5678", "mason.lee@acme.id", "Asia/Jakarta", "images/avatars/mason_lee.jpg")),
            Map.entry("K5L6M7N8O", new Employee("K5L6M7N8O", "Ella", "Kim", "HR Specialist", "+82 2-123-4567", "ella.kim@acme.kr", "Asia/Seoul", "images/avatars/ella_kim.jpg")),
            Map.entry("P1Q2R3S4T", new Employee("P1Q2R3S4T", "James", "Wang", "Content Writer", "+86 21 1234 5678", "james.wang@acme.cn", "Asia/Shanghai", "images/avatars/james_wang.jpg")),
            Map.entry("U5V6W7X8Y", new Employee("U5V6W7X8Y", "Chloe", "Patel", "Event Coordinator", "+91 44 1234 5678", "chloe.patel@acme.in", "Asia/Kolkata", "images/avatars/chloe_patel.jpg")),
            Map.entry("Z3A4B5C6D", new Employee("Z3A4B5C6D", "Lucas", "Nguyen", "Social Media Manager", "+84 28 1234 5678", "lucas.nguyen@acme.vn", "Asia/Ho_Chi_Minh", "images/avatars/lucas_nguyen.jpg")),
            Map.entry("E7F8G9H0I", new Employee("E7F8G9H0I", "Emily", "Thomas", "SEO Specialist", "+63 2 1234 5678", "emily.thomas@acme.ph", "Asia/Manila", "images/avatars/emily_thomas.jpg")),
            Map.entry("J5K6L7M8N", new Employee("J5K6L7M8N", "Jacob", "Perez", "Customer Success Manager", "+64 4 123 4567", "jacob.perez@acme.nz", "Pacific/Auckland", "images/avatars/jacob_perez.jpg")),
            Map.entry("O1P2Q3R4S", new Employee("O1P2Q3R4S", "Amelia", "Carter", "Digital Marketing Manager", "+61 3 1234 5678", "amelia.carter@acme.au", "Australia/Melbourne", "images/avatars/amelia_carter.jpg")),
            Map.entry("T5U6V7W8X", new Employee("T5U6V7W8X", "Ethan", "Mitchell", "PR Specialist", "+353 1 123 4567", "ethan.mitchell@acme.ie", "Europe/Dublin", "images/avatars/ethan_mitchell.jpg")),
            Map.entry("Y2Z3A4B5C", new Employee("Y2Z3A4B5C", "Aiden", "Gonzalez", "Account Manager", "+45 33 123 4567", "aiden.gonzalez@acme.dk", "Europe/Copenhagen", "images/avatars/aiden_gonzalez.jpg")),
            Map.entry("D7E8F9G0H", new Employee("D7E8F9G0H", "Harper", "Phillips", "Research Analyst", "+48 22 123 4567", "harper.phillips@acme.pl", "Europe/Warsaw", "images/avatars/harper_phillips.jpg"))
    );
    private final List<Team> teams = List.of(
            new Team("hmdCDSmF3", "Research and Development (R&D)", "Spearheading innovation and cutting-edge technology solutions."),
            new Team("AiPhxywOI", "Customer Experience (CX)", "Dedicated to ensuring seamless interactions and satisfaction for clients. The CX team focuses on understanding user needs."),
            new Team("3dCjxWEc3", "Services", "Crafting robust and scalable software solutions to meet client needs efficiently."),
            new Team("PIRrEiAFl", "Data Analytics", "Extracting valuable insights from data to drive informed decision-making and optimize business processes."),
            new Team("BVB6fAqiZ", "Project Management", "Coordinating and overseeing the execution of IT projects, from inception to delivery, ensuring alignment with client objectives and timelines."),
            new Team("Irpn9NHPm", "Artificial Intelligence (AI) and Machine Learning", "Exploring and implementing AI-driven solutions to automate processes, improve efficiency, and drive innovation."),
            new Team("kKj6CzWw9", "Technical Support", "Providing timely assistance and troubleshooting for clients and internal users to resolve technical issues and ensure smooth operations."),
            new Team("O1P2Q3R4S", "Digital Marketing", "Developing and executing digital marketing strategies to enhance brand visibility and drive customer engagement."),
            new Team("T5U6V7W8X", "PR", "Managing public relations and communications to maintain a positive brand image and foster relationships with stakeholders.")
    );
    private final Map<String, TeamDetails> teamDetails = Map.of(
            "hmdCDSmF3", new TeamDetails("hmdCDSmF3", "Research and Development (R&D)", "Spearheading innovation and cutting-edge technology solutions.",
                    List.of(
                            new TeamMember(employees.get("dJPtOxV3n"), true, LocalDate.of(2023, 6, 2)),
                            new TeamMember(employees.get("XIFloA5M3"), true, LocalDate.of(2003, 3, 7)),
                            new TeamMember(employees.get("A1B2C3D4E"), false, LocalDate.of(2019, 1, 15)),
                            new TeamMember(employees.get("F5G6H7I8J"), false, LocalDate.of(2018, 2, 18)),
                            new TeamMember(employees.get("S9T8U7V6W"), false, LocalDate.of(2013, 3, 25)),
                            new TeamMember(employees.get("Q1R2S3T4U"), false, LocalDate.of(2001, 4, 30)),
                            new TeamMember(employees.get("V5W6X7Y8Z"), false, LocalDate.of(2009, 5, 1)),
                            new TeamMember(employees.get("A2B3C4D5E"), false, LocalDate.of(2022, 6, 5))
                    )),
            "AiPhxywOI", new TeamDetails("AiPhxywOI", "Customer Experience (CX)", "Dedicated to ensuring seamless interactions and satisfaction for clients. The CX team focuses on understanding user needs.",
                    List.of(
                            new TeamMember(employees.get("K9L0M1N2O"), true, LocalDate.of(2019, 4, 22)),
                            new TeamMember(employees.get("P3Q4R5S6T"), false, LocalDate.of(2023, 5, 30)),
                            new TeamMember(employees.get("U7V8W9X0Y"), false, LocalDate.of(2023, 6, 10)),
                            new TeamMember(employees.get("F1G2H3I4J"), false, LocalDate.of(2023, 6, 11)),
                            new TeamMember(employees.get("K5L6M7N8O"), false, LocalDate.of(2022, 3, 20))
                    )),
            "3dCjxWEc3", new TeamDetails("3dCjxWEc3", "Services", "Crafting robust and scalable software solutions to meet client needs efficiently.",
                    List.of(
                            new TeamMember(employees.get("Z1A2B3C4D"), true, LocalDate.of(2015, 7, 5)),
                            new TeamMember(employees.get("E5F6G7H8I"), false, LocalDate.of(2018, 8, 14)),
                            new TeamMember(employees.get("J9K0L1M2N"), false, LocalDate.of(2020, 9, 20)),
                            new TeamMember(employees.get("P1Q2R3S4T"), false, LocalDate.of(2021, 3, 10))
                    )),
            "PIRrEiAFl", new TeamDetails("PIRrEiAFl", "Data Analytics", "Extracting valuable insights from data to drive informed decision-making and optimize business processes.",
                    List.of(
                            new TeamMember(employees.get("O3P4Q5R6S"), true, LocalDate.of(2020, 10, 25)),
                            new TeamMember(employees.get("T7U8V9W0X"), false, LocalDate.of(2020, 11, 11)),
                            new TeamMember(employees.get("Y1Z2A3B4C"), false, LocalDate.of(2021, 12, 3)),
                            new TeamMember(employees.get("U5V6W7X8Y"), false, LocalDate.of(2022, 1, 15))
                    )),
            "BVB6fAqiZ", new TeamDetails("BVB6fAqiZ", "Project Management", "Coordinating and overseeing the execution of IT projects, from inception to delivery, ensuring alignment with client objectives and timelines.",
                    List.of(
                            new TeamMember(employees.get("D5E6F7G8H"), true, LocalDate.of(2015, 1, 7)),
                            new TeamMember(employees.get("I9J0K1L2M"), false, LocalDate.of(2009, 2, 19)),
                            new TeamMember(employees.get("N3O4P5Q6R"), false, LocalDate.of(2010, 3, 8)),
                            new TeamMember(employees.get("Z3A4B5C6D"), false, LocalDate.of(2011, 4, 10))
                    )),
            "Irpn9NHPm", new TeamDetails("Irpn9NHPm", "Artificial Intelligence (AI) and Machine Learning", "Exploring and implementing AI-driven solutions to automate processes, improve efficiency, and drive innovation.",
                    List.of(
                            new TeamMember(employees.get("S7T8U9V0W"), true, LocalDate.of(2024, 4, 15)),
                            new TeamMember(employees.get("X1Y2Z3A4B"), false, LocalDate.of(2024, 5, 21)),
                            new TeamMember(employees.get("C5D6E7F8G"), false, LocalDate.of(2024, 6, 9)),
                            new TeamMember(employees.get("E7F8G9H0I"), false, LocalDate.of(2024, 6, 10))
                    )),
            "kKj6CzWw9", new TeamDetails("kKj6CzWw9", "Technical Support", "Providing timely assistance and troubleshooting for clients and internal users to resolve technical issues and ensure smooth operations.",
                    List.of(
                            new TeamMember(employees.get("H9I0J1K2L"), true, LocalDate.of(2003, 7, 12)),
                            new TeamMember(employees.get("M3N4O5P6Q"), false, LocalDate.of(2021, 8, 3)),
                            new TeamMember(employees.get("R7S8T9U0V"), false, LocalDate.of(2018, 9, 16)),
                            new TeamMember(employees.get("J5K6L7M8N"), false, LocalDate.of(2019, 3, 8))
                    )),
            "O1P2Q3R4S", new TeamDetails("O1P2Q3R4S", "Digital Marketing", "Developing and executing digital marketing strategies to enhance brand visibility and drive customer engagement.",
                    List.of(
                            new TeamMember(employees.get("O1P2Q3R4S"), true, LocalDate.of(2010, 4, 10)),
                            new TeamMember(employees.get("Y2Z3A4B5C"), false, LocalDate.of(2019, 5, 15)),
                            new TeamMember(employees.get("D7E8F9G0H"), false, LocalDate.of(2020, 6, 20))
                    )),
            "T5U6V7W8X", new TeamDetails("T5U6V7W8X", "PR", "Managing public relations and communications to maintain a positive brand image and foster relationships with stakeholders.",
                    List.of(
                            new TeamMember(employees.get("T5U6V7W8X"), true, LocalDate.of(2024, 7, 3))
                    ))
    );

    public @Nonnull List<@Nonnull Team> findTeams(String searchTerm) {
        if (searchTerm == null || searchTerm.isBlank()) {
            return teams;
        } else {
            return teams.stream().filter(team -> team.name().toLowerCase().contains(searchTerm.toLowerCase())).toList();
        }
    }

    public Optional<Team> findTeamByPublicId(@Nonnull String publicId) {
        return teams.stream().filter(team -> team.publicId().equals(publicId)).findFirst();
    }

    public Optional<TeamDetails> findTeamDetailsByPublicId(@Nonnull String publicId) {
        return Optional.ofNullable(teamDetails.get(publicId));
    }
}
