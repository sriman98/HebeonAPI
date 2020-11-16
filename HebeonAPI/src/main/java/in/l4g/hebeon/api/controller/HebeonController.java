package in.l4g.hebeon.api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import in.l4g.hebeon.api.model.CategoryOutput;
import in.l4g.hebeon.api.model.InstitutionMemberOutput;
import in.l4g.hebeon.api.model.InstitutionOutput;
import in.l4g.hebeon.api.service.HebeonService;
import in.l4g.hebeon.commons.util.Base64Utils;
import in.l4g.hebeon.security.userService.MyUserDetailsService;
import in.l4g.hebeon.security.utils.JwtTokenUtils;
import in.l4g.hebeon.security.model.AuthenticationRequest;
import in.l4g.hebeon.security.model.AuthenticationResponse;

@RestController
public class HebeonController
{

	@Autowired
	private HebeonService hebeonService;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired JwtTokenUtils jwtTokenUtil;

	
	@RequestMapping(value = "/login",
			method = { RequestMethod.POST })
	public ResponseEntity<?>createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception
	{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword()));
		}
		catch(BadCredentialsException e)
		{throw new Exception("Incorrect username or password",e);}
		final UserDetails userDetails=userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt=jwtTokenUtil.generateToken(userDetails,"secret");
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/institutions", method = { RequestMethod.GET })
	public List<InstitutionOutput> getAll()
	{
		return hebeonService.getAll();
	}

	@RequestMapping(value = "/institutions/{institutionId}/metadata",
			method = { RequestMethod.GET })
	public CategoryOutput getByInstitutionId(@PathVariable int institutionId)
	{
		CategoryOutput output = hebeonService.getById(institutionId);
		return output;
	}

	@ResponseBody
	@RequestMapping(value = "/institutions/{institutionId}/members", method = { RequestMethod.GET })
	public List<InstitutionMemberOutput> getByInstitutionIdandByCategory(

			@PathVariable java.lang.Integer institutionId,@RequestParam(name="category",required = false,defaultValue = "e30=") String category)
	{
		category=new String(Base64Utils.decode(category));
		return hebeonService.getByCategory(institutionId,category);
	}


}
