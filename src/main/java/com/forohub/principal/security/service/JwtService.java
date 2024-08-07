package com.forohub.principal.security.service;

import com.forohub.principal.models.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;


@Service
public class JwtService {
    @Value("${security.jwt.expiration-minutes}")
    private long EXPIRATION_MINUTES;
    @Value("${security.jwt.secrete-key}")
    private String SECRET_KEY;

    public String generateToken(Usuario user, Map<String, Object> extraClaim){
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date(issuedAt.getTime()+EXPIRATION_MINUTES*60*100);
        return Jwts.builder()
                .setClaims(extraClaim)
                .setSubject(user.getUsername())
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .setHeaderParam(Header.TYPE,Header.JWT_TYPE)
                .signWith(generateKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    public String extractUsername(String jwt) {
        return extractAllClaims(jwt).getSubject();
    }
    private Key generateKey(){
        byte[] secretByte = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(secretByte);
    }
    public Claims extractAllClaims(String jwt){
        return Jwts.parser().setSigningKey(generateKey()).build().parseClaimsJws(jwt).getBody();
    }








}
