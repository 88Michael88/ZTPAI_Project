package pl.edu.pk.ztpai_project.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private final String secret = "my-super-secret-key-that-is-at-least-32-bytes-long!-kjsdf;lajslf;jasd;ljfaskdjf;lkasjfl;kjasl;fja;slkjdfkl";

    public String generateToken(Long id, String username, String role) {
        Key key = getSigningKey();

        return Jwts.builder()
        .setSubject(username)
        .claim("id", id)
        .claim("role", role)
        .setExpiration(new Date(System.currentTimeMillis() + 86400000))
        .signWith(key, SignatureAlgorithm.HS256)
        .compact();
    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
        .setSigningKey(getSigningKey())
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
    }

    public String extractRole(String token) {
        return Jwts.parserBuilder()
        .setSigningKey(getSigningKey())
        .build()
        .parseClaimsJws(token)
        .getBody()
        .get("role", String.class);
    }

    public Long extractId(String token) {
        return Jwts.parserBuilder()
        .setSigningKey(getSigningKey())
        .build()
        .parseClaimsJws(token)
        .getBody()
        .get("id", Long.class);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token);

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
