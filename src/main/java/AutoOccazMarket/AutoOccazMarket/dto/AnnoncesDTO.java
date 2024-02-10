package AutoOccazMarket.AutoOccazMarket.dto;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import AutoOccazMarket.AutoOccazMarket.entities.Annonces;

@Component
public class AnnoncesDTO {
    Annonces [] listAnnonces ;    

    Annonces annonces ;

    String errors ;

    String [] photosAnnonces ;

    Map<String, String> tokenInformation;

    public Map<String, String> getTokenInformation() {
        return tokenInformation;
    }

    public void setTokenInformation(Map<String, String> tokenInformation) {
        this.tokenInformation = tokenInformation;
    }

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

    public String[] getPhotosAnnonces() {
        return photosAnnonces;
    }

    public void setPhotosAnnonces(String[] photosAnnonces) {
        this.photosAnnonces = photosAnnonces;
    }

    
}
