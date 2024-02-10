package AutoOccazMarket.AutoOccazMarket.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Favoris {
    
    public static void logicFavori(Connection connection, Integer user, Integer annonceID) throws Exception{
            // Vérifier si l'annonce est déjà présente dans la table favoris pour cet utilisateur
            String checkQuery = "SELECT idfavoris, status FROM favoris WHERE iduser = ? AND id_annonce = ?";
            try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
                checkStmt.setInt(1, user);
                checkStmt.setInt(2, annonceID);
                ResultSet resultSet = checkStmt.executeQuery();

                if (resultSet.next()) {
                    // L'annonce est déjà présente, mettre à jour le statut (0 -> 1 ou 1 -> 0)
                    int favorisID = resultSet.getInt("idfavoris");
                    int currentStatus = resultSet.getInt("status");
                    int newStatus = (currentStatus == 0) ? 1 : 0;

                    String updateQuery = "UPDATE favoris SET status = ? WHERE idfavoris = ?";
                    try (PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {
                        updateStmt.setInt(1, newStatus);
                        updateStmt.setInt(2, favorisID);
                        updateStmt.executeUpdate();
                    }
                } else {
                    // L'annonce n'est pas présente, l'ajouter avec le statut 1
                    String insertQuery = "INSERT INTO favoris (iduser, id_annonce, status) VALUES (?, ?, 1)";
                    try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
                        insertStmt.setInt(1, user);
                        insertStmt.setInt(2, annonceID);
                        insertStmt.executeUpdate();
                    }
                }
            }

    }

}


// TABLE: favoris
//  idfavoris | iduser | id_annonce | status


// IMPLEMENTE LA FONCTION SUIVANTE:
// PREMIEREMENT ON REGARDE SI L'USER A DEJA CETTE ANNONCE DANS LA TABLE

// SI OUI : UPDATE : 0 ->1 OU 1->0

// si n'est pas presente dans la table avec l'user:
// insertion avec status =1


//     public static void logicFavori(Connection connection , Integer user , Integer annoconcesID){
        
//     }

