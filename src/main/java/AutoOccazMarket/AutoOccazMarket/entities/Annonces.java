package AutoOccazMarket.AutoOccazMarket.entities;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@Entity

public class Annonces {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAnnonce ;

    @ManyToOne(fetch = FetchType.EAGER ,optional = false)
    private Modeles modeles ;

    @ManyToOne(fetch = FetchType.EAGER ,optional = false)
    private Utilisateur utilisateur;

    @Column(name = "etat_general")
    private Integer etatGeneral;

    private String localisation ;

    @Column(name = "etat_validation",nullable = false)
    private Integer etatValidation;

    @OneToMany    
    private List<PhotoAnnonce> photoAnnonces ;

    @OneToMany( fetch = FetchType.EAGER  , mappedBy = "annonces")
    @JsonIgnore
    private List<ValidationAnnoncesHistorique> validationAnnoncesHistoriques ;

    private Double prix ;

    private String description ;

    @CreationTimestamp
    private Timestamp datePoste ;

    @Transient
    private Integer status ;

    @Transient
    private String [] annoncesPhotos;



    public static ArrayList<Annonces> getAnnoncesAccueil(Connection con, Integer userID , String order) throws Exception {
        ArrayList<Annonces> annoncesList = new ArrayList<>();

        // SQL query with CTEs
        String sql = "WITH fav AS (SELECT id_annonce, 0 AS status FROM annonces WHERE etat_validation = 20), " +
                     "userfav AS (SELECT favoris.id_annonce, status FROM favoris JOIN annonces ON etat_validation = 20 and favoris.id_annonce = annonces.id_annonce WHERE iduser = ?), " +
                     "favunion AS (SELECT * FROM fav UNION ALL SELECT * FROM userfav), " +
                     "favok AS (SELECT id_annonce, SUM(status) AS status FROM favunion GROUP BY id_annonce), " +
                     "v_annonces AS (SELECT a.id_annonce, a.etat_general, a.etat_validation, a.localisation, a.prix, a.modeles_id_modeles, " +
                                    "a.utilisateur_id_utilisateur, a.description, a.annonces_order, a.date_poste, m.nom_modele, " +
                                    "m.carburant_id_carburant, m.categorie_id_categorie, m.marque_id_marque, c.categorie, cb.carburant, " +
                                    "ma.marque, u.birthday, u.hierarchie, u.mail, u.nom, u.prenom " +
                                    "FROM annonces a " +
                                    "JOIN modeles m ON a.modeles_id_modeles = m.id_modeles " +
                                    "JOIN categorie c ON m.categorie_id_categorie = c.id_categorie " +
                                    "JOIN carburant cb ON m.carburant_id_carburant = cb.id_carburant " +
                                    "JOIN marque ma ON m.marque_id_marque = ma.id_marque " +
                                    "JOIN utilisateur u ON a.utilisateur_id_utilisateur = u.id_utilisateur), " +
                     "v_annonces2 AS (SELECT * FROM v_annonces WHERE etat_validation = 20) " +
                     "SELECT v_annonces2.*, status FROM v_annonces2 JOIN favok ON v_annonces2.id_annonce = favok.id_annonce order by date_poste "+order;

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Create Annonces object and populate it with data from ResultSet
                Annonces annonce = new Annonces();
                // Populate Annonces fields from ResultSet, e.g., rs.getInt("id_annonce"), rs.getString("etat_general"), etc.
                annonce.setIdAnnonce(rs.getInt("id_annonce"));
                annonce.setEtatGeneral(rs.getInt("etat_general"));
                annonce.setLocalisation(rs.getString("localisation"));
                annonce.setPrix(rs.getDouble("prix"));
                annonce.setDescription(rs.getString("description"));
                annonce.setDatePoste(rs.getTimestamp("date_poste"));
                annonce.setStatus(rs.getInt("status"));
                annonce.setAnnoncesPhotos(PhotoAnnonce.selectWhere(con, annonce.getIdAnnonce(), true));
                Carburant c = new Carburant();
                c.setCarburant(rs.getString("carburant"));
                Categorie cc = new Categorie() ;
                cc.setCategorie(rs.getString("categorie"));                

                Marque m = new Marque(); 
                m.setMarque(rs.getString("marque"));

                Modeles modeles = new Modeles() ;
                modeles.setCarburant(c);
                modeles.setCategorie(cc);
                modeles.setMarque(m);
                modeles.setNomModele(rs.getString("nom_modele"));

                annonce.setModeles(modeles);

                Utilisateur u = new Utilisateur() ;
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                // ...
                annonce.setUtilisateur(u);
                annoncesList.add(annonce);
            }
        }
        finally{
            con.close();
        }
        return annoncesList;
    }

    public static ArrayList<Annonces> getAnnoncesAccueilFiltreBy(Connection con, Integer userID,
                    String [] modeless , String [] categorie , String[] carburant , String [] marque , Double one  , Double two , String order) throws Exception {
        ArrayList<Annonces> annoncesList = new ArrayList<>();

        // SQL query with CTEs
        String sql = "WITH fav AS (SELECT id_annonce, 0 AS status FROM annonces WHERE etat_validation = 20), " +
                     "userfav AS (SELECT favoris.id_annonce, status FROM favoris JOIN annonces ON etat_validation = 20 and favoris.id_annonce = annonces.id_annonce WHERE iduser = ?), " +
                     "favunion AS (SELECT * FROM fav UNION ALL SELECT * FROM userfav), " +
                     "favok AS (SELECT id_annonce, SUM(status) AS status FROM favunion GROUP BY id_annonce), " +
                     "v_annonces AS (SELECT a.id_annonce, a.etat_general, a.etat_validation, a.localisation, a.prix, a.modeles_id_modeles, " +
                                    "a.utilisateur_id_utilisateur, a.description, a.annonces_order, a.date_poste, m.nom_modele, " +
                                    "m.carburant_id_carburant, m.categorie_id_categorie, m.marque_id_marque, c.categorie, cb.carburant, " +
                                    "ma.marque, u.birthday, u.hierarchie, u.mail, u.nom, u.prenom " +
                                    "FROM annonces a " +
                                    "JOIN modeles m ON a.modeles_id_modeles = m.id_modeles " +
                                    "JOIN categorie c ON m.categorie_id_categorie = c.id_categorie " +
                                    "JOIN carburant cb ON m.carburant_id_carburant = cb.id_carburant " +
                                    "JOIN marque ma ON m.marque_id_marque = ma.id_marque " +
                                    "JOIN utilisateur u ON a.utilisateur_id_utilisateur = u.id_utilisateur), " +
                     "v_annonces2 AS (SELECT * FROM v_annonces WHERE etat_validation = 20), " +
                     "v_annonces3 as (SELECT v_annonces2.*, status FROM v_annonces2 JOIN favok ON v_annonces2.id_annonce = favok.id_annonce order by date_poste "+order+") "+
                     "select * from v_annonces3 "+ joinWhere(modeless, marque, categorie, carburant ,one ,two);

        
        System.out.println(sql);


        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Create Annonces object and populate it with data from ResultSet
                Annonces annonce = new Annonces();
                // Populate Annonces fields from ResultSet, e.g., rs.getInt("id_annonce"), rs.getString("etat_general"), etc.
                annonce.setIdAnnonce(rs.getInt("id_annonce"));
                annonce.setEtatGeneral(rs.getInt("etat_general"));
                annonce.setLocalisation(rs.getString("localisation"));
                annonce.setPrix(rs.getDouble("prix"));
                annonce.setDescription(rs.getString("description"));
                annonce.setDatePoste(rs.getTimestamp("date_poste"));
                annonce.setStatus(rs.getInt("status"));
                annonce.setAnnoncesPhotos(PhotoAnnonce.selectWhere(con, annonce.getIdAnnonce(), true));
                Carburant c = new Carburant();
                c.setCarburant(rs.getString("carburant"));
                Categorie cc = new Categorie() ;
                cc.setCategorie(rs.getString("categorie"));                

                Marque m = new Marque(); 
                m.setMarque(rs.getString("marque"));

                Modeles modeles = new Modeles() ;
                modeles.setCarburant(c);
                modeles.setCategorie(cc);
                modeles.setMarque(m);
                modeles.setNomModele(rs.getString("nom_modele"));

                annonce.setModeles(modeles);

                Utilisateur u = new Utilisateur() ;
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                // ...
                annonce.setUtilisateur(u);
                annoncesList.add(annonce);
            }
        }
        finally{
            con.close();
        }

        return annoncesList;
    }

    private static String joinWhere(String[] modeles , String[] marque, String[] categorie , String[] carburant ,Double one ,Double two){
        String modelesWhere = modelesWhere(modeles, " OR ");
        String categorieWhere = categorieWhere(categorie, " OR ");
        String carburantWhere = carburantWhere(carburant, " OR ");
        String marqueWhere = marqueWhere(marque, " OR ");
        String prixWhere = prixWhere(one ,two ) ;

        String sql = modelesWhere+" " + categorieWhere +" "+carburantWhere+" "+marqueWhere +" "+prixWhere ;

        if (sql.isBlank()) {
            return "";
        }
        sql= sql.trim();
        if (sql.endsWith("OR")) {
            sql= sql.substring(0,sql.lastIndexOf("OR"));
        }
        return "where "+sql ;
        
        
    }

    private static String prixWhere(Double one, Double two) {
        if (one != null && two !=null) {
            return one+"<= prix and prix<"+two ;
        }
        return "";
    }

    private static String marqueWhere(String[] marque, String concat) {
        if (marque.length==0) {
            return "";            
        }
        String sql = "";
        for (String string : marque) {
            sql = sql +" marque ilike " +"'" +string+"' "+concat;
        }
        System.out.println(sql);
        return sql;
    }

    private static String carburantWhere(String[] carburant, String concat) {
        if (carburant.length==0) {
            return "";            
        }
        String sql = "";
        for (String string : carburant) {
            sql = sql+" carburant ilike " +"'" +string+"' "+concat;
        }
        System.out.println(sql);
        return sql;
    }

    private static String categorieWhere(String[] categorie, String concat) {
        if (categorie.length==0) {
            return "";            
        }
        String sql = "";
        for (String string : categorie) {
            sql = sql+" categorie ilike " + "'"+string+"' "+concat;
        }
        System.out.println(sql);
        return sql;
    }

    private static String modelesWhere(String[] modeles2, String concat) {
        if (modeles2.length==0) {
            return "";            
        }
        String sql = "";
        for (String string : modeles2) {
            sql = sql+" nom_modele ilike " +"'" +string+"' "+concat;
        }
        return sql;
    }

    public static List<Annonces> getFavAnnonces(Connection con, Integer userID , String order) throws Exception {
        ArrayList<Annonces> annoncesList = new ArrayList<>();

        String sql = "WITH fav AS (SELECT id_annonce, 0 AS status FROM annonces WHERE etat_validation = 20), " +
                     "userfav AS (SELECT favoris.id_annonce, status FROM favoris JOIN annonces ON etat_validation = 20 and favoris.id_annonce = annonces.id_annonce WHERE iduser = ?), " +
                     "favunion AS (SELECT * FROM fav UNION ALL SELECT * FROM userfav), " +
                     "favok AS (SELECT id_annonce, SUM(status) AS status FROM favunion GROUP BY id_annonce), " +
                     "v_annonces AS (SELECT a.id_annonce, a.etat_general, a.etat_validation, a.localisation, a.prix, a.modeles_id_modeles, " +
                                    "a.utilisateur_id_utilisateur, a.description, a.annonces_order, a.date_poste, m.nom_modele, " +
                                    "m.carburant_id_carburant, m.categorie_id_categorie, m.marque_id_marque, c.categorie, cb.carburant, " +
                                    "ma.marque, u.birthday, u.hierarchie, u.mail, u.nom, u.prenom " +
                                    "FROM annonces a " +
                                    "JOIN modeles m ON a.modeles_id_modeles = m.id_modeles " +
                                    "JOIN categorie c ON m.categorie_id_categorie = c.id_categorie " +
                                    "JOIN carburant cb ON m.carburant_id_carburant = cb.id_carburant " +
                                    "JOIN marque ma ON m.marque_id_marque = ma.id_marque " +
                                    "JOIN utilisateur u ON a.utilisateur_id_utilisateur = u.id_utilisateur), " +
                     "v_annonces2 AS (SELECT * FROM v_annonces WHERE etat_validation = 20) " +
                     "SELECT v_annonces2.*, status FROM v_annonces2 JOIN favok ON v_annonces2.id_annonce = favok.id_annonce where status =1 order by date_poste "+order;

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Create Annonces object and populate it with data from ResultSet
                Annonces annonce = new Annonces();
                // Populate Annonces fields from ResultSet, e.g., rs.getInt("id_annonce"), rs.getString("etat_general"), etc.
                annonce.setIdAnnonce(rs.getInt("id_annonce"));
                annonce.setEtatGeneral(rs.getInt("etat_general"));
                annonce.setLocalisation(rs.getString("localisation"));
                annonce.setPrix(rs.getDouble("prix"));
                annonce.setDescription(rs.getString("description"));
                annonce.setDatePoste(rs.getTimestamp("date_poste"));
                annonce.setStatus(rs.getInt("status"));
                annonce.setAnnoncesPhotos(PhotoAnnonce.selectWhere(con, annonce.getIdAnnonce(), true));

                Carburant c = new Carburant();
                c.setCarburant(rs.getString("carburant"));
                Categorie cc = new Categorie() ;
                cc.setCategorie(rs.getString("categorie"));                

                Marque m = new Marque(); 
                m.setMarque(rs.getString("marque"));

                Modeles modeles = new Modeles() ;
                modeles.setCarburant(c);
                modeles.setCategorie(cc);
                modeles.setMarque(m);
                modeles.setNomModele(rs.getString("nom_modele"));

                annonce.setModeles(modeles);

                Utilisateur u = new Utilisateur() ;
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                // ...
                annonce.setUtilisateur(u);
                annoncesList.add(annonce);
            }
        }
        finally{
            con.close();
        }

        return annoncesList;

    }
    public Timestamp getDatePoste() {
        return datePoste;
    }

    public void setDatePoste(Timestamp datePoste) {
        this.datePoste = datePoste;
    }

    public List<PhotoAnnonce> getPhotoAnnonces() {
        return photoAnnonces;
    }

    public void setPhotoAnnonces(List<PhotoAnnonce> photoAnnonces) {
        this.photoAnnonces = photoAnnonces;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Integer getIdAnnonce() {
        return idAnnonce;
    }

    public void setIdAnnonce(Integer idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

    public Modeles getModeles() {
        return modeles;
    }

    public void setModeles(Modeles modeles) {
        this.modeles = modeles;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Integer getEtatGeneral() {
        return etatGeneral;
    }

    public void setEtatGeneral(Integer etatGeneral) {
        this.etatGeneral = etatGeneral;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public Integer getEtatValidation() {
        return etatValidation;
    }

    public void setEtatValidation(Integer etatValidation) {
        this.etatValidation = etatValidation;
    }

    public List<ValidationAnnoncesHistorique> getValidationAnnoncesHistoriques() {
        return validationAnnoncesHistoriques;
    }

    public void setValidationAnnoncesHistoriques(List<ValidationAnnoncesHistorique> validationAnnoncesHistoriques) {
        this.validationAnnoncesHistoriques = validationAnnoncesHistoriques;
    }


    public Integer getStatus() {
        return status;
    }


    public void setStatus(Integer status) {
        this.status = status;
    }

    public static List<Annonces> getAnnoncesAccueilFiltreByKeywords(Connection con, String key, Integer userID ,String order) throws SQLException {
        ArrayList<Annonces> annoncesList = new ArrayList<>();

        String sql = "WITH fav AS (SELECT id_annonce, 0 AS status FROM annonces WHERE etat_validation = 20), " +
                     "userfav AS (SELECT favoris.id_annonce, status FROM favoris JOIN annonces ON etat_validation = 20 and favoris.id_annonce = annonces.id_annonce WHERE iduser = ?), " +
                     "favunion AS (SELECT * FROM fav UNION ALL SELECT * FROM userfav), " +
                     "favok AS (SELECT id_annonce, SUM(status) AS status FROM favunion GROUP BY id_annonce), " +
                     "v_annonces AS (SELECT a.id_annonce, a.etat_general, a.etat_validation, a.localisation, a.prix, a.modeles_id_modeles, " +
                                    "a.utilisateur_id_utilisateur, a.description, a.annonces_order, a.date_poste, m.nom_modele, " +
                                    "m.carburant_id_carburant, m.categorie_id_categorie, m.marque_id_marque, c.categorie, cb.carburant, " +
                                    "ma.marque, u.birthday, u.hierarchie, u.mail, u.nom, u.prenom " +
                                    "FROM annonces a " +
                                    "JOIN modeles m ON a.modeles_id_modeles = m.id_modeles " +
                                    "JOIN categorie c ON m.categorie_id_categorie = c.id_categorie " +
                                    "JOIN carburant cb ON m.carburant_id_carburant = cb.id_carburant " +
                                    "JOIN marque ma ON m.marque_id_marque = ma.id_marque " +
                                    "JOIN utilisateur u ON a.utilisateur_id_utilisateur = u.id_utilisateur), " +
                     "v_annonces2 AS (SELECT * FROM v_annonces WHERE etat_validation = 20) " +
                     "SELECT v_annonces2.*, status FROM v_annonces2 JOIN favok ON v_annonces2.id_annonce = favok.id_annonce and("
                     +
                     getLikeForKeywords(key)
                     +") order by date_poste "+order;

        System.out.println(sql);

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Create Annonces object and populate it with data from ResultSet
                Annonces annonce = new Annonces();
                // Populate Annonces fields from ResultSet, e.g., rs.getInt("id_annonce"), rs.getString("etat_general"), etc.
                annonce.setIdAnnonce(rs.getInt("id_annonce"));
                annonce.setEtatGeneral(rs.getInt("etat_general"));
                annonce.setLocalisation(rs.getString("localisation"));
                annonce.setPrix(rs.getDouble("prix"));
                annonce.setDescription(rs.getString("description"));
                annonce.setDatePoste(rs.getTimestamp("date_poste"));
                annonce.setStatus(rs.getInt("status"));
                annonce.setAnnoncesPhotos(PhotoAnnonce.selectWhere(con, userID, true));
                Carburant c = new Carburant();
                c.setCarburant(rs.getString("carburant"));
                Categorie cc = new Categorie() ;
                cc.setCategorie(rs.getString("categorie"));                

                Marque m = new Marque(); 
                m.setMarque(rs.getString("marque"));

                Modeles modeles = new Modeles() ;
                modeles.setCarburant(c);
                modeles.setCategorie(cc);
                modeles.setMarque(m);
                modeles.setNomModele(rs.getString("nom_modele"));

                annonce.setModeles(modeles);

                Utilisateur u = new Utilisateur() ;
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                // ...
                annonce.setUtilisateur(u);
                annoncesList.add(annonce);
            }
        }
        finally{
            con.close();
        }

        return annoncesList;
    }

    private static String getLikeForKeywords(String key) {
        String [] all = new String[]{"localisation" ,"description" ," nom_modele","categorie","carburant","marque","nom","prenom"};
        String[] in =  "etat_general,prix".split(",") ;
        String end = "";
        for (String string : all) {
            end = end + string+" ilike '%"+key+"%' or ";  
        }
        for (String string : in) {
            try {
                end = string+ "="+Integer.parseInt(key);
            } catch (Exception e) {// do nothing
            }
        }
        end = end.trim();
        if (end.endsWith("or")) {
            end = end .substring(0, end.lastIndexOf("or"));
        }
        return end;
    }

    public String[] getAnnoncesPhotos() {
        return annoncesPhotos;
    }

    public void setAnnoncesPhotos(String[] annoncesPhotos) {
        this.annoncesPhotos = annoncesPhotos;
    }
    
}

  

 
         
  
  
  
  