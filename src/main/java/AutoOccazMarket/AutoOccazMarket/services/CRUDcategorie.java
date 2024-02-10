package AutoOccazMarket.AutoOccazMarket.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import AutoOccazMarket.AutoOccazMarket.entities.Categorie;
import AutoOccazMarket.AutoOccazMarket.repositories.CategorieRepository;

@Service
public class CRUDcategorie {

    @Autowired
    CategorieRepository categorieRepository;

    public List<Categorie> getCategorieList() {
        return categorieRepository.findAll();
    }

    public void deleteCategorieByID(Integer id) {
        categorieRepository.deleteById(id);

    }

    public Categorie postCategorie(Categorie categorie) throws Exception {
        if (categorieRepository.existsByCategorie(categorie.getCategorie().trim())) {
            throw new Exception("Categorie deja existant pour: " + categorie.getCategorie());
        }
        return categorieRepository.save(categorie);
    }

    public Categorie getCategorieByID(Integer id) {
        try {
            return categorieRepository.findById(id).get();
        } catch (java.util.NoSuchElementException e) {
            return null;
        }
    }

    public void updateCategorie(Integer id, Categorie categorie) throws Exception {
        Categorie categorieToUpdate = getCategorieByID(id);

        if (categorieToUpdate == null)
            return;

        if (categorie.getCategorie() != null) {
            categorieToUpdate.setCategorie(categorie.getCategorie());
        }

        postCategorie(categorieToUpdate);
    }

    public Page<Categorie> findCategoriesWithPagination(Integer offset, Integer pageSize) {
        Page<Categorie> products = categorieRepository.findAll(PageRequest.of(offset, pageSize));
        return products;
    }

}
