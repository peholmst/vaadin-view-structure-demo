package com.example.application.data;

import java.util.List;

public record TeamDetails(String publicId, String name, String description, List<TeamMember> members) {
}
