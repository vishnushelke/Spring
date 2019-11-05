/******************************************************************************
*  Purpose: This class is an utility class
*  @author  Vishnu Shelke
*  @version 1.0
*  @since   02-10-2019
*
******************************************************************************/
package com.bridgelabz.fundoo.user.note.utility;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Component
public class NoteTokenUtility {

	/**
	 * purpose: this method will take an userId as input and generate a token using that userId.
	 * @param userId of user
	 * @return token generated
	 */
	public String createToken(int userId) {
		String token = Jwts.builder().setSubject(String.valueOf(userId)).signWith(SignatureAlgorithm.HS256, "userId")
				.compact();
		return token;
	}
	
	/**
	 * purpose: this method will take token as input,parse it and returns an userId got after parsing
	 * @param token
	 * @return userId got after parsing
	 */
	public int getIdFromToken(String token) {
		Claims claim = Jwts.parser().setSigningKey("userId").parseClaimsJws(token).getBody();
		int userId = Integer.parseInt(claim.getSubject());
		return userId;
	}
}
