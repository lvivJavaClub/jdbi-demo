package com.example.jdbiplayground;

import com.example.jdbiplayground.data.CustomerRepository;
import com.example.jdbiplayground.data.ManagerRepository;
import com.example.jdbiplayground.models.Customer;
import com.example.jdbiplayground.models.Manager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JdbiPlaygroundApplicationTests {

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void contextLoads() {

        var newManagerId = managerRepository.createNewManager("Captain", "AMerica", 100);

        Manager manager = Manager.builder()
            .firstName("Tomas")
            .lastName("First")
            .bonus(111)
            .build();
        manager.setId(managerRepository.createNewManager(manager));

        var customer1 = Customer.builder().firstName("C1").lastName("L1").manager(manager).build();
        var customer2 = Customer.builder().firstName("C1").lastName("L1").manager(manager).build();
        var customer3 = Customer.builder().firstName("C1").lastName("L1").manager(manager).build();
        var customer4 = Customer.builder().firstName("C1").lastName("L1").manager(manager).build();

        customerRepository.createCustomer(customer1);
        customerRepository.createCustomer(customer2);
        customerRepository.createCustomer(customer3);
        customerRepository.createCustomer(customer4);

        var manager1 = managerRepository.getManager(manager.getId());
        System.out.println("manager1 = " + manager1);


        System.out.println("newManagerId = " + newManagerId);




        managerRepository.findAll(System.out::println);
    }

}
