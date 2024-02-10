package AutoOccazMarket.AutoOccazMarket.CORS;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Cors {

    @Value("${frontend.url}")
    private String url;

    @Bean
    WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry re){
                re.addMapping("/**").
                allowedMethods(CorsConfiguration.ALL).
                allowedHeaders(CorsConfiguration.ALL).
                allowedOriginPatterns("*") ;
            }
        };
    }    
}
