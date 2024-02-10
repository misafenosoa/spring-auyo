package AutoOccazMarket.AutoOccazMarket.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Modeles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_modeles")
    private Integer idModeles;
    private String nomModele;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Categorie categorie;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Marque marque;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Carburant carburant;

    public Integer getIdModeles() {
        return idModeles;
    }

    public void setIdModeles(Integer idModeles) {
        this.idModeles = idModeles;
    }

    public String getNomModele() {
        return nomModele;
    }

    public void setNomModele(String nomModele) {
        this.nomModele = nomModele;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public Carburant getCarburant() {
        return carburant;
    }

    public void setCarburant(Carburant carburant) {
        this.carburant = carburant;
    }
}
