package AutoOccazMarket.AutoOccazMarket.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import AutoOccazMarket.AutoOccazMarket.entities.Annonces;
import AutoOccazMarket.AutoOccazMarket.entities.Commission;
import AutoOccazMarket.AutoOccazMarket.entities.ValidationAnnoncesHistorique;
import AutoOccazMarket.AutoOccazMarket.repositories.AnnoncesRepository;
import AutoOccazMarket.AutoOccazMarket.repositories.CommissionRepository;
import AutoOccazMarket.AutoOccazMarket.repositories.ValidationAnnoncesHistoriqueRepository;
import jakarta.transaction.Transactional;

@Service
public class CRUDCommission {
    
    @Autowired
    CommissionRepository commissionRepository ;

    @Autowired 
    AnnoncesRepository annoncesRepository ;

    @Autowired 
    CRUDValidationAnnoncesHistorique validationAnnoncesHistoriqueCRUD ;    

    @Autowired
    ValidationAnnoncesHistoriqueRepository validationAnnoncesHistoriqueRepository;

    public List<Commission> getCommissionList() 
    {
        return commissionRepository.findAll();
    }

    public void deleteCommissionByID(Integer id)
    {
        commissionRepository.deleteById(id);

    }

    @Transactional
    public Commission postCommission(Commission commission)
    {
        Annonces a = annoncesRepository.findById(commission.getAnnonces().getIdAnnonce()).get();
        ValidationAnnoncesHistorique validationAnnoncesHistorique = new ValidationAnnoncesHistorique();
        validationAnnoncesHistorique.setAnnonces(a);
        validationAnnoncesHistorique.setDescription("insertion de commission");
        validationAnnoncesHistorique.setEtatValidation(20);
        validationAnnoncesHistorique.setValidateur(commission.getValidateur());

        a.setEtatValidation(20);

        annoncesRepository.save(a);

        validationAnnoncesHistoriqueRepository.save(validationAnnoncesHistorique);

        commissionRepository.save(commission);
        

        return commissionRepository.save(commission) ;
    }

    public Commission getCommissionByID (Integer id)
    {
        try 
        {
            return commissionRepository.findById(id).get();
        } 
        catch (java.util.NoSuchElementException e) 
        {
            return null ;
        }
    }

    public void updateCommission(Integer id , Commission commission)
    {
        Commission commissionToUpdate = getCommissionByID(id) ;

        if(commissionToUpdate == null) return;


        if (commission.getAnnonces()!=null) {
            commissionToUpdate.setAnnonces(commission.getAnnonces());                    
        }

        if (commission.getPourcentages()!=null) {
            commissionToUpdate.setPourcentages(commission.getPourcentages());                    
        }
        postCommission(commissionToUpdate) ;
    }


    
}