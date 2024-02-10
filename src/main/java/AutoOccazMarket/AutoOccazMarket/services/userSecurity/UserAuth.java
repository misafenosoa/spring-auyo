package AutoOccazMarket.AutoOccazMarket.services.userSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import AutoOccazMarket.AutoOccazMarket.entities.Utilisateur;
import AutoOccazMarket.AutoOccazMarket.repositories.UtilisateurRepository;

@Component
public class UserAuth {

    @Autowired
    UtilisateurRepository utilisateurRepository;

    public Utilisateur login(Utilisateur utilisateur) throws Exception{
        Utilisateur u = null;
        try {
            u = utilisateurRepository.findByMail(utilisateur.getMail()).get();
        } catch (Exception e) {
            throw new Exception("Utilisateur non trouve:"+e.getMessage());
        }



        if ((utilisateur.getMdp().equals(u.getMdp()) )) {
            return u;
        }else{
            throw new Exception("Identifiant invalide ou mot de passe invalide ");
        }


    }
    
}
