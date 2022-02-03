package com.example.jdbiplayground.models;

import java.time.LocalDate;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Customer extends Auditable {

    private Integer id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Email emails;
    private Map<PhoneType, Phone> phones;
    private Manager manager;

}
