package com.frt.product.service.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.List;
import java.util.function.Function;

@Component
@Slf4j
public class JwtUtil {

    // TODO move to env variables
    private static final String SECRET_KEY = "645367566B59703373367638792F423F4528482B4D6251655468576D5A713474";

    /**
     * Verifies the given JWT token and returns its claims if the token is valid.
     *
     * @param token the JWT token to verify
     * @return the claims of the token if it is valid
     * @throws JwtException if the token is invalid
     */
    public Claims verifyToken(String token) throws JwtException {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new JwtException("Invalid JWT token: " + e.getMessage());
        }
    }

    public Authentication getAuthentication(String token) {
        // Parse the token to extract the claims
        Claims claims = extractAllClaims(token);

        // Get the user ID and authorities from the claims
        Long userId = claims.get("id", Long.class);
        String role = "ROLE_" + claims.get("role", String.class);
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role));

        // Create a UserDetails object from the extracted information
        UserDetails userDetails = new User(userId.toString(), "", authorities);

        // Return an Authentication object with the UserDetails and the token
        return new UsernamePasswordAuthenticationToken(userDetails, token, authorities);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        try {

            Claims claims = extractAllClaims(token);
            return claimsResolver.apply(claims);

        } catch (ExpiredJwtException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}