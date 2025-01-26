package co.com.ancas.configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CorsConfiguration implements WebMvcConfigurer {

    @Value("${cors.allowed.paths}")
    private String paths;
    @Value("${cors.allowed.origins}")
    private String origins;
    @Value("${cors.allowed.methods}")
    private String[] methods;
    @Value("${cors.allowed.headers}")
    private String[] headers;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(paths)
                .allowedOrigins(origins)
                .allowedMethods(methods)
                .allowedHeaders(headers)
                .allowCredentials(true)
                .maxAge(3600);
    }
}
