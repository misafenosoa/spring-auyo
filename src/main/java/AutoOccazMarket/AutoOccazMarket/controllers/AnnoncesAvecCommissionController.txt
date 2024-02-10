package AutoOccazMarket.AutoOccazMarket.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import AutoOccazMarket.AutoOccazMarket.dto.AnnoncesAvecCommissionDTO;
import AutoOccazMarket.AutoOccazMarket.entities.AnnoncesAvecCommission;
import AutoOccazMarket.AutoOccazMarket.services.CRUDAnnoncesAvecCommission;


@RestController
public class AnnoncesAvecCommissionController 
{

    @Autowired
    private CRUDAnnoncesAvecCommission crudAnnoncesAvecCommission ;

    @Autowired
    private AnnoncesAvecCommissionDTO annoncesDTO ;


    @GetMapping(path = "/annoncesAvecCommission")
    public AnnoncesAvecCommissionDTO getAnnoncesAvecCommissions()
    {
        List<AnnoncesAvecCommission> annonces = crudAnnoncesAvecCommission.getAnnoncesAvecCommissionList() ;
        annoncesDTO.setAnnoncesAvecCommissionAsList(annonces);
        return annoncesDTO ;
    }

    @GetMapping(path = "/annoncesAvecCommission/{id}")
    public AnnoncesAvecCommissionDTO getAnnoncesAvecCommissionsById(@PathVariable("id") final Integer id)
    {
        AnnoncesAvecCommission annonces = crudAnnoncesAvecCommission.getAnnoncesAvecCommissionByID(Integer.valueOf(id)) ;
        annoncesDTO.setAnnoncesAvecCommission(annonces) ;

        return annoncesDTO ;
    }

    @PostMapping(path = "/annoncesAvecCommission")
    public AnnoncesAvecCommissionDTO saveAnnoncesAvecCommission(@RequestBody AnnoncesAvecCommissionDTO annoncesDTO)
    {
        try {
            crudAnnoncesAvecCommission.postAnnoncesAvecCommission(annoncesDTO.getAnnoncesAvecCommission());
            
        } catch (Exception e) {
            annoncesDTO.setErrors(e.getMessage());
        }

        return annoncesDTO;

    }
        


    @DeleteMapping(path = "/annoncesAvecCommission/{id}")
    public void deleteAnnoncesAvecCommission(@PathVariable("id") final Integer id)
    {
        crudAnnoncesAvecCommission.deleteAnnoncesAvecCommissionByID(id);
    }    
}
