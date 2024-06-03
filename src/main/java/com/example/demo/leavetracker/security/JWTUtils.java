package com.example.demo.leavetracker.security;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;


@Service
public class JWTUtils {
	private  SecretKey Key;
	
	private static final Long EXPIRATION_TIME= 86400000L;//24HOURS
	
	public JWTUtils(@Value("${jwt.secret}") String secretString) {
		
		
		byte[] keyBytes=Base64.getDecoder().decode( secretString.getBytes(StandardCharsets.UTF_8));
		this.Key=new SecretKeySpec(keyBytes, "HmacSHA256");
	}
		 
	 public String generateToken(UserDetails userDetails) {
	        Date now = new Date();
        return Jwts.builder().setSubject(userDetails.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date( System.currentTimeMillis()+ EXPIRATION_TIME))
        .signWith(Key).compact();
        
	 }	 
	
	 public  String generateRefreshToken(HashMap<String, Object>claims, UserDetails userDetails ) {
		 return Jwts.builder()
				 .addClaims(claims)
				 .setSubject(userDetails.getUsername())
			        .setIssuedAt(new Date(System.currentTimeMillis()))
			        .setExpiration(new Date( System.currentTimeMillis()+ EXPIRATION_TIME))
			        .signWith(Key).compact();
		  }
	 
	 public String extractUsername(String token) {
		 
		 return extractClaims(	token, Claims::getSubject);
	 }
	 
	 private <T> T extractClaims(String token, Function<Claims, T>claimsTFunction) {
		 
		 return 
				 claimsTFunction.
				 apply(Jwts.parserBuilder().
						 setSigningKey(Key).build().
			     parseClaimsJws(token).getBody());
	 }
	 
	 public boolean isTokenValid(String token ,UserDetails userDetails) {
		 
		final  String username =extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	        
	    }
	 public boolean isTokenExpired(String token) {
		 return extractClaims(token, Claims::getExpiration).before(new Date());
	 }

}
