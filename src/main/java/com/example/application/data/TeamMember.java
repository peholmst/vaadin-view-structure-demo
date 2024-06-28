package com.example.application.data;

import java.time.LocalDate;

public record TeamMember(Employee employee, boolean manager, LocalDate joined) {
}
