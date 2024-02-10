package AutoOccazMarket.AutoOccazMarket.dto;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import AutoOccazMarket.AutoOccazMarket.entities.AnnoncesComplet;

@Component
public class AnnoncesCompletDTO {
    AnnoncesComplet [] listAnnonces ;    

    Integer userID ;

    String errors ;

    Map<String, String> tokenInformation;

    public Map<String, String> getTokenInformation() {
        return tokenInformation;
    }

    public void setTokenInformation(Map<String, String> tokenInformation) {
        this.tokenInformation = tokenInformation;
    }

    public void setAnnoncesCompletAsList(List<AnnoncesComplet> list)
    {
        listAnnonces = list.toArray(new AnnoncesComplet[list.size()]);
    }

    public AnnoncesComplet[] getListAnnonces() {
        return listAnnonces;
    }

    public void setListAnnonces(AnnoncesComplet[] listAnnonces) {
        this.listAnnonces = listAnnonces;
    }



    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }
}
