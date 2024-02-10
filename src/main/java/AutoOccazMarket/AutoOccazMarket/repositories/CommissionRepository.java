package AutoOccazMarket.AutoOccazMarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import AutoOccazMarket.AutoOccazMarket.entities.Commission;

public interface CommissionRepository extends JpaRepository<Commission ,Integer>{
    
}
