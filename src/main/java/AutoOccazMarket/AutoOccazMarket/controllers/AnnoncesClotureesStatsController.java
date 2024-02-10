package AutoOccazMarket.AutoOccazMarket.controllers;

import org.springframework.web.bind.annotation.RestController;

import AutoOccazMarket.AutoOccazMarket.dto.AnnoncesClotureesStatsDTO;
import AutoOccazMarket.AutoOccazMarket.entities.AnnoncesClotureesStats;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@CrossOrigin(origins = "${frontend.url}")
public class AnnoncesClotureesStatsController {

    @Value("${spring.datasource.url}")
    private String url ;

    @Value("${spring.datasource.username}")
    private String username ;

    @Value("${spring.datasource.password}")
    private String password ;

    @GetMapping("/AnnoncesClotureesStats")
    public AnnoncesClotureesStatsDTO select() {
        AnnoncesClotureesStatsDTO c = new AnnoncesClotureesStatsDTO();
        try {
            Connection con = DriverManager.getConnection(url, username, password) ;
            c.setListAnnoncesClotureesStats(AnnoncesClotureesStats.select(con));            
        } catch (Exception e) {
            c.setErrors(e.getMessage());            
        }
        return c ;
    }
    
}
