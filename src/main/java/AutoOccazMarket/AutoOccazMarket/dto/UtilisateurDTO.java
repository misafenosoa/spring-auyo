package AutoOccazMarket.AutoOccazMarket.dto;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import AutoOccazMarket.AutoOccazMarket.entities.Utilisateur;

@Component
public class UtilisateurDTO {
    Utilisateur[] listUtilisateur;

    Utilisateur utilisateur;

    String mdp;

    String errors;

    Map<String, String> tokenInformation;

    private int page;

    public Map<String, String> getTokenInformation() {
        return tokenInformation;
    }

    public void setTokenInformation(Map<String, String> tokenInformation) {
        this.tokenInformation = tokenInformation;
    }

    public void setUtilisateurAsList(List<Utilisateur> list) {
        listUtilisateur = list.toArray(new Utilisateur[list.size()]);
    }

    public Utilisateur[] getListUtilisateur() {
        return listUtilisateur;
    }

    public void setListUtilisateur(Utilisateur[] listUtilisateur) {
        this.listUtilisateur = listUtilisateur;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setPage(int totalPages) {
        this.page = totalPages;
    }

    public int getPage() {
        return page;
    }

}
