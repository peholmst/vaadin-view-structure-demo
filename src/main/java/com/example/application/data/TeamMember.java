package com.example.application.data;

import com.vaadin.hilla.Nonnull;

import java.time.LocalDate;

public record TeamMember(@Nonnull Employee employee, boolean manager, @Nonnull LocalDate joined) {
}
