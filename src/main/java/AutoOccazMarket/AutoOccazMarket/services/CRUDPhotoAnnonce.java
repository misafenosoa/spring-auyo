package AutoOccazMarket.AutoOccazMarket.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import AutoOccazMarket.AutoOccazMarket.entities.PhotoAnnonce;
import AutoOccazMarket.AutoOccazMarket.repositories.PhotoAnnonceRepository;



@Service
public class CRUDPhotoAnnonce {
    
    @Autowired
    PhotoAnnonceRepository photoannoncesRepository ;

    public List<PhotoAnnonce> getPhotoAnnonceList() 
    {
        return photoannoncesRepository.findAll();
    }

    public void deletePhotoAnnonceByID(Integer id)
    {
        photoannoncesRepository.deleteById(id);

    }

    public PhotoAnnonce postPhotoAnnonce(PhotoAnnonce photoannonces)
    {
        return photoannoncesRepository.save(photoannonces) ;
    }

    public PhotoAnnonce getPhotoAnnonceByID (Integer id)
    {
        try 
        {
            return photoannoncesRepository.findById(id).get();
        } 
        catch (java.util.NoSuchElementException e) 
        {
            return null ;
        }
    }

    public void updatePhotoAnnonce(Integer id , PhotoAnnonce photoannonces)
    {
        PhotoAnnonce photoannoncesToUpdate = getPhotoAnnonceByID(id) ;

        if(photoannoncesToUpdate == null) return;


        if(photoannonces.getAnnonces()!=null){
            photoannoncesToUpdate.setAnnonces(photoannonces.getAnnonces());
        }
        if (photoannonces.getBase64()!=null) {
            photoannoncesToUpdate.setBase64(photoannonces.getBase64());
        }

       


        postPhotoAnnonce(photoannoncesToUpdate) ;
    }


    
}

