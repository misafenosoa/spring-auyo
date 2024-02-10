package AutoOccazMarket.AutoOccazMarket.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class PhotoAnnonce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPhotoAnnonce;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Annonces annonces;

    private String base64;

    private static final String INSERT_QUERY = "INSERT INTO photo_annonce (annonces_id_annonce, base64) VALUES (?, ?)";
    private static final String SELECT_WHERE_QUERY = "SELECT base64 FROM photo_annonce WHERE annonces_id_annonce = ?";



    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public Integer getIdPhotoAnnonce() {
        return idPhotoAnnonce;
    }

    public void setIdPhotoAnnonce(Integer idPhotoAnnonce) {
        this.idPhotoAnnonce = idPhotoAnnonce;
    }

    public Annonces getAnnonces() {
        return annonces;
    }

    public void setAnnonces(Annonces annonces) {
        this.annonces = annonces;
    }

    public void save(Connection connection, String[] photos, Integer annonceID) throws Exception{
        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)) {
            for (String photo : photos) {
                statement.setInt(1, annonceID);
                statement.setString(2, photo);
                statement.addBatch();
            }
            statement.executeBatch();
        }
        finally{
            connection.close();
        }
    }

    public static String[] selectWhere(Connection connection, Integer annonceID, boolean b) throws SQLException {
        List<String> urls = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SELECT_WHERE_QUERY)) {
            statement.setInt(1, annonceID);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String url = resultSet.getString("base64");
                    urls.add(url);
                }
            }
            return urls.toArray(new String[urls.size()]);
        }finally{
            if (!b) {
                connection.close();
            }
        }

    }
}
