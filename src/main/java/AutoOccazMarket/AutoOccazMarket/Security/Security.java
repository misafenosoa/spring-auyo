package AutoOccazMarket.AutoOccazMarket.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
public class Security 
{
    
    @Bean
    @SuppressWarnings("removal")
    public SecurityFilterChain filterChain ( HttpSecurity http) throws Exception
    {
         http
        //  required
        .csrf(AbstractHttpConfigurer::disable)
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        // routes
        .authorizeHttpRequests(
           auth -> {
                    // we authorize any request but FilterSecurityConfiguration
                    // will manage the authentification
                    // and authorization for different request

                    auth.anyRequest().permitAll() ;
                   }
           ) ;

        return http.build() ;
        
    }


// required
    @Bean
    public BCryptPasswordEncoder passwordEncoder ()
    {
        return new BCryptPasswordEncoder() ;
    }

}
