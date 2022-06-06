package com.royal.security.jwt;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.royal.exception.AppAPIException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 *
 *@author Isaachome
 */

@Service
public class JwtTokenProvider {
	
	@Value("${app.jwt-secret}")
	private String jwtSecret;
	


	// generate token
	public String generateToken(Authentication authentication) {
		String username =  authentication.getName();

        Calendar expireAt = Calendar.getInstance();
        expireAt.add(Calendar.WEEK_OF_YEAR, 1);
		return Jwts.builder()
				.setSubject(username)
				.setIssuer("Royal Software")
				.setIssuedAt(new Date())
				.setExpiration(expireAt.getTime())
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}
	// get user name from token
	public String getUsernameFromToken(String token) {
		Claims claims  = Jwts.parser()
				.setSigningKey(jwtSecret)
				.parseClaimsJws(token)
				.getBody();
		return claims.getSubject();
	}
	// validate token
	 public boolean validateToken(String token){
	        try {
	            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
	            return true;
	        }catch (SignatureException exception){
	            throw new AppAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT signature");
	        } catch (MalformedJwtException ex) {
	            throw new AppAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
	        } catch (ExpiredJwtException ex) {
	            throw new AppAPIException(HttpStatus.BAD_REQUEST, "Expired JWT token");
	        } catch (UnsupportedJwtException ex) {
	            throw new AppAPIException(HttpStatus.BAD_REQUEST, "Unsupported JWT token");
	        } catch (IllegalArgumentException ex) {
	            throw new AppAPIException(HttpStatus.BAD_REQUEST, "JWT claims string is empty.");
	        }
	    }

}
