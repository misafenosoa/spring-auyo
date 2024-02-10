package AutoOccazMarket.AutoOccazMarket.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import AutoOccazMarket.AutoOccazMarket.dto.UtilisateurDTO;

import AutoOccazMarket.AutoOccazMarket.entities.Utilisateur;
import AutoOccazMarket.AutoOccazMarket.services.CRUDutilisateur;


@RestController

public class UtilisateurController {

    @Autowired
    private CRUDutilisateur crudUtilisateur;

    @Autowired
    private UtilisateurDTO utilisateursDTO;

    @GetMapping(path = "/utilisateurs")
    public UtilisateurDTO getUtilisateurs() {
        List<Utilisateur> utilisateurs = crudUtilisateur.getUtilisateurList();
        utilisateursDTO.setUtilisateurAsList(utilisateurs);
        return utilisateursDTO;
    }

    @GetMapping(path = "/utilisateurs/{id}")
    public UtilisateurDTO getUtilisateursById(@PathVariable("id") final Integer id) {
        Utilisateur utilisateurs = crudUtilisateur.getUtilisateurByID(Integer.valueOf(id));
        utilisateursDTO.setUtilisateur(utilisateurs);

        return utilisateursDTO;
    }

    @PostMapping(path = "/utilisateurs")
    public UtilisateurDTO saveUtilisateur(@RequestBody UtilisateurDTO utilisateursDTO) {
        try {
            utilisateursDTO.getUtilisateur().setMdp(utilisateursDTO.getMdp());
            crudUtilisateur.postUtilisateur(utilisateursDTO.getUtilisateur());

        } catch (Exception e) {
            utilisateursDTO.setErrors(e.getMessage());
        }

        return utilisateursDTO;

    }


    @PutMapping(path = "/utilisateurs/{id}")
    public UtilisateurDTO updateUtilisateur(@PathVariable("id") final Integer id,
            @RequestBody UtilisateurDTO utilisateursDTO) {
                System.out.println(utilisateursDTO.getUtilisateur().getNom());
        utilisateursDTO.getUtilisateur().setMdp(utilisateursDTO.getMdp());
        crudUtilisateur.updateUtilisateur(id, utilisateursDTO.getUtilisateur());

        return utilisateursDTO;
    }

    @DeleteMapping(path = "/utilisateurs/{id}")
    public void deleteUtilisateur(@PathVariable("id") final Integer id) {
        crudUtilisateur.deleteUtilisateurByID(id);
    }

    @GetMapping("/admins")
    public UtilisateurDTO getAdmins() {
        List<Utilisateur> utilisateurs = crudUtilisateur.getAdminList();
        utilisateursDTO.setUtilisateurAsList(utilisateurs);
        return utilisateursDTO;
    }

    @PostMapping(path = "/admins")
    public UtilisateurDTO saveAdmins(@RequestBody UtilisateurDTO utilisateursDTO) {
        try {
            utilisateursDTO.getUtilisateur().setMdp(utilisateursDTO.getMdp());
            crudUtilisateur.postUtilisateur(utilisateursDTO.getUtilisateur());

        } catch (Exception e) {
            utilisateursDTO.setErrors(e.getMessage());
        }

        return utilisateursDTO;

    }

    @GetMapping(path = "/admins/{offset}/{pageSize}")
    public UtilisateurDTO getUtilisateur(@PathVariable("offset") Integer offset, @PathVariable("pageSize") Integer pageSize) {
        UtilisateurDTO utilisateurDTO = new UtilisateurDTO();

        try {

            Page<Utilisateur>  c =crudUtilisateur.findUtilisateurWithPagination(offset, pageSize);
            utilisateurDTO.setUtilisateurAsList(c.toList());
            utilisateurDTO.setPage(c.getTotalPages());
            
            
        } catch (Exception e) {
            utilisateurDTO.setErrors(e.getMessage());
        }

        return utilisateurDTO;
    }
    
}
