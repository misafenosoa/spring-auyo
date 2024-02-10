package AutoOccazMarket.AutoOccazMarket.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import AutoOccazMarket.AutoOccazMarket.entities.Annonces;
import AutoOccazMarket.AutoOccazMarket.entities.ValidationAnnoncesHistorique;
import AutoOccazMarket.AutoOccazMarket.repositories.AnnoncesRepository;
import AutoOccazMarket.AutoOccazMarket.repositories.ValidationAnnoncesHistoriqueRepository;
import jakarta.transaction.Transactional;

@Component
public class CRUDValidationAnnoncesHistorique {
    @Autowired
    ValidationAnnoncesHistoriqueRepository validationAnnoncesHistoriqueRepository;

    @Autowired
    AnnoncesRepository annoncesRepository ;    

    public List<ValidationAnnoncesHistorique> getValidationAnnoncesHistoriqueList() {
        return validationAnnoncesHistoriqueRepository.findAll();
    }

    public void deleteValidationAnnoncesHistoriqueByID(Integer id) {
        validationAnnoncesHistoriqueRepository.deleteById(id);

    }

    /**
     * Inserer validation ny objetif .
     * Tokony efa voa set dieny mialoha ny etat anle validation .
     * transactionel miaraka @ denomralisation
     * @param validationAnnoncesHistorique
     * @return
     */
    @Transactional
    public ValidationAnnoncesHistorique postValidationAnnoncesHistorique(ValidationAnnoncesHistorique validationAnnoncesHistorique) {

        Annonces annonces = validationAnnoncesHistorique.getAnnonces();
        annonces = annoncesRepository.findById(annonces.getIdAnnonce()).get();
        annonces.setEtatValidation(validationAnnoncesHistorique.getEtatValidation());
        annoncesRepository.save(annonces);

        return validationAnnoncesHistoriqueRepository.save(validationAnnoncesHistorique);
    }

    public ValidationAnnoncesHistorique getValidationAnnoncesHistoriqueByID(Integer id) {
        try {
            return validationAnnoncesHistoriqueRepository.findById(id).get();
        } catch (java.util.NoSuchElementException e) {
            return null;
        }
    }


}
