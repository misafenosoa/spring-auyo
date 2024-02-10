package AutoOccazMarket.AutoOccazMarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import AutoOccazMarket.AutoOccazMarket.entities.Carburant;

public interface CarburantRepository extends JpaRepository<Carburant,Integer>{
    
    boolean existsByCarburant(String carburant); 
}
