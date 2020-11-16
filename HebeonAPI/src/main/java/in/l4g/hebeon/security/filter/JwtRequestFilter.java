package in.l4g.hebeon.security.filter;


import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import in.l4g.hebeon.security.userService.MyUserDetailsService;
import in.l4g.hebeon.security.utils.JwtTokenUtils;


@Component
public class JwtRequestFilter extends OncePerRequestFilter
{
	@Autowired
	JwtTokenUtils jwtTokenUtils;

	@Autowired
	private MyUserDetailsService userDetailsService;
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain) throws ServletException, IOException
	{
		final String authorizationHeader = request.getHeader("Authorization");

		String jwt = null;
		String username = null;
		if (authorizationHeader != null)
		{
			jwt = authorizationHeader.substring(7);
			username = jwtTokenUtils.extractUsername(jwt);
			System.out.println("Authorization Header" + jwt + "username " + username);
		}

		if ((username != null) && (SecurityContextHolder.getContext().getAuthentication() == null))
		{
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			
			if (jwtTokenUtils.validateToken(jwt, userDetails))
			{
				System.out.print("Token valid " + jwt);
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
						new UsernamePasswordAuthenticationToken(userDetails, null,
								userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetails(request));
				SecurityContextHolder.getContext()
						.setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}
}
