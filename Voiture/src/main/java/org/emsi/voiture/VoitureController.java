package org.emsi.voiture;

import org.emsi.voiture.entities.Client;
import org.emsi.voiture.entities.Voiture;
import org.emsi.voiture.repositories.VoitureRepository;
import org.emsi.voiture.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/voitures")
public class VoitureController {

    @Autowired
    VoitureRepository voitureRepository;

    @Autowired
    ClientService clientService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        try {
            List<Voiture> voitures = voitureRepository.findAll();
            for (Voiture voiture : voitures) {
                if (voiture.getId_client() != null) {
                    try {
                        Client client = clientService.clientById(voiture.getId_client().intValue());
                        voiture.setClient(client);
                    } catch (Exception e) {
                        voiture.setClient(null);
                    }
                }
            }
            return ResponseEntity.ok(voitures);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching voitures: " + e.getMessage());
        }
    }
}

