package AutoOccazMarket.AutoOccazMarket.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import AutoOccazMarket.AutoOccazMarket.entities.Marque;
import AutoOccazMarket.AutoOccazMarket.repositories.MarqueRepository;

@Service
public class CRUDMarque {

    @Autowired
    MarqueRepository marqueRepository;

    public List<Marque> getMarqueList() {
        return marqueRepository.findAll();
    }

    public void deleteMarqueByID(Integer id) {
        marqueRepository.deleteById(id);

    }

    public Marque postMarque(Marque marque) throws Exception {
        if (marqueRepository.existsByMarque(marque.getMarque().trim())) {
            throw new Exception("Marque deja existant pour: " + marque.getMarque());
        }
        return marqueRepository.save(marque);
    }

    public Marque getMarqueByID(Integer id) {
        try {
            return marqueRepository.findById(id).get();
        } catch (java.util.NoSuchElementException e) {
            return null;
        }
    }

    public void updateMarque(Integer id, Marque marque) throws Exception {
        Marque marqueToUpdate = getMarqueByID(id);

        if (marqueToUpdate == null)
            return;

        if (marque.getMarque() != null)

            if (marque.getMarque() != null) {
                marqueToUpdate.setMarque(marque.getMarque());
            }

        marqueToUpdate.setMarque(marque.getMarque());

        postMarque(marqueToUpdate);
    }

    public Page<Marque> findMarquesWithPagination(Integer offset, Integer pageSize) {
        Page<Marque> products = marqueRepository.findAll(PageRequest.of(offset, pageSize));
        return products;
    }

}
