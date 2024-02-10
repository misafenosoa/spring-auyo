package AutoOccazMarket.AutoOccazMarket.services.userSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import AutoOccazMarket.AutoOccazMarket.entities.Utilisateur;
import AutoOccazMarket.AutoOccazMarket.repositories.UtilisateurRepository;

@Component
public class RegisterService {
    
    @Autowired 
    private UtilisateurRepository utilisateurRepository;

    public void registerUser(Utilisateur user) throws Exception {
        if (utilisateurRepository.existsByMail(user.getMail())) {
            throw new Exception("email deja existant pour "+user.getMail());
        }
        utilisateurRepository.save(user);
    } 

}
