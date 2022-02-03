package com.example.jdbiplayground.models;

import java.time.Instant;

import lombok.Data;

@Data
public class Auditable {
    protected Instant createdAt;
    protected String createdBy;
    protected Instant updatedAt;
    protected String updatedBy;
}
