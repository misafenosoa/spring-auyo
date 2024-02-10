package AutoOccazMarket.AutoOccazMarket.services;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.LinkedHashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import AutoOccazMarket.AutoOccazMarket.dto.AnnoncesFiltreDTO;
import AutoOccazMarket.AutoOccazMarket.entities.Annonces;
import AutoOccazMarket.AutoOccazMarket.entities.PhotoAnnonce;
import AutoOccazMarket.AutoOccazMarket.entities.ValidationAnnoncesHistorique;
import AutoOccazMarket.AutoOccazMarket.repositories.AnnoncesRepository;
import AutoOccazMarket.AutoOccazMarket.repositories.ValidationAnnoncesHistoriqueRepository;
import jakarta.transaction.Transactional;

@Service
public class CRUDAnnonces {
    
    @Autowired
    AnnoncesRepository annoncesRepository ;

    @Autowired
    ValidationAnnoncesHistoriqueRepository validationAnnoncesHistoriqueRepository ;


    public List<Annonces> getAnnoncesNonPostees(){
        return annoncesRepository.findByEtatValidationOrderByDatePosteAsc(1);
    }

    public List<Annonces> getAnnoncesPostees(Connection con) throws Exception{
        try{
            con.setAutoCommit(false);
        } catch (Exception e) {
            throw e ;
        }
        List<Annonces> ls = annoncesRepository.findByEtatValidationOrderByDatePosteDesc(20);

        for (int i = 0; i < ls.size(); i++) {
            try {
                ls.get(i).setAnnoncesPhotos(PhotoAnnonce.selectWhere(con,ls.get(i).getIdAnnonce(), true));
            } catch (Exception e) {
                con.rollback();
                con.close();
                throw e;
            }
        }
        return ls ;
    }

    public List<Annonces> getAnnoncesList() 
    {
        return annoncesRepository.findAll();
    }

    public void deleteAnnoncesByID(Integer id)
    {
        annoncesRepository.deleteById(id);

    }

    /**
     * Insertion annonces
     * =>transactionel miaraka @ historisation
     * @param annonces
     * @return
     */
    @Transactional
    public Annonces postAnnonces(Annonces annonces)
    {
        annonces.setEtatValidation(1);
        Annonces a= annoncesRepository.save(annonces) ;
        ValidationAnnoncesHistorique v = new ValidationAnnoncesHistorique();
        v.setAnnonces(annonces);
        v.setDescription("premiere insertion");
        v.setEtatValidation(1);
        v.setValidateur(annonces.getUtilisateur());

        validationAnnoncesHistoriqueRepository.save(v) ;
        return a ;
    }

    public Annonces getAnnoncesByID (Integer id)
    {
        try 
        {
            return annoncesRepository.findById(id).get();
        } 
        catch (java.util.NoSuchElementException e) 
        {
            return null ;
        }
    }

    public void updateAnnonces(Integer id , Annonces annonces)
    {
        Annonces annoncesToUpdate = getAnnoncesByID(id) ;

        if(annoncesToUpdate == null) return;


        if (annonces.getEtatGeneral()!=null) {
            annoncesToUpdate.setEtatGeneral(annonces.getEtatGeneral());                    
        }

        if (annonces.getEtatValidation()!=null) {
            annoncesToUpdate.setEtatValidation(annonces.getEtatValidation());                    
        }
        if (annonces.getLocalisation()!=null) {
            annoncesToUpdate.setLocalisation(annonces.getLocalisation());                    
        }
        if (annonces.getModeles()!=null) {
            annoncesToUpdate.setModeles(annonces.getModeles());                    
        }
        if (annonces.getUtilisateur()!=null) {
            annoncesToUpdate.setUtilisateur(annonces.getUtilisateur());                    
        }
        if (annonces.getPrix()!=null) {
            annoncesToUpdate.setPrix(annonces.getPrix());
        }
        if (annonces.getDescription()!=null) {
            annoncesToUpdate.setDescription(annonces.getDescription());
        }


        postAnnonces(annoncesToUpdate) ;
    }

    public List<Annonces> selectWhere(AnnoncesFiltreDTO annoncesFiltreDTO, Connection con) throws SQLException {
        if (annoncesFiltreDTO == null) {
            return null;
        }

        java.util.Set<Annonces> resultSet = new LinkedHashSet<>(); // Use LinkedHashSet for consistent order

        if (annoncesFiltreDTO.getModeles() != null) {
            for (String modele : annoncesFiltreDTO.getModeles()) {
                System.out.println("modeles");
                resultSet.addAll(annoncesRepository.searchAnnoncesByModeles(modele, annoncesFiltreDTO.getOrder()));
            }
        }

        if (annoncesFiltreDTO.getCarburant() != null) {
            for (String carburant : annoncesFiltreDTO.getCarburant()) {
                System.out.println("annonces");
                resultSet.addAll(annoncesRepository.searchAnnoncesByCarburant(carburant, annoncesFiltreDTO.getOrder()));
            }
        }

        if (annoncesFiltreDTO.getCategories() != null) {
            for (String categorie : annoncesFiltreDTO.getCategories()) {
                System.out.println("categorie");
                resultSet.addAll(annoncesRepository.searchAnnoncesByCategorie(categorie, annoncesFiltreDTO.getOrder()));
            }
        }

        if (annoncesFiltreDTO.getMarque() != null) {
            for (String marque : annoncesFiltreDTO.getMarque()) {
                System.out.println("marque");
                resultSet.addAll(annoncesRepository.searchAnnoncesByMarque(marque, annoncesFiltreDTO.getOrder()));
            }
        }
        if ( annoncesFiltreDTO.getOneValue() !=null && annoncesFiltreDTO.getTwoValue()!=null) {
            resultSet.addAll(annoncesRepository.searchByPrix(annoncesFiltreDTO.getOne(),annoncesFiltreDTO.getTwo() , annoncesFiltreDTO.getOrder()));
        }

        if (annoncesFiltreDTO.getCarburant() == null && annoncesFiltreDTO.getCategories() == null
                && annoncesFiltreDTO.getMarque() == null && annoncesFiltreDTO.getModeles() == null && annoncesFiltreDTO.getOneValue() ==null && annoncesFiltreDTO.getTwoValue()==null) {

            resultSet.addAll(annoncesRepository.searchByDate(annoncesFiltreDTO.getOrder()));
        }


        ArrayList<Annonces> ls = new ArrayList<>(resultSet);
        for (Annonces annonces : ls) {
            annonces.setAnnoncesPhotos(PhotoAnnonce.selectWhere(con,annonces.getIdAnnonce(), true));
        }
        return ls;
    }

    public List<Annonces> searchBy(AnnoncesFiltreDTO annoncesFiltreDTO, Connection con) throws Exception{
        List<Annonces> ls = annoncesRepository.searchByMotCle(annoncesFiltreDTO.getKey() ,annoncesFiltreDTO.getOrder());
        for (int i = 0; i < ls.size(); i++) {
            try{
                ls.get(i).setAnnoncesPhotos(PhotoAnnonce.selectWhere(con,ls.get(i).getIdAnnonce(), true));
            }catch(Exception e){
                con.close();
                throw e ;
            }
        }
        return  ls ;
    }

    
}