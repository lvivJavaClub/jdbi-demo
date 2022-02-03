package com.example.jdbiplayground.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manager extends Auditable {

    private Integer id;

    private String firstName;

    private String lastName;

    private Integer bonus;

    private List<Customer> customers;


}