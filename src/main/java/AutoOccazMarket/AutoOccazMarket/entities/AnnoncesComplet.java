package AutoOccazMarket.AutoOccazMarket.entities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

public class AnnoncesComplet {
    
    Integer id_annonce ;
    Integer etat_general ;
    String localisation ;
    Double prix ;
    Integer utilisateur_id_utilisateur ;
    String description;
    Double commission ;
    String nom_modele ;
    String carburant ;
    String categorie;
    String marque; 
    String etatValidation ;

    Timestamp datePoste ;
    String [] images;


    public static AnnoncesComplet [] select (Connection connection , Integer user) throws Exception{
        try{
            Statement statement = connection.createStatement();
            String requ = "with v_annonces as (select annonces.* , 0 as pourcentages from annonces), v_annonces2 as( select annonces.* , commission.pourcentages as pourcentages from annonces      join commission on annonces.id_annonce = commission.annonces_id_annonce ) ,    v_annonces3 as (select v_annonces.* from v_annonces union all  (select v_annonces2.* from v_annonces2) ) , v_annonces4 as (select id_annonce ,etat_general ,localisation ,prix , modeles_id_modeles, utilisateur_id_utilisateur    , etat_validation         , description , sum(pourcentages) as commission , date_poste from v_annonces3 group by  id_annonce ,etat_general, etat_validation ,localisation ,prix , modeles_id_modeles, utilisateur_id_utilisateur , description , date_poste) ,                 v_annonces5 as (select v_annonces4.* , modeles.* from v_annonces4 join modeles on modeles.id_modeles =v_annonces4.modeles_id_modeles) , v_annonces6 as (select v_annonces5.* , carburant from v_annonces5 join carburant on carburant.id_carburant = v_annonces5.carburant_id_carburant),v_annonces7 as (select v_annonces6.* ,categorie from v_annonces6 join categorie on categorie.id_categorie = v_annonces6.categorie_id_categorie) ,v_annonces8 as (select v_annonces7.* , marque from v_annonces7 join marque on marque.id_marque = v_annonces7.marque_id_marque)select  id_annonce ,etat_general ,localisation ,prix ,etat_validation , utilisateur_id_utilisateur ,description,commission ,nom_modele ,carburant ,categorie ,marque, date_poste from v_annonces8 "+
            "where utilisateur_id_utilisateur="+user ;
            System.out.println(requ);
            ResultSet re = statement.executeQuery(requ);
    
    
    
            ArrayList<AnnoncesComplet> l = new ArrayList<>();
            while (re.next()) {
                AnnoncesComplet v = new AnnoncesComplet();
                v.setCarburant(re.getString("carburant"));
                v.setCategorie(re.getString("categorie"));
                v.setCommission(re.getDouble("commission"));
                v.setDescription(re.getString("description"));
                v.setEtat_general(re.getInt("etat_general"));
                v.setId_annonce(re.getInt("id_annonce"));
                v.setLocalisation(re.getString("localisation"));
                v.setMarque(re.getString("marque"));
                v.setNom_modele(re.getString("nom_modele"));
                v.setPrix(re.getDouble("prix"));
                v.setUtilisateur_id_utilisateur(user);
                v.setDatePoste(re.getTimestamp("date_poste"));
                v.setEtatValidation(re.getString("etat_validation"));
                v.setImages(PhotoAnnonce.selectWhere( connection ,v.getId_annonce(),true));
                l.add(v);
            }
                return l.toArray(new AnnoncesComplet[l.size()]);
        }
        finally{
            connection.close();
        }

    }

    public Integer getId_annonce() {
        return id_annonce;
    }

    public void setId_annonce(Integer id_annonce) {
        this.id_annonce = id_annonce;
    }

    public Integer getEtat_general() {
        return etat_general;
    }

    public void setEtat_general(Integer etat_general) {
        this.etat_general = etat_general;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Integer getUtilisateur_id_utilisateur() {
        return utilisateur_id_utilisateur;
    }

    public void setUtilisateur_id_utilisateur(Integer utilisateur_id_utilisateur) {
        this.utilisateur_id_utilisateur = utilisateur_id_utilisateur;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    public String getNom_modele() {
        return nom_modele;
    }

    public void setNom_modele(String nom_modele) {
        this.nom_modele = nom_modele;
    }

    public String getCarburant() {
        return carburant;
    }

    public void setCarburant(String carburant) {
        this.carburant = carburant;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public Timestamp getDatePoste() {
        return datePoste;
    }

    public void setDatePoste(Timestamp datePoste) {
        this.datePoste = datePoste;
    }

    public String getEtatValidation() {
        return etatValidation;
    }

    public void setEtatValidation(String etatValidation) {
        this.etatValidation = etatValidation;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

}
