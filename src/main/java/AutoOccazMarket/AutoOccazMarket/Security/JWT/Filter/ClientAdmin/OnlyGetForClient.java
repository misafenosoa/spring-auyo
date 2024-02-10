package AutoOccazMarket.AutoOccazMarket.Security.JWT.Filter.ClientAdmin;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import AutoOccazMarket.AutoOccazMarket.Security.JWTConfiguration.JWTValidatorConfiguration;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OnlyGetForClient extends OncePerRequestFilter {

    @Autowired
    private JWTValidatorConfiguration jwtValidator;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException 
    {
        // if the token is present
        if (request.getMethod().equals("GET")) {
            GET(request, response, filterChain);            
        }
        else{
            POSTDELETEPUT(request, response, filterChain);
        }

    }
    private void GET(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException{
        String token = getJWTFromRequest(request);
        System.out.println(token);
        System.out.println(jwtValidator.validateToken(token));
        if (token != null && jwtValidator.validateToken(token)) {

            // token informations

            String username = jwtValidator.getUsernameFromJWT(token);
            Double role = jwtValidator.getRoleFromJWT(token);

            System.out.println(username + role);
            // Role verification
            if (role != null && role >=1 ) 
            {
                // OK
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList()));
                filterChain.doFilter(request, response);
            } 
            else 
            {
                // unsatisfied role
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }
        } 
        else 
        {
            // not valid token
            
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private void POSTDELETEPUT(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException{
        String token = getJWTFromRequest(request);

        if (token != null && jwtValidator.validateToken(token)) {

            // token informations

            String username = jwtValidator.getUsernameFromJWT(token);
            Double role = jwtValidator.getRoleFromJWT(token);

            System.out.println(username + role);
            // Role verification
            if (role != null && role ==30) 
            {
                // OK
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList()));
                filterChain.doFilter(request, response);
            } 
            else 
            {
                // unsatisfied role
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }
        } 
        else 
        {
            // not valid token
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private String getJWTFromRequest(HttpServletRequest request) 
    {

        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) ) 
        {
            return bearerToken;
        }

        return null;
    }
    
}
