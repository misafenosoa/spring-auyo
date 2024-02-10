package AutoOccazMarket.AutoOccazMarket.entities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * ModelesStats
 */
public class ModelesStats {

    private String nomModeles;

    private Double prixVenteMoyenne ;

    private Integer nbAnnonces ;

    public static ModelesStats[] select(Connection connection) throws Exception{

        Statement statement = connection.createStatement();
        ResultSet rs= statement.executeQuery("with V_modeles_annonces as (select id_modeles , nom_modele , id_annonce , etat_general , etat_validation , localisation , modeles_id_modeles , utilisateur_id_utilisateur , prix from modeles join annonces  on modeles_id_modeles = id_modeles ) ,v_MODELES_ANNONCES_STATM as (select nom_modele , count(id_annonce) as nb_annonces ,avg(prix) as avg_prix from V_modeles_annonces group by nom_modele)select * from v_MODELES_ANNONCES_STATM ");
        ArrayList<ModelesStats> modelesStats = new ArrayList<>();
        while (rs.next()) {
            ModelesStats m = new ModelesStats();
            m.setNomModeles(rs.getString("nom_modele"));
            m.setPrixVenteMoyenne(rs.getDouble("avg_prix"));
            m.setNbAnnonces(rs.getInt("nb_annonces"));
            modelesStats.add(m);            
        }
        return modelesStats.toArray(new ModelesStats[modelesStats.size()]);
    }

    public String getNomModeles() {
        return nomModeles;
    }

    public void setNomModeles(String nomModeles) {
        this.nomModeles = nomModeles;
    }

    public Double getPrixVenteMoyenne() {
        return prixVenteMoyenne;
    }

    public void setPrixVenteMoyenne(Double prixVenteMoyenne) {
        this.prixVenteMoyenne = prixVenteMoyenne;
    }

    public Integer getNbAnnonces() {
        return nbAnnonces;
    }

    public void setNbAnnonces(Integer nbAnnonces) {
        this.nbAnnonces = nbAnnonces;
    }
    
}