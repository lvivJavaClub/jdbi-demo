package com.example.jdbiplayground;

import java.util.List;
import java.util.Random;

import com.example.jdbiplayground.data.UuidArgumentFactory;
import com.example.jdbiplayground.models.Manager;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.jdbi.v3.core.Jdbi;

public class JdbiPlay {


    public static void main(String[] args) {
        var jdbi = Jdbi.create(dataSource());

        var manager = Manager.builder()
            .firstName("Adam")
            .lastName("Smith")
            .bonus(new Random().nextInt())
            .build();


        var id = jdbi.withHandle(handle -> {
            return handle.createUpdate(
                    """
                        INSERT INTO manager (id, first_name, last_name, bonus) 
                           VALUES (DEFAULT, :firstName, :lastName, :bonus)
                        """)
                .bindBean(manager)
                .registerArgument(new UuidArgumentFactory())
                .executeAndReturnGeneratedKeys()
                .mapTo(Integer.class)
                .first();
        });

        var wantBonus = List.of(
            1584024585,
            -766382349
        );

        var list = jdbi.withHandle(handle ->
            handle.select(
                    """
                        SELECT id 
                          FROM manager m
                          WHERE m.bonus in (<bonusList>)
                        """
                )
                .bindList("bonusList", wantBonus)
                .mapTo(Integer.class)
                .list()
        );

        System.out.println("list = " + list);

        System.out.println("id = " + id);

    }

    public static DataSource dataSource() {
        var hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        hikariDataSource.setUsername("postgres");
        hikariDataSource.setPassword("password");
        return hikariDataSource;
    }

}
