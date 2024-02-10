package AutoOccazMarket.AutoOccazMarket.Security.JWT.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTGenerator 
{

    @Value("${jwt.secret}") // Vous pouvez externaliser cette valeur dans votre application.properties ou application.yml
    private String secretKey;

    @Value("${jwt.expiration}") // 
    private long expiration;

    public String generateToken(String email, String username, String role , Integer idUser) {

        // expiryDate
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);

        // subject
        Claims claims = Jwts.claims()
                .setSubject(email);

        // claims
        claims.put("username", username); 
        claims.put("role", role);
        claims.put("idUser", idUser);

        // generate token
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS512)
                .compact();
    }
    
}
