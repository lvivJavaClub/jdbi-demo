package com.example.jdbiplayground.data;

import com.example.jdbiplayground.models.Customer;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface CustomerRepository {

    @SqlUpdate(
        """
            INSERT INTO CUSTOMER (id, first_name, last_name, manager_id) 
            VALUES (DEFAULT, :firstName, :lastName, :manager.id)
            """)
    void createCustomer(@BindBean Customer customer);

}
