package com.example.application.data;

import com.vaadin.hilla.Nonnull;

public record Team(@Nonnull String publicId, String name, String description) {
}
