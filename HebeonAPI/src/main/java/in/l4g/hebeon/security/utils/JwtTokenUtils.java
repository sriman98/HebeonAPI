package in.l4g.hebeon.security.utils;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtTokenUtils
{
	@Value("${jwt.secret}")
	private String secret;

	public static String generateToken(UserDetails userDetails, String secret)
	{
		Map<String, Object> claims = new HashMap<String, Object>();
		return createToken(claims, userDetails.getUsername(), secret);
	}

	private static String createToken(Map<String, Object> claims, String username, String secret)
	{
		return Jwts.builder().setClaims(claims).setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new java.util.Date(System.currentTimeMillis() + 10 * 60 * 60 * 1000))
				.signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	public String extractUsername(String token)
	{
		return extractClaim(token, Claims::getSubject);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver)
	{
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token)
	{
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token)
	{
		return extractExpiration(token).before(new Date());
	}

	public Date extractExpiration(String token)
	{
		return (Date) extractClaim(token, Claims::getExpiration);
	}

	public boolean validateToken(String token, UserDetails userDetails)
	{
		String username = this.extractUsername(token);
		return username.equals(userDetails.getUsername()) && !isTokenExpired(token);

	}
}
