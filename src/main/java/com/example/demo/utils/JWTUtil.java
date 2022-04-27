package com.example.demo.utils;

import com.example.demo.domains.UserModel;
import com.nimbusds.jose.jwk.JWKException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.function.Function;

@Component
public class JWTUtil {
    private final String issuer = "demo";

    private final String secret = "zrURoYeKwW0Lfae4ckcTMbf9Hw0Bs57GQg9rKEbdoD6bjDJexMdRQv8AJ0EmVxoPQbkYHLZ18aT9w6cu";

    private final String audience = "demo";

    private final long accessTokenExpirationMin = 120;

    private final long refreshTokenExpirationMin = 1440;

    private SecretKey secretKey;

    private static final String CLAIM_USER_ID = "userId";
    private static final String CLAIM_USER_ROLE_ID = "roleIds";
    private static final Logger logger = LoggerFactory.getLogger(JWTUtil.class);

    @PostConstruct
    public void setUpSecretKey() {
        secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(UserModel user){
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_USER_ID, user.getId());
        claims.put(CLAIM_USER_ROLE_ID, user.getRoles());
        return createToken(claims, user.getUsername(), accessTokenExpirationMin);
    }

    public String generateRefreshToken(UserModel user){
          Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_USER_ID, user.getId());
        claims.put(CLAIM_USER_ROLE_ID, user.getRoles());
        return createToken(claims, user.getUsername(), refreshTokenExpirationMin);
    }

    private String createToken(Map<String, Object> claims, String subject,long expirationMin) {
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(subject)
                .setClaims(claims)
                .setIssuer(issuer)
                .setAudience(audience)
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(Duration.ofMinutes(expirationMin))))
                .signWith(secretKey)
                .compact();
    }

    private  Claims extractAllClaims(String token){
        return  Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJwt(token).getBody();
    }

    private Claims parseClaimsFromToken(String token, String subject) {

        try {
            var jwt = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .requireAudience(audience)
                    .requireSubject(subject)
                    .build()
                    .parseClaimsJwt(token);
            return jwt.getBody();

        }
        catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }catch (InvalidClaimException e){
            logger.error("Invalid claims jwt: {}", e.getMessage());
        }
        return null;
    }

    public boolean validateToken(String token, UserModel userModel) {
        return parseClaimsFromToken(token, userModel.getUsername()) != null;
    }

    public String extractUsername(String token){
        return extractClaims(token, Claims::getSubject);
    }
    public Date extractExpiration(String token){
        return extractClaims(token, Claims::getExpiration);
    }

    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver){
        return claimsResolver.apply(extractAllClaims(token));
    }
    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }


}
