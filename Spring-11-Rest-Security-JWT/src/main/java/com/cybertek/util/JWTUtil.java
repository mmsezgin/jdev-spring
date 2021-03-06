package com.cybertek.util;

import com.cybertek.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
//We can use anyname other than JWTUtil
// As a best practice, some companies create utils folder and put it under this folder
@Component
public class JWTUtil {

    @Value("${security.jwt.secret-key}")
    private String secret = "cybertek";
    //Why? create and generate token separate? We do not want to mix payload with token
    // Creating payload here
    public String generateToken(User user){

        Map<String,Object> claims = new HashMap<>();
        claims.put("username",user.getUsername());
        claims.put("email",user.getEmail());
        return createToken(claims,user.getUsername());
    }
    // createToken steps are fixed for Spring
    private String createToken(Map<String,Object> claims,String username){

        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 *10)) //10 hours token validity
                .signWith(SignatureAlgorithm.HS256,secret).compact();

    }
    // First we need to DECODE the TOKEN. These code snippets are from Spring documentation
    private Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractUsername(String token){
        return extractClaim(token,Claims::getSubject);
    }

    public Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);

    }

    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }
    // Why UserDetails not User? To able to validate we need UserDetails.
    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }












}
