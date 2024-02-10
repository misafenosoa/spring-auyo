package AutoOccazMarket.AutoOccazMarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import AutoOccazMarket.AutoOccazMarket.entities.Modeles;

public interface ModelesRepository extends JpaRepository<Modeles,Integer> {

    boolean existsByNomModele(String NomModele);
}
