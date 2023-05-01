package com.example.demo.config;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.websocket.Decoder;

@Service
public class JwtService {


	private static final String SECRET_KEY="337436763979244226452948404D635166546A576E5A7234753778217A25432A";
	
	public String extractUserName(String jwt) {
		// TODO Auto-generated method stub
		return extractClaim(jwt, Claims::getSubject);
	}
	
	public <T> T extractClaim(String token,Function<Claims,T> claimResolver) {
		final Claims claims=extractAllClaims(token);
		return claimResolver.apply(claims);
		
	}
	
	public String generateToken(
		Map<String,Object> extraClaims,
		UserDetails userDetail
			) {
		return Jwts
				.builder()
				.setClaims(extraClaims)
				.setSubject(userDetail.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
				.signWith(getSignInKey(),SignatureAlgorithm.HS256)
				.compact();
	}

	public String generateToken(UserDetails userDetail) {
			return generateToken(new HashMap<>(),userDetail);
		}
	
	private Claims extractAllClaims(String token) {
		return 
				Jwts.parserBuilder()
				.setSigningKey(getSignInKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
				
				
	}
	
	public boolean isValidToken(String token,UserDetails userDetails) {
		String userName=extractUserName(token);
		return (userName.equals(userDetails.getUsername()))&&!isTokenExpired(token);
	}

	private boolean isTokenExpired(String token) {
		// TODO Auto-generated method stub
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		// TODO Auto-generated method stub
		return extractClaim(token, Claims::getExpiration);
	}

	private Key getSignInKey() {
		byte[] keyBytes=Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}

}
