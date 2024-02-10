package AutoOccazMarket.AutoOccazMarket.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import AutoOccazMarket.AutoOccazMarket.dto.CategorieDTO;
import AutoOccazMarket.AutoOccazMarket.entities.Categorie;
import AutoOccazMarket.AutoOccazMarket.services.CRUDcategorie;



@RestController
@CrossOrigin(origins = "${frontend.url}")
public class CategorieController {

    @Autowired
    private CRUDcategorie crudCategorie;

    @Autowired
    private CategorieDTO categoriesDTO;

    @GetMapping(path = "/categories")
    public CategorieDTO getCategories() {
        List<Categorie> categories = crudCategorie.getCategorieList();
        categoriesDTO.setCategorieAsList(categories);
        return categoriesDTO;
    }

    @GetMapping(path = "/categories/{id}")
    public CategorieDTO getCategoriesById(@PathVariable("id") final Integer id) {
        Categorie categories = crudCategorie.getCategorieByID(Integer.valueOf(id));
        categoriesDTO.setCategorie(categories);

        return categoriesDTO;
    }

    @PostMapping(path = "/categories")
    public CategorieDTO saveCategorie(@RequestBody CategorieDTO categoriesDTO) {
        try {
            crudCategorie.postCategorie(categoriesDTO.getCategorie());

        } catch (Exception e) {
            categoriesDTO.setErrors(e.getMessage());
        }

        return categoriesDTO;

    }

    @PutMapping(path = "/categories/{id}")
    public CategorieDTO updateCategorie(@PathVariable("id") final Integer id, @RequestBody CategorieDTO categoriesDTO) {
        try {
            crudCategorie.updateCategorie(id, categoriesDTO.getCategorie());
            
        } catch (Exception e) {
            categoriesDTO.setErrors(e.getMessage());
        }
        return categoriesDTO;
    }

    @DeleteMapping(path = "/categories/{id}")
    public void deleteCategorie(@PathVariable("id") final Integer id) {
        crudCategorie.deleteCategorieByID(id);
    }

    @GetMapping(path = "/categories/{offset}/{pageSize}")
    public CategorieDTO getCategories(@PathVariable("offset") Integer offset, @PathVariable("pageSize") Integer pageSize) {
        CategorieDTO categoriesDTO = new CategorieDTO();
        
        try {
            Page<Categorie> c = crudCategorie.findCategoriesWithPagination(offset, pageSize) ;
            categoriesDTO.setCategorieAsList(c.toList());
            categoriesDTO.setPage(c.getTotalPages()) ;

        } catch (Exception e) {
            categoriesDTO.setErrors(e.getMessage());
        }
        
        return categoriesDTO;
    }
}
