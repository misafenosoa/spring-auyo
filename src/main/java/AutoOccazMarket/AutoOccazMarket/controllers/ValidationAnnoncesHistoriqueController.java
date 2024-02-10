package AutoOccazMarket.AutoOccazMarket.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import AutoOccazMarket.AutoOccazMarket.dto.ValidationAnnoncesHistoriqueDTO;
import AutoOccazMarket.AutoOccazMarket.entities.ValidationAnnoncesHistorique;
import AutoOccazMarket.AutoOccazMarket.services.CRUDValidationAnnoncesHistorique;

@RestController
@CrossOrigin(origins = "${frontend.url}")
public class ValidationAnnoncesHistoriqueController {
    @Autowired
    private CRUDValidationAnnoncesHistorique crudValidationAnnoncesHistorique;

    @Autowired
    private ValidationAnnoncesHistoriqueDTO validationAnnoncesHistoriqueDTO;

    @GetMapping(path = "/validationAnnoncesHistoriques")
    public ValidationAnnoncesHistoriqueDTO getValidationAnnoncesHistoriques() {
        List<ValidationAnnoncesHistorique> validationAnnoncesHistorique = crudValidationAnnoncesHistorique.getValidationAnnoncesHistoriqueList();
        validationAnnoncesHistoriqueDTO.setValidationAnnoncesHistoriqueAsList(validationAnnoncesHistorique);
        return validationAnnoncesHistoriqueDTO;
    }

    @GetMapping(path = "/validationAnnoncesHistoriques/{id}")
    public ValidationAnnoncesHistoriqueDTO getValidationAnnoncesHistoriquesById(@PathVariable("id") final Integer id) {
        ValidationAnnoncesHistorique validationAnnoncesHistorique = crudValidationAnnoncesHistorique.getValidationAnnoncesHistoriqueByID(Integer.valueOf(id));
        validationAnnoncesHistoriqueDTO.setValidationAnnoncesHistorique(validationAnnoncesHistorique);

        return validationAnnoncesHistoriqueDTO;
    }

    @PostMapping(path = "/validationAnnoncesHistoriques")
    public ValidationAnnoncesHistoriqueDTO saveValidationAnnoncesHistorique(@RequestBody ValidationAnnoncesHistoriqueDTO validationAnnoncesHistoriqueDTO) {
        try {
            crudValidationAnnoncesHistorique.postValidationAnnoncesHistorique(validationAnnoncesHistoriqueDTO.getValidationAnnoncesHistorique());

        } catch (Exception e) {
            validationAnnoncesHistoriqueDTO.setErrors(e.getMessage());
        }

        return validationAnnoncesHistoriqueDTO;

    }


}
