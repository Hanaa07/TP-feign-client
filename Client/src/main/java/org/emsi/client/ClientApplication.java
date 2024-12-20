package org.emsi.client;

import org.emsi.client.entities.Client;
import org.emsi.client.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class ClientApplication {

    @Bean
    CommandLineRunner initializerBaseH2(ClientRepository clientRepository) {
        return args -> {
            clientRepository.save(Client.builder().nom("Hanaa EL YANBOUAI").age(23f).build());
            clientRepository.save(Client.builder().nom("Amal RAMI").age(22f).build());
            clientRepository.save(Client.builder().nom("Samir SAFI").age(22f).build());
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

}
