package com.krishna.ram_krishna.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    private final String jwtSecret = "mySecretKeyForJwtGenerationThatIsLongEnough12345";
    private final long jwtExpirationMs = 86400000; // 1 day
    private final SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

    public String generateToken(String uniqueId) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Date exp = new Date(nowMillis + jwtExpirationMs);
        return Jwts.builder()
                .subject(uniqueId)
                .issuedAt(now)
                .expiration(exp)
                .signWith(key)
                .compact();
    }

    public String getUniqueIdFromToken(String token) {
    return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
