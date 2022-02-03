package com.example.jdbiplayground.models;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Email {

    private EmailType emailType;
    private String emailAddress;

}
