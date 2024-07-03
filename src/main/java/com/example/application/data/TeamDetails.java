package com.example.application.data;

import com.vaadin.hilla.Nonnull;

import java.util.List;

public record TeamDetails(@Nonnull String publicId, String name, String description,
                          @Nonnull List<@Nonnull TeamMember> members) {
}
