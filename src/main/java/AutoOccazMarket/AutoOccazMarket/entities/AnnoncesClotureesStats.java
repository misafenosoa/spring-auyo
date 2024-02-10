package AutoOccazMarket.AutoOccazMarket.entities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AnnoncesClotureesStats {

    private String nomModele ;

    private Integer nbrePosteClotures ;

    private Double prixvendu;

    public static AnnoncesClotureesStats[] select(Connection connection ) throws Exception{
        Statement statement = connection.createStatement();
        ResultSet re = statement.executeQuery("WITH V_modeles_annonces AS (SELECT modeles.id_modeles, modeles.nom_modele, annonces.id_annonce, annonces.etat_general, annonces.etat_validation, annonces.localisation, annonces.modeles_id_modeles, annonces.utilisateur_id_utilisateur, annonces.prix FROM modeles JOIN annonces ON modeles.id_modeles = annonces.modeles_id_modeles WHERE annonces.etat_validation = 30), V_annonces_commission AS (SELECT V_modeles_annonces.*, commission.pourcentages FROM V_modeles_annonces JOIN commission ON V_modeles_annonces.id_annonce = commission.annonces_id_annonce) SELECT nom_modele, COUNT(*) AS nbreVoitureVendu, SUM(prix - (prix * pourcentages/100)) AS prixvendu FROM V_annonces_commission GROUP BY nom_modele");
        ArrayList<AnnoncesClotureesStats> l = new ArrayList<>();
        while (re.next()) {
            AnnoncesClotureesStats v = new AnnoncesClotureesStats();
            v.setNbrePosteClotures(re.getInt(2));
            v.setNomModele(re.getString(1));
            v.setPrixvendu(re.getDouble(3));            
            l.add(v);
        }
        return l.toArray(new AnnoncesClotureesStats[l.size()]);
    }    


    public String getNomModele() {
        return nomModele;
    }

    public void setNomModele(String nomModele) {
        this.nomModele = nomModele;
    }

    public Integer getNbrePosteClotures() {
        return nbrePosteClotures;
    }

    public void setNbrePosteClotures(Integer nbrePosteClotures) {
        this.nbrePosteClotures = nbrePosteClotures;
    }

    public Double getPrixvendu() {
        return prixvendu;
    }

    public void setPrixvendu(Double prixvendu) {
        this.prixvendu = prixvendu;
    }
}
