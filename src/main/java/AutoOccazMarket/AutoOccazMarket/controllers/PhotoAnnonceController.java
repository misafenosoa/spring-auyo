package AutoOccazMarket.AutoOccazMarket.controllers;

import java.sql.DriverManager;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import AutoOccazMarket.AutoOccazMarket.dto.PhotoAnnonceDTO;
import AutoOccazMarket.AutoOccazMarket.entities.PhotoAnnonce;
import AutoOccazMarket.AutoOccazMarket.services.CRUDPhotoAnnonce;


@RestController
@CrossOrigin(origins = "")
public class PhotoAnnonceController {

    @Autowired
    private CRUDPhotoAnnonce crudPhotoAnnonce;

    @Autowired
    private PhotoAnnonceDTO photoannoncesDTO;

    @Value("${spring.datasource.url}")
    private String url ;

    @Value("${spring.datasource.username}")
    private String username ;

    @Value("${spring.datasource.password}")
    private String password ;

    @GetMapping(path = "/photoannonces")
    public PhotoAnnonceDTO getPhotoAnnonces() {
        List<PhotoAnnonce> photoannonces = crudPhotoAnnonce.getPhotoAnnonceList();
        photoannoncesDTO.setPhotoAnnonceAsList(photoannonces);
        return photoannoncesDTO;
    }


    @PostMapping(path = "/photoannonces")
    public PhotoAnnonceDTO savePhotoAnnonce(@RequestBody PhotoAnnonceDTO photoannoncesDTO) {
        try {
            new PhotoAnnonce().save(DriverManager.getConnection(url, username, password),photoannoncesDTO.getPhotos() , photoannoncesDTO.getAnnonceID());

        } catch (Exception e) {
            photoannoncesDTO.setErrors(e.getMessage());
        }

        return photoannoncesDTO;

    }

    @GetMapping("/photoannonces/{annonceID}")
    public PhotoAnnonceDTO getPhotos(@PathVariable("annonceID") Integer annonceID) {
        PhotoAnnonceDTO x = new PhotoAnnonceDTO(); 
        try {
            x.setPhotos(PhotoAnnonce.selectWhere( DriverManager.getConnection(url, username, password) ,annonceID,false));            
        } catch (Exception e) {
            x.setErrors(e.getMessage());
        }
        return x; 
    }
    


    @PutMapping(path = "/photoannonces/{id}")
    public PhotoAnnonceDTO updatePhotoAnnonce(@PathVariable("id") final Integer id, @RequestBody PhotoAnnonceDTO photoannoncesDTO) {
        crudPhotoAnnonce.updatePhotoAnnonce(id, photoannoncesDTO.getPhotoAnnonce());

        return photoannoncesDTO;
    }

    @DeleteMapping(path = "/photoannonces/{id}")
    public void deletePhotoAnnonce(@PathVariable("id") final Integer id) {
        crudPhotoAnnonce.deletePhotoAnnonceByID(id);
    }
}
