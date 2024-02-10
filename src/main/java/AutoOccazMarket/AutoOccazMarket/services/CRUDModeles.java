package AutoOccazMarket.AutoOccazMarket.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import AutoOccazMarket.AutoOccazMarket.entities.Modeles;
import AutoOccazMarket.AutoOccazMarket.repositories.ModelesRepository;

@Service
public class CRUDModeles {

    @Autowired
    ModelesRepository modelesRepository;

    public List<Modeles> getModelesList() {
        return modelesRepository.findAll();
    }

    public void deleteModelesByID(Integer id) {
        modelesRepository.deleteById(id);

    }

    public Modeles postModeles(Modeles modeles) throws Exception {
        if (modelesRepository.existsByNomModele(modeles.getNomModele().trim())) {
            throw new Exception("Modele deja existant pour: " + modeles.getNomModele());
        }
        return modelesRepository.save(modeles);
    }

    public Modeles getModelesByID(Integer id) {
        try {
            return modelesRepository.findById(id).get();
        } catch (java.util.NoSuchElementException e) {
            return null;
        }
    }

    public void updateModeles(Integer id, Modeles modeles) throws Exception {
        Modeles modelesToUpdate = getModelesByID(id);

        if (modelesToUpdate == null)
            return;

        if (modeles.getCarburant() != null) {
            modelesToUpdate.setCarburant(modeles.getCarburant());
        }

        if (modeles.getCategorie() != null) {
            modelesToUpdate.setCategorie(modeles.getCategorie());
        }

        if (modeles.getMarque() != null) {
            modelesToUpdate.setMarque(modeles.getMarque());
        }

        if (modeles.getNomModele() != null) {
            modelesToUpdate.setNomModele(modeles.getNomModele());
        }

        modelesRepository.save(modelesToUpdate);
    }

    public Page<Modeles> findModelesWithPagination(Integer offset, Integer pageSize) {
        Page<Modeles> products = modelesRepository.findAll(PageRequest.of(offset, pageSize));
        return products;
    }

}
