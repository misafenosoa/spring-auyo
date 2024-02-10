package AutoOccazMarket.AutoOccazMarket.dto;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import AutoOccazMarket.AutoOccazMarket.entities.Categorie;

@Component
public class CategorieDTO {
    Categorie[] listCategorie;

    Categorie categorie;

    String errors;

    Map<String, String> tokenInformation;

    private int page;

    public Map<String, String> getTokenInformation() {
        return tokenInformation;
    }

    public void setTokenInformation(Map<String, String> tokenInformation) {
        this.tokenInformation = tokenInformation;
    }

    public void setCategorieAsList(List<Categorie> list) {
        listCategorie = list.toArray(new Categorie[list.size()]);
    }

    public Categorie[] getListCategorie() {
        return listCategorie;
    }

    public void setListCategorie(Categorie[] listCategorie) {
        this.listCategorie = listCategorie;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    public void setPage(int totalPages) {
        this.page = totalPages ;

    }

    public int getPage() {
        return page;
    }
}
