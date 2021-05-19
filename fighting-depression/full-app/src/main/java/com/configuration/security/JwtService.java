package com.configuration.security;

import io.jsonwebtoken.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author avinash.a.mishra
 */

@Service
public class JwtService {

    public static final long ONE_MINUTE =  1000 * 60;
    public static final long ONE_HOUR =  1000 * 60 * 60;
    public static final String SECRET_KEY = "sopWebApp";


    public String getUsernameFromToken(String token) {
        String username=null;

            username = getClaimFromToken(token, Claims::getSubject);

        return username;
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }


    public String generateTokenFromUserDetail(UserDetails userDetails) {
       Map<String, Object> claims= new HashMap<>();
        String token = createToken(claims, userDetails.getUsername());
        return token;
    }

    private String createToken(Map<String, Object> claims, String subject) {
        String compact = Jwts.builder().setClaims(claims).setSubject(subject)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ONE_HOUR * 5))
                .compact();
        return compact;
    }


    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
