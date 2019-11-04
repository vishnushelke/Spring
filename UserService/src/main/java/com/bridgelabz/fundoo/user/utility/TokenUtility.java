package com.bridgelabz.fundoo.user.utility;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class TokenUtility {
	
	public String createToken(String emailId) {
		String token = Jwts.builder().setSubject(emailId).signWith(SignatureAlgorithm.HS256, "userId")
				.compact();
		return token;
	}
	
	public String getEmailIdFromToken(String token) {
		Claims claim = Jwts.parser().setSigningKey("userId").parseClaimsJws(token).getBody();
		String email = claim.getSubject();
		return email;
	}

}
