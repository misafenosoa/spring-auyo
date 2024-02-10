package AutoOccazMarket.AutoOccazMarket.dto;

import java.util.List;

import AutoOccazMarket.AutoOccazMarket.entities.Annonces;

public class FavorisDTO {
    
    
    Annonces [] listAnnonces ;    

    Annonces annonces ;

    String errors ;


    public void setAnnoncesAsList(List<Annonces> list)
    {
        listAnnonces = list.toArray(new Annonces[list.size()]);
    }

    public Annonces[] getListAnnonces() {
        return listAnnonces;
    }

    public void setListAnnonces(Annonces[] listAnnonces) {
        this.listAnnonces = listAnnonces;
    }

    public Annonces getAnnonces() {
        return annonces;
    }

    public void setAnnonces(Annonces annonces) {
        this.annonces = annonces;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

 
}
