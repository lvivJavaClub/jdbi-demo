package com.example.jdbiplayground;

import com.example.jdbiplayground.data.CustomerRepository;
import com.example.jdbiplayground.data.ManagerRepository;
import javax.sql.DataSource;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.spring5.JdbiFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JdbiPlaygroundApplication {

    @Bean
    JdbiFactoryBean jdbi(DataSource dataSource) {
        return new JdbiFactoryBean(dataSource)
            .setAutoInstallPlugins(true);
    }

    @Bean
    ManagerRepository managerRepository(Jdbi jdbi) {
        return jdbi.onDemand(ManagerRepository.class);
    }

    @Bean
    CustomerRepository customerRepository(Jdbi jdbi) {
        return jdbi.onDemand(CustomerRepository.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(JdbiPlaygroundApplication.class, args);
    }

}
