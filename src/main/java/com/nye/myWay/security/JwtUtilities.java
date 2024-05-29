package com.nye.myWay.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

//This utility class will be used by authenticate endpoint and filter
@Service
public class JwtUtilities {

    //data in application properties
    // created by https://asecuritysite.com/encryption/plain
    @Value("${application.security.jwt.secret-key}")
    private String secretKey;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;

    //extractUsername method extracts the username from a given JWT token
    // by utilizing the Claims class and its getSubject method
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    //The isTokenValid method checks the validity of a JWT token. It extracts the username from the token,
    // compares it with the username from the UserDetails object, and verifies that the token has not expired.
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    //The isTokenExpired method checks whether a given token has expired
    // by comparing its expiration time with the current time.
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    //The extractExpiration method extracts the expiration date from a JWT token.
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    //The extractAllClaims method parses and extracts all the claims from a JWT token.
    // It uses the parseClaimsJws method of the Jwts class and the getBody method to retrieve the claims.
    //https://medium.com/@tericcabrel/implement-jwt-authentication-in-a-spring-boot-3-application-5839e4fd8fac
    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    //The extractClaim method is a generic function that can extract any claim from a JWT token.
    // It takes a Function parameter, claimsResolver, which determines the type of claim to be extracted.
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    //The generateToken method generates a JWT token based on the provided UserDetails object - it is the username.
    // It uses the getStringObjectMap method to retrieve the user's roles and add them as claims to the token.
    public String generateToken(UserDetails userDetails) {
        return Jwts
                .builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setIssuedAt(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSignInKey())
                .compact();
    }

    //The getSignInKey method decodes the secret key and returns a Key object that is used for signing and verifying the JWT token.
    // It uses the hmacShaKeyFor method from the Keys class.
    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

//------------------------------

    /*//The getStringObjectMap method converts the user's authorities (roles) into a list
    // and adds them to a claims map.
    private static Map<String, Object> getStringObjectMap(UserDetails userDetails) {
        Map<String,Object> claims = new HashMap<>();
        claims.put("roles", userDetails.getAuthorities()
                .stream()
                .map(simpelAuthority -> simpelAuthority.getAuthority())
                .collect(Collectors.toList()));
        return claims;
    }

    //The overloaded generateToken method allows the addition of extra claims to the JWT token, in addition to the user details.
    // It calls the buildToken method to construct the token.
    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }
    //The buildToken method constructs the JWT token by setting the claims, subject, issued-at time, expiration time,
    // and signing the token using the secret key obtained from the getSignInKey method.
    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration) {

        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey())
                 .compact();
    }*/
}
