package AutoOccazMarket.AutoOccazMarket.repositories;

import AutoOccazMarket.AutoOccazMarket.entities.Annonces;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnnoncesRepository extends JpaRepository<Annonces, Integer> {

       List<Annonces> findByEtatValidationOrderByDatePosteDesc(Integer etatValidation);
       
       List<Annonces> findByEtatValidationOrderByDatePosteAsc(Integer etatValidation);

       // Search by Categorie with order
 // Search by Categorie with order
       @Query("SELECT a FROM Annonces a " +
       "WHERE (:categorie IS NULL OR LOWER(a.modeles.categorie.categorie) LIKE LOWER(CONCAT('%', :categorie, '%'))) " +
       "AND a.etatValidation = 20 " +
       "ORDER BY CASE WHEN :order = 'asc' THEN a.datePoste END ASC, " +
       "CASE WHEN :order = 'desc' THEN a.datePoste END DESC")
       List<Annonces> searchAnnoncesByCategorie(@Param("categorie") String categorie, @Param("order") String order);

       // Search by Marque with order
       @Query("SELECT a FROM Annonces a " +
       "WHERE (:marque IS NULL OR LOWER(a.modeles.marque.marque) LIKE LOWER(CONCAT('%', :marque, '%'))) " +
       "AND a.etatValidation = 20 " +
       "ORDER BY CASE WHEN :order = 'asc' THEN a.datePoste END ASC, " +
       "CASE WHEN :order = 'desc' THEN a.datePoste END DESC")
       List<Annonces> searchAnnoncesByMarque(@Param("marque") String marque, @Param("order") String order);

       // Search by Carburant with order
       @Query("SELECT a FROM Annonces a " +
       "WHERE (:carburant IS NULL OR LOWER(a.modeles.carburant.carburant) LIKE LOWER(CONCAT('%', :carburant, '%'))) " +
       "AND a.etatValidation = 20 " +
       "ORDER BY CASE WHEN :order = 'asc' THEN a.datePoste END ASC, " +
       "CASE WHEN :order = 'desc' THEN a.datePoste END DESC")
       List<Annonces> searchAnnoncesByCarburant(@Param("carburant") String carburant, @Param("order") String order);

       // Search by Modeles with order
       @Query("SELECT a FROM Annonces a " +
       "WHERE (:modele IS NULL OR LOWER(a.modeles.nomModele) LIKE LOWER(CONCAT('%', :modele, '%'))) " +
       "AND a.etatValidation = 20 " +
       "ORDER BY CASE WHEN :order = 'asc' THEN a.datePoste END ASC, " +
       "CASE WHEN :order = 'desc' THEN a.datePoste END DESC")
       List<Annonces> searchAnnoncesByModeles(@Param("modele") String modele, @Param("order") String order);


      @Query("SELECT a FROM Annonces a where :one<= a.prix and a.prix < :two AND a.etatValidation = 20"+
      "ORDER BY CASE WHEN :order = 'asc' THEN a.datePoste END ASC, " +
      "CASE WHEN :order = 'desc' THEN a.datePoste END DESC")
      List<Annonces> searchByPrix(@Param("one") Double one , @Param("two") Double two , @Param("order") String order);

       @Query(value = "SELECT a FROM Annonces a WHERE a.etatValidation = 20 " +
       "ORDER BY CASE WHEN :order = 'asc' THEN a.datePoste END ASC, " +
       "CASE WHEN :order = 'desc' THEN a.datePoste END DESC")
       List<Annonces> searchByDate(@Param("order") String order);
       
       
       

       @Query("SELECT a FROM Annonces a " +
       "WHERE a.etatValidation = 20 AND " +
       "(LOWER(a.description) LIKE LOWER(CONCAT('%', :motCle, '%')) OR " +
       "LOWER(a.localisation) LIKE LOWER(CONCAT('%', :motCle, '%')) OR " +
       "LOWER(a.modeles.nomModele) LIKE LOWER(CONCAT('%', :motCle, '%')) OR " +
       "LOWER(a.utilisateur.nom) LIKE LOWER(CONCAT('%', :motCle, '%')) OR " +
       "LOWER(a.utilisateur.prenom) LIKE LOWER(CONCAT('%', :motCle, '%')) OR " +
       "LOWER(a.modeles.marque.marque) LIKE LOWER(CONCAT('%', :motCle, '%')) OR " +
       "LOWER(a.modeles.categorie.categorie) LIKE LOWER(CONCAT('%', :motCle, '%')) OR " +
       "LOWER(a.modeles.carburant.carburant) LIKE LOWER(CONCAT('%', :motCle, '%')) OR " +
       "CAST(a.prix AS STRING) LIKE :motCle) " +
       "ORDER BY CASE WHEN :order = 'asc' THEN a.datePoste END ASC, " +
       "CASE WHEN :order = 'desc' THEN a.datePoste END DESC")
       List<Annonces> searchByMotCle(@Param("motCle") String motCle, @Param("order") String order);



}
