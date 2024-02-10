package AutoOccazMarket.AutoOccazMarket.dto;

import java.util.List;

import AutoOccazMarket.AutoOccazMarket.entities.Annonces;

public class AnnoncesFiltreDTO {
    Annonces [] listAnnonces ;    

    String[] modeles;

    String[] categories ;
    
    String [] marque ;

    String [] carburant ;

    String errors ;

    String order ;

    String key ;

    Integer userID ;

    Double one ;

    Double two ;

    public Double getOneValue(){
        return one; 
    }
    public Double getTwoValue(){
        return two ;
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


    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    public String[] getModeles() {
        if (modeles.length==0) {
            return null ;
        }
        return modeles;
    }

    public void setModeles(String[] modeles) {
        this.modeles = modeles;
    }

    public String[] getCategories() {
        if (categories.length == 0) {
            return null ;
        }
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public String[] getMarque() {
        if (marque.length==0) {
            return null ;
        }
        return marque;
    }

    public void setMarque(String[] marque) {
        this.marque = marque;
    }

    public String[] getCarburant() {
        if (carburant.length == 0) {
            return  null ;
        }
        return carburant;
    }

    public void setCarburant(String[] carburant) {
        this.carburant = carburant;
    }

    public String getOrder() {
        if (order==null) {
            return "desc";
        }
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getKey() {
        if (key==null) {
            return "";
        }
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String[] getModelesAs0() {
        if (modeles==null) {
            return new String[0];
        }
        return modeles;
    }

    public String[] getCategoriesAs0() {
        if (categories==null) {
            return new String[0];
        }
        return categories ;
    }

    public String[] getCarburantAs0() {
        if (carburant==null) {
            return new String[0];
        }
        return carburant ;
    }

    public String[] getMarqueAs0() {
        if (marque==null) {
            return new String[0];
        }
        return marque ;
    }

    public Double getOne() {
        if (one == null) {
            return 0.;
        }
        return one;
    }

    public void setOne(Double one) {
        this.one = one;
    }

    public Double getTwo() {
        if (two==null) {
            return 999999999999999999999.9;
        }
        return two;
    }

    public void setTwo(Double two) {
        this.two = two;
    }
}
