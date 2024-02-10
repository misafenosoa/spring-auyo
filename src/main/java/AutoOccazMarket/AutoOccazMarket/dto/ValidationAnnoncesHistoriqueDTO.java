package AutoOccazMarket.AutoOccazMarket.dto;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import AutoOccazMarket.AutoOccazMarket.entities.ValidationAnnoncesHistorique;

@Component
public class ValidationAnnoncesHistoriqueDTO {
    ValidationAnnoncesHistorique[] listValidationAnnoncesHistorique;

    ValidationAnnoncesHistorique validationAnnoncesHistorique;

    String errors;

    Map<String, String> tokenInformation;

    public Map<String, String> getTokenInformation() {
        return tokenInformation;
    }

    public void setTokenInformation(Map<String, String> tokenInformation) {
        this.tokenInformation = tokenInformation;
    }

    public void setValidationAnnoncesHistoriqueAsList(List<ValidationAnnoncesHistorique> list) {
        listValidationAnnoncesHistorique = list.toArray(new ValidationAnnoncesHistorique[list.size()]);
    }

    public ValidationAnnoncesHistorique[] getListValidationAnnoncesHistorique() {
        return listValidationAnnoncesHistorique;
    }

    public void setListValidationAnnoncesHistorique(ValidationAnnoncesHistorique[] listValidationAnnoncesHistorique) {
        this.listValidationAnnoncesHistorique = listValidationAnnoncesHistorique;
    }

    public ValidationAnnoncesHistorique getValidationAnnoncesHistorique() {
        return validationAnnoncesHistorique;
    }

    public void setValidationAnnoncesHistorique(ValidationAnnoncesHistorique validationAnnoncesHistorique) {
        this.validationAnnoncesHistorique = validationAnnoncesHistorique;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }    
}
