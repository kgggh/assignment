package com.triple.assignment.model.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserResponse {
    private UUID id;
    private String username;
}
