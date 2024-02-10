package AutoOccazMarket.AutoOccazMarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import AutoOccazMarket.AutoOccazMarket.entities.Marque;

public interface MarqueRepository extends JpaRepository <Marque,Integer>{
    
    boolean existsByMarque(String marque) ;

}
