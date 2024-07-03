package com.example.application.data;

import com.vaadin.hilla.Nonnull;

public record Employee(@Nonnull String publicId, String firstName, String lastName, String role, String phone,
                       String email, String timeZone, String picture) {
}
