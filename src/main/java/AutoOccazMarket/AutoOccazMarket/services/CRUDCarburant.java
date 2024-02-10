package AutoOccazMarket.AutoOccazMarket.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import AutoOccazMarket.AutoOccazMarket.entities.Carburant;
import AutoOccazMarket.AutoOccazMarket.repositories.CarburantRepository;

@Service
public class CRUDCarburant {

    @Autowired
    CarburantRepository carburantRepository;

    public List<Carburant> getCarburantList() {
        return carburantRepository.findAll();
    }

    public Page<Carburant> findCarburantsWithPagination(int offset, int pageSize) {
        Page<Carburant> products = carburantRepository.findAll(PageRequest.of(offset, pageSize));
        return products;
    }

    public void deleteCarburantByID(Integer id) {
        carburantRepository.deleteById(id);

    }

    public Carburant postCarburant(Carburant carburant) throws Exception {
        if (carburantRepository.existsByCarburant(carburant.getCarburant().trim())) {
            throw new Exception("Carburant " + carburant.getCarburant() + " deja existant");
        }
        return carburantRepository.save(carburant);
    }

    public Carburant getCarburantByID(Integer id) {
        try {
            return carburantRepository.findById(id).get();
        } catch (java.util.NoSuchElementException e) {
            return null;
        }
    }

    public void updateCarburant(Integer id, Carburant carburant) throws Exception {
        Carburant carburantToUpdate = getCarburantByID(id);

        if (carburantToUpdate == null)
            return;

        if (carburant.getCarburant() != null) {
            carburantToUpdate.setCarburant(carburant.getCarburant());
        }
        postCarburant(carburantToUpdate);
    }

}
