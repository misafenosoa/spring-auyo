package AutoOccazMarket.AutoOccazMarket.dto;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import AutoOccazMarket.AutoOccazMarket.entities.PhotoAnnonce;



@Component
public class PhotoAnnonceDTO 
{
    PhotoAnnonce [] listPhotoAnnonce ;    

    String [] photos ;

    Integer annonceID ;

    PhotoAnnonce photoannonces ;

    String errors ;

    Map<String, String> tokenInformation;

    public Map<String, String> getTokenInformation() {
        return tokenInformation;
    }

    public void setTokenInformation(Map<String, String> tokenInformation) {
        this.tokenInformation = tokenInformation;
    }

    public void setPhotoAnnonceAsList(List<PhotoAnnonce> list)
    {
        listPhotoAnnonce = list.toArray(new PhotoAnnonce[list.size()]);
    }

    public PhotoAnnonce[] getListPhotoAnnonce() {
        return listPhotoAnnonce;
    }

    public void setListPhotoAnnonce(PhotoAnnonce[] listPhotoAnnonce) {
        this.listPhotoAnnonce = listPhotoAnnonce;
    }

    public PhotoAnnonce getPhotoAnnonce() {
        return photoannonces;
    }

    public void setPhotoAnnonce(PhotoAnnonce photoannonces) {
        this.photoannonces = photoannonces;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    public String[] getPhotos() {
        return photos;
    }

    public void setPhotos(String[] photos) {
        this.photos = photos;
    }

    public Integer getAnnonceID() {
        return annonceID;
    }

    public void setAnnonceID(Integer annonceID) {
        this.annonceID = annonceID;
    }

    public PhotoAnnonce getPhotoannonces() {
        return photoannonces;
    }

    public void setPhotoannonces(PhotoAnnonce photoannonces) {
        this.photoannonces = photoannonces;
    }

}

