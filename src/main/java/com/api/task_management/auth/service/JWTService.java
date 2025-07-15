package com.api.task_management.auth.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Autowired
    ApplicationContext applicationContext;

    public JWTService(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    private Key getKey() {
        byte[] decoder = Base64.getDecoder().decode(secretKey.getBytes()); //dont pass direct string so divide into bytes and then return
        System.out.println("JWTService instance: " + decoder.toString());
        System.out.println("Secret key: " + secretKey);
        System.out.println("hmacsha: " + Keys.hmacShaKeyFor(decoder));

        return Keys.hmacShaKeyFor(decoder);
    }


    public String generateToken(String username, String userId) {

        Map<String,Object> claims = new HashMap<>();
        claims.put("userId", userId);

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 1 * 60)) //1 hour(1 * 60)
                //for 2 min = 1000 milisecond(1 sec) * 60 second(1 min) *  2 min(2min)
                .and()
                .signWith(getKey())
                .compact();
    }

    public boolean verifyToken(String token, UserDetails user) {
        final String username = extractUserName(token);
        return (username.equals(user.getUsername()) && !isTokenExpired(token));
    }

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public void authenticateToken(String token, HttpServletRequest request, HttpServletResponse response) {
        String username = extractUserName(token);
        UserDetails user = (UserDetails) applicationContext.getBean(UserDetailService.class).loadUserByUsername(username);
        if(verifyToken(token, user)){
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            authToken.setDetails(new WebAuthenticationDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);

            response.setHeader("Authorization", "Bearer " + token);

        }
    }
}
