package AutoOccazMarket.AutoOccazMarket.controllers;

import java.sql.DriverManager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import AutoOccazMarket.AutoOccazMarket.dto.FavorisDTO;
import AutoOccazMarket.AutoOccazMarket.entities.Annonces;
import AutoOccazMarket.AutoOccazMarket.entities.Favoris;


/**
 * FavorisController
 */
@RestController
public class FavorisController {

    @Value("${spring.datasource.url}")
    private String url ;

    @Value("${spring.datasource.username}")
    private String username ;

    @Value("${spring.datasource.password}")
    private String password ;


    @GetMapping("/annoncesFavoris/{user}")
    public FavorisDTO getFav(@PathVariable("user") Integer user ) {
        FavorisDTO f = new FavorisDTO();
        try {
            f.setAnnoncesAsList(Annonces.getFavAnnonces(DriverManager.getConnection(url, username, password), user," desc"));            
        } catch (Exception e) {
            f.setErrors(e.getMessage());
        }
        return f ;
    }
    
    

    @PutMapping("/annoncesFavoris/{user}/{annonces}")
    public FavorisDTO favoris(@PathVariable("user") Integer user , @PathVariable("annonces") Integer annonces) {
        FavorisDTO x = new FavorisDTO() ;
        try {
            Favoris.logicFavori(DriverManager.getConnection(url, username, password), user, annonces);
        } catch (Exception e) {
            x.setErrors(e.getMessage());
        }       
        return x;
    }

    // @GetMapping



    
}