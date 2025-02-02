package co.com.ancas.uses_cases.jwt;

import co.com.ancas.models.model.TokenInformation;
import co.com.ancas.models.repositories.JwtRepositoryPort;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;

@Component
@RequiredArgsConstructor
public class JwtAdapter {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Integer expirationTime;

    private final JwtRepositoryPort jwtRepositoryPort;

    public void saveToken(String token, TokenInformation data) {
        jwtRepositoryPort.save(token, data);
    }

    public void deleteToken(String token) {
        jwtRepositoryPort.delete(token);
    }

    public boolean verifyToken(String token) {
        return Objects.isNull(jwtRepositoryPort.find(token));
    }
    public TokenInformation getTokenInformation(String token) {
        return jwtRepositoryPort.find(token);
    }
    public String generateToken(UserDetails userDetails ) {
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date(issuedAt.getTime() + expirationTime * 60* 60 * 1000);
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", userDetails.getUsername());
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        claims.put("roles", roles);
        return Jwts.builder()
                .header()
                .type("JWT")
                .and()
                .subject(userDetails.getUsername())
                .issuedAt(issuedAt)
                .expiration(expiration)
                .claims(claims)
                .signWith(generateKey(), Jwts.SIG.HS256)
                .compact();
    }

    private SecretKey generateKey() {
        byte[] keyDecoded= Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyDecoded);
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public List<String> extractRoles(String token) {
        Claims claims = extractAllClaims(token);
        return  claims.get("roles", List.class);
    }
    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
