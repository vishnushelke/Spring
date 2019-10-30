package com.bridgelabz.fundoo.note.utility;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Component
public class TokenUtility {

	public String createToken(int userId) {
		String token = Jwts.builder().setSubject(String.valueOf(userId)).signWith(SignatureAlgorithm.HS256, "userId")
				.compact();
		return token;
	}
	
	public int getIdFromToken(String token) {
		Claims claim = Jwts.parser().setSigningKey("secretKey").parseClaimsJws(token).getBody();
		int userId = Integer.parseInt(claim.getSubject());
		return userId;
	}
}
