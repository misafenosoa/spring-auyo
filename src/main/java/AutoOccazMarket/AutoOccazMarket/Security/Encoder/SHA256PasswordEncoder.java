package AutoOccazMarket.AutoOccazMarket.Security.Encoder;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256PasswordEncoder 
{

    public String encodePassword(String password) 
    {
        try 
        {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            
            byte[] hashedPassword = md.digest(password.getBytes());

            StringBuilder hexPassword = new StringBuilder();
            
            for (byte b : hashedPassword) 
            {
                String hex = String.format("%02x", b);
                hexPassword.append(hex);
            }

            return hexPassword.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();

            return null;
        }
    }

    public boolean matches(String rawPassword, String encodedPassword)
    {
        return encodePassword(rawPassword).equals(encodedPassword);
    }
}
