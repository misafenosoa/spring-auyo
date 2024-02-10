package AutoOccazMarket.AutoOccazMarket.entities;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import AutoOccazMarket.AutoOccazMarket.Security.Encoder.SHA256PasswordEncoder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_utilisateur")
    private Integer idutilisateur;

    @Column(nullable = false, unique = true, length = 250)
    private String mail;

    @Column(nullable = false, length = 250)
    @JsonIgnore
    private String mdp;

    @Column(nullable = false, unique = true, length = 250)
    private String prenom;

    private Date birthday;

    private String nom;

    // @OneToMany( mappedBy = "utilisateur" , fetch = FetchType.EAGER)
    // private Annonces [] annonces ;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Column(nullable = false, length = 250)
    private Integer hierarchie;

    /**
     * @return Integer return the idutilisateur
     */
    public Integer getIdutilisateur() {
        return idutilisateur;
    }

    /**
     * @param idutilisateur the idutilisateur to set
     */
    public void setIdutilisateur(Integer idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

    /**
     * @return String return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * @param mail the mail to set
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * @return String return the mdp
     */
    public String getMdp() {
        return mdp;
    }

    /**
     * @param mdp the mdp to set
     */
    public void setMdp(String mdp) {
        if (mdp!=null) 
        this.mdp= new SHA256PasswordEncoder().encodePassword(mdp);
    }

    /**
     * @return String return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * @return String return the hierarchie
     */
    public Integer getHierarchie() {
        return hierarchie;
    }

    /**
     * @param hierarchie the hierarchie to set
     */
    public void setHierarchie(Integer hierarchie) {
        this.hierarchie = hierarchie;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    // public Annonces[] getAnnonces() {
    //     return annonces;
    // }

    // public void setAnnonces(Annonces[] annonces) {
    //     this.annonces = annonces;
    // }



}
