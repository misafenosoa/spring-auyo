package AutoOccazMarket.AutoOccazMarket.controllers;

import java.sql.DriverManager;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import AutoOccazMarket.AutoOccazMarket.dto.AnnoncesCompletDTO;
import AutoOccazMarket.AutoOccazMarket.dto.AnnoncesDTO;
import AutoOccazMarket.AutoOccazMarket.dto.AnnoncesFiltreDTO;
import AutoOccazMarket.AutoOccazMarket.entities.Annonces;
import AutoOccazMarket.AutoOccazMarket.entities.AnnoncesComplet;
import AutoOccazMarket.AutoOccazMarket.services.CRUDAnnonces;






@RestController
@CrossOrigin(origins = "${frontend.url}")

public class AnnoncesController 
{

    @Autowired
    private CRUDAnnonces crudAnnonces ;

    @Autowired
    private AnnoncesDTO annoncesDTO ;

    @Value("${spring.datasource.url}")
    private String url ;

    @Value("${spring.datasource.username}")
    private String username ;

    @Value("${spring.datasource.password}")
    private String password ;




    @GetMapping(path = "/annoncesNonPostees")
    public AnnoncesDTO getAnnoncesNonPostees()
    {
        List<Annonces> annonces = crudAnnonces.getAnnoncesNonPostees() ;
        annoncesDTO.setAnnoncesAsList(annonces);
        return annoncesDTO ;
    }    

    @PostMapping("/annoncesfiltre")
    public AnnoncesFiltreDTO filtre(@RequestBody AnnoncesFiltreDTO entity) {
        try {
            List<Annonces> annonces = crudAnnonces.selectWhere(entity,DriverManager.getConnection(url, username, password));
            entity.setAnnoncesAsList(annonces);
        } catch (Exception e) {
            entity.setErrors(e.getMessage());
        }
        return entity;
    }
    


    @GetMapping(path = "/annonces")
    public AnnoncesDTO getAnnoncess()
    {
        List<Annonces> annonces = crudAnnonces.getAnnoncesList() ;
        annoncesDTO.setAnnoncesAsList(annonces);
        return annoncesDTO ;
    }

    @GetMapping(path = "/annoncesAccueil")
    public AnnoncesDTO getAnnoncessAccueil()
    {
        try {
            List<Annonces> annonces = crudAnnonces.getAnnoncesPostees(DriverManager.getConnection(url, username, password)) ;
            annoncesDTO.setAnnoncesAsList(annonces);
        } catch (Exception e) {
            annoncesDTO.setErrors(e.getMessage());
        }        
        return annoncesDTO ;
    }

    @GetMapping("/annoncesAccueilByUser/{user}")
    public AnnoncesDTO getannoncesAccueilByUser(@PathVariable("user") Integer user) {
        AnnoncesDTO x = new AnnoncesDTO() ;
        try {
            x.setAnnoncesAsList(Annonces.getAnnoncesAccueil(DriverManager.getConnection(url, username, password), user," desc"));            
        } catch (Exception e) {
            x.setErrors(e.getMessage());
        }
        return x;
    }

    @PostMapping("/annoncesAccueilByUserFiltre")
    public AnnoncesDTO annoncesAccueilByUserFiltre(@RequestBody AnnoncesFiltreDTO annoncesFiltreDTO) {
        AnnoncesDTO x = new AnnoncesDTO() ;
        try {
            x.setAnnoncesAsList(Annonces.getAnnoncesAccueilFiltreBy(DriverManager.getConnection(url, username, password), annoncesFiltreDTO.getUserID(), annoncesFiltreDTO.getModelesAs0(),annoncesFiltreDTO.getCategoriesAs0(), annoncesFiltreDTO.getCarburantAs0(), annoncesFiltreDTO.getMarqueAs0(), annoncesFiltreDTO.getOneValue() ,annoncesFiltreDTO.getTwoValue(), annoncesFiltreDTO.getOrder()));            
        } catch (Exception e) {
            x.setErrors(e.getMessage());
        }
        return x ;
    }

    @PostMapping("/annoncesAccueilByUserKeywords")
    public AnnoncesDTO postMethodName(@RequestBody AnnoncesFiltreDTO entity) {
        AnnoncesDTO x = new AnnoncesDTO() ;
        try {
            x.setAnnoncesAsList(Annonces.getAnnoncesAccueilFiltreByKeywords(DriverManager.getConnection(url, username, password),entity.getKey(), entity.getUserID() , entity.getOrder() ));            
        } catch (Exception e) {
            x.setErrors(e.getMessage());
        }
        return x ;
    }
    
    

    
    

    @PostMapping("/annoncesAccueilKeyWord")
    public AnnoncesDTO getMethodName(@RequestBody AnnoncesFiltreDTO annoncesFiltreDTO) {
        AnnoncesDTO c = new AnnoncesDTO() ;
        try {
            c.setAnnoncesAsList(crudAnnonces.searchBy(annoncesFiltreDTO,DriverManager.getConnection(url, username, password)));            
        } catch (Exception e) {
            c.setErrors(e.getMessage());
        }
        return c;
    }
    

    @GetMapping(path = "/annonces/{id}")
    public AnnoncesDTO getAnnoncessById(@PathVariable("id") final Integer id)
    {
        Annonces annonces = crudAnnonces.getAnnoncesByID(Integer.valueOf(id)) ;
        annoncesDTO.setAnnonces(annonces) ;

        return annoncesDTO ;
    }

    @PostMapping(path = "/annonces")
    public AnnoncesDTO saveAnnonces(@RequestBody AnnoncesDTO annoncesDTO)
    {
        try {
            crudAnnonces.postAnnonces(annoncesDTO.getAnnonces());
            
        } catch (Exception e) {
            annoncesDTO.setErrors(e.getMessage());
        }

        return annoncesDTO;

    }
        

    @PutMapping(path ="/annonces/{id}")
    public AnnoncesDTO updateAnnonces(@PathVariable("id") final Integer id , @RequestBody AnnoncesDTO annoncesDTO)
    {
        try {
            crudAnnonces.updateAnnonces(id, annoncesDTO.getAnnonces());
            
        } catch (Exception e) {
            annoncesDTO.setErrors(e.getMessage());
        }

        return annoncesDTO;
    }

    @DeleteMapping(path = "/annonces/{id}")
    public void deleteAnnonces(@PathVariable("id") final Integer id)
    {
        crudAnnonces.deleteAnnoncesByID(id);
    }    

    @GetMapping("/annoncesHistorique/{id}")
    public AnnoncesCompletDTO getHistorique(@PathVariable("id") final Integer id) {
        AnnoncesCompletDTO param = new AnnoncesCompletDTO();
        try {
            param.setListAnnonces(AnnoncesComplet.select(DriverManager.getConnection(url, username, password),id));

        } catch (Exception e) {
            param.setErrors(e.getMessage());
        }
        return param;
    }
    
}
