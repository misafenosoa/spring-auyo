package AutoOccazMarket.AutoOccazMarket.Security.JWT.Filter.Admin;

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
public class AllPermission extends OncePerRequestFilter{

    @Autowired
    private JWTValidatorConfiguration jwtValidator;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // if the token is present
        String token = getJWTFromRequest(request);

        if (token != null && jwtValidator.validateToken(token)) {
            String username = jwtValidator.getUsernameFromJWT(token);
            Double role = jwtValidator.getRoleFromJWT(token);
            if (role != null && role ==30) 
            {
                // OK
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList()));
                filterChain.doFilter(request, response);                
            }else{
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
