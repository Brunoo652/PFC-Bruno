package com.api.processor;

import com.api.model.ClubEntity;
import com.api.sanitize.SanitizeClub;
import com.api.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Component
public class DataClubesProcessor {
    private final ClubService clubService;

    @Autowired
    public DataClubesProcessor(ClubService clubService) {
        this.clubService = clubService;
    }

    public void processClubesFromFile(String filepath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            StringBuilder clubData = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                if (line.equals("=========================================")) {
                    //    System.out.println("salto de linea");
                    // Procesar y guardar el club cuando se encuentra una línea de separación
                    ClubEntity club = SanitizeClub.sanitize(clubData.toString());
                    if (club != null) {
                        clubService.saveClubToApi(club);
                        //      System.out.println("club guardado");
                    } else {
                        clubData = new StringBuilder(); // Reset clubData
                    }
                } else {
                    clubData.append(line).append("\n");
                }
            }
        }
    }
}
