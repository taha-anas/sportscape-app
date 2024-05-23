//package com.sportscape.api.userservice.service;
//
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//import java.security.Key;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//@Service
//public class JwtService {
//
//    private static final String SECRET_KEY = "8b5f4172875bff6430679ed1b773dc3f99ae47922fb607e15ee8df5f6fef9a39";
//
//    public String extractUsername(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//
//    //generate token based on the given claims and userDetails
//    public String generateToken(
//            Map<String, Object> claims,
//            UserDetails userDetails
//    ) {
//        return Jwts
//                .builder()
//                .setClaims(claims)
//                .setSubject(userDetails.getUsername())
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*24))
//                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    // generate token based on userDetails only
//    public String generateToken(UserDetails userDetails){
//        return generateToken(new HashMap<>(), userDetails);
//    }
//
//    // function to validate user token by checking both username and user token
//    public boolean validateToken(String token, UserDetails userDetails) {
//        final String username = this.extractUsername(token);
//        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
//    }
//
//    // function to check if the token is expired
//    private boolean isTokenExpired(String token) {
//        // first we will extract the expiration date of the token using extraClaims method
//        return extractClaim(token, Claims::getExpiration).before(new Date());
//    }
//
//    private Claims extractAllClaims(String token){
//        return Jwts
//                .parserBuilder()
//                .setSigningKey(getSignInKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    private Key getSignInKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//}
