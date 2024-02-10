package AutoOccazMarket.AutoOccazMarket.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import AutoOccazMarket.AutoOccazMarket.entities.AnnoncesAvecCommission;
import AutoOccazMarket.AutoOccazMarket.repositories.AnnoncesAvecCommissionRepository;


@Service
public class CRUDAnnoncesAvecCommission {

    @Autowired
    AnnoncesAvecCommissionRepository annoncesAvecCommissionRepository;

    public List<AnnoncesAvecCommission> getAnnoncesAvecCommissionList() {
        return annoncesAvecCommissionRepository.findAll();
    }

    public void deleteAnnoncesAvecCommissionByID(Integer id) {
        annoncesAvecCommissionRepository.deleteById(id);

    }

    public AnnoncesAvecCommission postAnnoncesAvecCommission(AnnoncesAvecCommission annoncesAvecCommission) {
        return annoncesAvecCommissionRepository.save(annoncesAvecCommission);
    }

    public AnnoncesAvecCommission getAnnoncesAvecCommissionByID(Integer id) {
        try {
            return annoncesAvecCommissionRepository.findById(id).get();
        } catch (java.util.NoSuchElementException e) {
            return null;
        }
    }


}
