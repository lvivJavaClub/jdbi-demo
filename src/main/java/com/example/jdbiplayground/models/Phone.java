package com.example.jdbiplayground.models;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class Phone {

    private PhoneType phoneType;

    private String phoneNumer;

}
