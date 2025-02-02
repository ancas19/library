package co.com.ancas.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfiguration {

    @Value("${cors.allowed.uris}")
    private String[] uris;

    private final JwtAuthenticationFilter jwtUtility;
    private final CustomAccessDeniedException customAccessDeniedException;
    private final UnauthorizedEntryPoint customAutheticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests(
                        requestAuth -> requestAuth
                                .requestMatchers(uris).permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement(
                        session -> session
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtUtility, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(
                        exceptionConf-> {
                            exceptionConf.authenticationEntryPoint(customAutheticationEntryPoint);
                            exceptionConf.accessDeniedHandler(customAccessDeniedException);
                        }
                )
                .build();
    }

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
