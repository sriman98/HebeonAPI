package in.l4g.hebeon.commons.model;

import java.io.Serializable;

import in.l4g.hebeon.commons.util.JsonUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class BaseModel implements Serializable
{
	@Override
	public String toString()
	{
		return JsonUtils.toString(this);
	}
}
