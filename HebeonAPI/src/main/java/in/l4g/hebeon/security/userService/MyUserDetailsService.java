package in.l4g.hebeon.security.userService;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService
{


	@Value("${jwt.username}")
	private String username;
	@Value("${jwt.password}")
	private String password;
	@Override
	public UserDetails loadUserByUsername(String uname) throws UsernameNotFoundException
	{
		if(uname.equals(username))
		{
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
		authorities.add(authority);
		return new org.springframework.security.core.userdetails.User(username,password,
				authorities);

		}
		else {return null;}
	}

}
