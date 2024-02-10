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

import AutoOccazMarket.AutoOccazMarket.dto.ModelesDTO;
import AutoOccazMarket.AutoOccazMarket.entities.Modeles;
import AutoOccazMarket.AutoOccazMarket.services.CRUDModeles;

@RestController
@CrossOrigin(origins = "${frontend.url}")
public class ModelesController {

    @Autowired
    private CRUDModeles crudModeles;

    @Autowired
    private ModelesDTO modelesDTO;

    @GetMapping(path = "/modeles")
    public ModelesDTO getModeless() {
        List<Modeles> modeles = crudModeles.getModelesList();
        modelesDTO.setModelesAsList(modeles);
        return modelesDTO;
    }

    @GetMapping(path = "/modeles/{id}")
    public ModelesDTO getModelessById(@PathVariable("id") final Integer id) {
        Modeles modeles = crudModeles.getModelesByID(Integer.valueOf(id));
        modelesDTO.setModeles(modeles);

        return modelesDTO;
    }

    @PostMapping(path = "/modeles")
    public ModelesDTO saveModeles(@RequestBody ModelesDTO modelesDTO) {
        try {
            crudModeles.postModeles(modelesDTO.getModeles());

        } catch (Exception e) {
            modelesDTO.setErrors(e.getMessage());
        }

        return modelesDTO;

    }

    @PutMapping(path = "/modeles/{id}")
    public ModelesDTO updateModeles(@PathVariable("id") final Integer id, @RequestBody ModelesDTO modelesDTO) {
        try {
            crudModeles.updateModeles(id, modelesDTO.getModeles());
        } catch (Exception e) {
            modelesDTO.setErrors(e.getMessage());
        }
        return modelesDTO;
    }

    @DeleteMapping(path = "/modeles/{id}")
    public void deleteModeles(@PathVariable("id") final Integer id) {
        crudModeles.deleteModelesByID(id);
    }

    @GetMapping(path = "/modeles/{offset}/{pageSize}")
    public ModelesDTO getModeles(@PathVariable("offset") Integer offset, @PathVariable("pageSize") Integer pageSize) {
        ModelesDTO modelesDTO = new ModelesDTO();

        try {
             Page<Modeles>  c =crudModeles.findModelesWithPagination(offset, pageSize);
            modelesDTO.setModelesAsList(c.toList());
            modelesDTO.setPage(c.getTotalPages());
            
        } catch (Exception e) {
            modelesDTO.setErrors(e.getMessage());
        }

        return modelesDTO;
    }
}
