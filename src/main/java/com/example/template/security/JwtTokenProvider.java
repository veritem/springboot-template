package com.example.template.security;
import io.jsonwebtoken.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import com.example.template.models.User;
import com.example.template.repository.UserRepository;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class JwtTokenProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Autowired
    private UserRepository userRepository;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiresIn}")
    private int jwtExpirationInMs;

    public String generateToken(Authentication authentication){
        System.out.println("Authentication "+authentication.getPrincipal());
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime()+jwtExpirationInMs);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for (GrantedAuthority role :userPrincipal.getAuthorities()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        }

        User authUser = userRepository.findById(userPrincipal.getId()).get();


        String token = Jwts .builder() .setId(authUser.getId()+"")
                .setSubject(userPrincipal.getId()+"")
                .claim("authorities",grantedAuthorities)
                .claim("user",authUser)
                .setIssuedAt(new
                        Date(System.currentTimeMillis())) .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
        return  token;
    }

    public String getUserIdFromToken(String token){
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature", ex);
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token", ex);
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token" + ex);
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token" + ex);
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty" + ex);
        }
        return false;
    }
}