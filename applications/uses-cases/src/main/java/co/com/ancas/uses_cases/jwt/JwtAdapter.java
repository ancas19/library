package co.com.ancas.uses_cases.jwt;

import co.com.ancas.models.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtAdapter {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Integer expirationTime;


    public String generateToken(User userEntity) {
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date(issuedAt.getTime() + expirationTime * 60 * 1000);
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", userEntity.getUsername());
        claims.put("id", userEntity.getId());
        return Jwts.builder()
                .header()
                .type("JWT")
                .and()
                .subject(userEntity.getUsername())
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

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
