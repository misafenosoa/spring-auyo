package AutoOccazMarket.AutoOccazMarket.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class AnnoncesAvecCommission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAnnoncesAvecCommission;

    @OneToOne(optional = false, fetch = FetchType.EAGER)
    private Commission commission;

    @OneToOne(optional = false , fetch = FetchType.EAGER)
    private Annonces annonces ;

    public Integer getIdAnnoncesAvecCommission() {
        return idAnnoncesAvecCommission;
    }

    public void setIdAnnoncesAvecCommission(Integer idAnnoncesAvecCommission) {
        this.idAnnoncesAvecCommission = idAnnoncesAvecCommission;
    }

    public Commission getCommission() {
        return commission;
    }

    public void setCommission(Commission commission) {
        this.commission = commission;
    }

    public Annonces getAnnonces() {
        return annonces;
    }

    public void setAnnonces(Annonces annonces) {
        this.annonces = annonces;
    }
}
