package AutoOccazMarket.AutoOccazMarket.Security.JWTConfiguration;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Component;

import AutoOccazMarket.AutoOccazMarket.Security.JWT.utils.JWTValidator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTValidatorConfiguration extends JWTValidator
{


    public String getUsernameFromJWT(String token) 
    {

        Claims claims =getClaims(token) ;

        String username = claims.get("username", String.class);
            
        return username;
       
    }

    public Double getRoleFromJWT(String token) {

        Claims claims =getClaims(token) ;

        String role = claims.get("role", String.class);

        try 
        {
            return Double.parseDouble(role);
        } 
        catch (Exception e) 
        {
            System.out.print(e);
            return null ;
        }

    }

    private Claims getClaims(String token)
    {
        byte[] secretKeyBytes;
        try 
        {
            secretKeyBytes = secretKey.getBytes("UTF-8");
            Claims claims = Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(secretKeyBytes)) // Utilize the updated method
            .build()
            .parseClaimsJws(token)
            .getBody();

            return claims ;
        } 
        catch (UnsupportedEncodingException e) 
        {

            System.out.println(e);

            return null ;
        }
        catch (Exception e2) 
        {
            System.out.print(e2);
         
            return null;
        } 


    }
}
