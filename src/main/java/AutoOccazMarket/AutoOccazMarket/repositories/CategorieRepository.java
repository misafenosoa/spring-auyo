package AutoOccazMarket.AutoOccazMarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import AutoOccazMarket.AutoOccazMarket.entities.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Integer> {

     boolean existsByCategorie(String categorie) ;
}
