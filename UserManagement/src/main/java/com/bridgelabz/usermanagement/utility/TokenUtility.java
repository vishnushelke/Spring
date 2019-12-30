package com.bridgelabz.usermanagement.utility;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenUtility {

	public String createToken(int userId) {
		String token = Jwts.builder().setSubject(String.valueOf(userId)).signWith(SignatureAlgorithm.HS256, "secretKey")
				.compact();
		return token;
	}
	public int getIdFromToken(String token) {
		String body = Jwts.parser().setSigningKey("secretKey").parseClaimsJws(token).getBody().getSubject();
		return Integer.parseInt(body);
	}
}
