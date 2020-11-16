package in.l4g.hebeon.security.model;

public class AuthenticationResponse {
  private final String jwt;
  public AuthenticationResponse(String jwt)
  {
	  this.jwt=jwt;
  }
  public String getJwt()
  {
	  return jwt;
  }
}
