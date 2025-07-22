package com.example.demo.JWT;import java.util.Date;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtUtils {

	@Value("${spring.app.jwtSecreatKey}")
	private String jwtSecreate;

	@Value("${spring.app.jwtExpirationMs}")
	private int jwtExpirationMs;

	// Extract Bearer token from Authorization
	public String getJwtFromHeader(HttpServletRequest request) {

		System.out.println(jwtSecreate + "****" + jwtExpirationMs);

		String bearerToken = request.getHeader("Authorization");

		if (bearerToken != null && bearerToken.startsWith("Bearer")) {
			// Remove Bearer
			return bearerToken.substring(7);
		}

		return null;
	}

	// GenerateTokenFromUserName
	public String genrateTokenFromUserName(UserDetails user) {

		String username = user.getUsername();

		return Jwts.builder().subject(username)
				.claim("roles",
						user
						.getAuthorities()
						.stream()
						.map(auth -> auth.getAuthority())
						.collect(Collectors.toList()))
				.issuedAt(new Date()).expiration(new Date(System.currentTimeMillis() + jwtExpirationMs)).signWith(key())
				.compact();

	}

	// Return SecretKey (not just Key)
	private SecretKey key() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecreate));
	}

	// GetUserName from Token
	public String getUserNameFromJwtToken(String token) {

		return Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(token).getPayload().getSubject();
	}

	// verify Jwt token
	public boolean validateJwtToken(String authToken) {

		try {
			Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(authToken);
			return true;
		} catch (ExpiredJwtException e) {
			System.err.println("JWT expired: " + e.getMessage());
		} catch (MalformedJwtException e) {
			System.err.println("Invalid JWT: " + e.getMessage());
		} catch (SignatureException e) {
			System.err.println("JWT signature invalid: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("JWT validation error: " + e.getMessage());
		}
		return false;
	}
}
