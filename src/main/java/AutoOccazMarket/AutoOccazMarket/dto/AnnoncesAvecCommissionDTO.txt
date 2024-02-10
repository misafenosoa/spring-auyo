package AutoOccazMarket.AutoOccazMarket.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import AutoOccazMarket.AutoOccazMarket.entities.AnnoncesAvecCommission;

@Component
public class AnnoncesAvecCommissionDTO {
    AnnoncesAvecCommission [] listAnnoncesAvecCommission ;    

    AnnoncesAvecCommission annonces ;

    String errors ;

    public void setAnnoncesAvecCommissionAsList(List<AnnoncesAvecCommission> list)
    {
        listAnnoncesAvecCommission = list.toArray(new AnnoncesAvecCommission[list.size()]);
    }

    public AnnoncesAvecCommission[] getListAnnoncesAvecCommission() {
        return listAnnoncesAvecCommission;
    }

    public void setListAnnoncesAvecCommission(AnnoncesAvecCommission[] listAnnoncesAvecCommission) {
        this.listAnnoncesAvecCommission = listAnnoncesAvecCommission;
    }

    public AnnoncesAvecCommission getAnnoncesAvecCommission() {
        return annonces;
    }

    public void setAnnoncesAvecCommission(AnnoncesAvecCommission annonces) {
        this.annonces = annonces;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    
}
