package com.example.jdbiplayground.data;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.Consumer;

import com.example.jdbiplayground.models.Customer;
import com.example.jdbiplayground.models.Manager;
import org.jdbi.v3.core.result.LinkedHashMapRowReducer;
import org.jdbi.v3.core.result.RowView;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowReducer;

public interface ManagerRepository {

    @SqlUpdate("""
            INSERT INTO manager VALUES (DEFAULT, :firstName, :lastName, :bonus)
        """)
    @GetGeneratedKeys
    int createNewManager(String firstName, String lastName, Integer bonus);

    @SqlUpdate("""
                INSERT INTO manager VALUES (DEFAULT, :firstName, :lastName, :bonus)
        """)
    @GetGeneratedKeys
    int createNewManager(@BindBean Manager manager);

    @SqlQuery("SELECT * FROM manager")
    @RegisterBeanMapper(Manager.class)
    void findAll(Consumer<Manager> consumer);


    @SqlQuery("""
        SELECT m.id m_id, m.first_name m_first_name, m.last_name m_last_name,
               c.id c_id, c.first_name c_first_name, c.last_name c_last_name, c.manager_id c_manager_id
               FROM manager m JOIN customer c ON c.manager_id = m.id
               WHERE m.id = :id
        """)
    @RegisterBeanMapper(value = Manager.class, prefix = "m")
    @RegisterBeanMapper(value = Customer.class, prefix = "c")
    @UseRowReducer(ManagerCustomerReducer.class)
    Manager getManager(Integer id);

    class ManagerCustomerReducer implements LinkedHashMapRowReducer<Integer, Manager> {

        @Override
        public void accumulate(Map<Integer, Manager> container, RowView rowView) {
            var manager = container.computeIfAbsent(rowView.getColumn("m_id", Integer.class),
                id -> rowView.getRow(Manager.class)
            );
            if (manager.getCustomers() == null) {
                manager.setCustomers(new ArrayList<>());
            }
            manager.getCustomers().add(rowView.getRow(Customer.class));
        }
    }


}
