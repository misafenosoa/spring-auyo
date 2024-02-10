package AutoOccazMarket.AutoOccazMarket.Security.JWT.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTValidator 
{

    @Value("${jwt.secret}")
    protected String secretKey;


    public boolean validateToken(String token) 
    {
        try 
        {
            Claims claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();

            if (isTokenExpired(claims.getExpiration())) 
            {
                return false; // Expired
            }

            return true;
        } 
        catch (ExpiredJwtException | MalformedJwtException |  SignatureException e) 
        {
            return false; 
        }
    }


    private boolean isTokenExpired(Date expiration) 
    {
        return expiration.before(new Date());
    }
}
