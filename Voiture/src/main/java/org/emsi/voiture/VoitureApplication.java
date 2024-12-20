package org.emsi.voiture;

import org.emsi.voiture.entities.Client;
import org.emsi.voiture.entities.Voiture;
import org.emsi.voiture.repositories.VoitureRepository;
import org.emsi.voiture.services.ClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class VoitureApplication {

	@Bean
	CommandLineRunner initializerBaseBD(VoitureRepository voitureRepository,
										ClientService clientService) {
		return args -> {
			Client c1 = clientService.clientById(2);
			Client c2 = clientService.clientById(1);
			System.out.println("**************************");
			System.out.println("Id est :" + c2.getId());
			System.out.println("Nom est :" + c2.getNom());
			System.out.println("**************************");
			System.out.println("**************************");
			System.out.println("Id est :" + c1.getId());
			System.out.println("Nom est :" + c1.getNom());
			System.out.println("Age est :" + c1.getAge());
			System.out.println("**************************");
			voitureRepository.save(new Voiture(null, "Toyota",
					"A 25 333", "Corolla", 1L, c2));
			voitureRepository.save(new Voiture(null, "Renault",
					"B 6 3456", "Megane", 1L, c2));
			voitureRepository.save(new Voiture(null, "Peugeot",
					"A 55 4444", "301", 2L, c1));
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(VoitureApplication.class, args);
	}

}
