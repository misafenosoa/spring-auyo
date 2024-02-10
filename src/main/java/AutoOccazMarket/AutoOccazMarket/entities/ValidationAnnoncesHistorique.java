package AutoOccazMarket.AutoOccazMarket.entities;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class ValidationAnnoncesHistorique 
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "validationAnnoncesHistoriqueId")
    private Integer validationAnnoncesHistoriqueId ; 

    @ManyToOne(optional = false ,fetch = FetchType.EAGER)
    private Annonces annonces ;

    @Column(name = "date_validation")
    @CreationTimestamp
    private Timestamp dateValidation ;

    private String description ;

    @ManyToOne(optional = false , fetch = FetchType.EAGER)
    private Utilisateur validateur ;

    @Column(name = "etat_validation")
    private Integer etatValidation ;


    public Integer getValidationAnnoncesHistoriqueId() {
        return validationAnnoncesHistoriqueId;
    }

    public void setValidationAnnoncesHistoriqueId(Integer validationAnnoncesHistoriqueId) {
        this.validationAnnoncesHistoriqueId = validationAnnoncesHistoriqueId;
    }

    public Annonces getAnnonces() {
        return annonces;
    }

    public void setAnnonces(Annonces annonces) {
        this.annonces = annonces;
    }

    public Timestamp getDateValidation() {
        return dateValidation;
    }

    public void setDateValidation(Timestamp dateValidation) {
        this.dateValidation = dateValidation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Utilisateur getValidateur() {
        return validateur;
    }

    public void setValidateur(Utilisateur validateur) {
        this.validateur = validateur;
    }

    public Integer getEtatValidation() {
        return etatValidation;
    }

    public void setEtatValidation(Integer etatValidation) {
        this.etatValidation = etatValidation;
    }

}
