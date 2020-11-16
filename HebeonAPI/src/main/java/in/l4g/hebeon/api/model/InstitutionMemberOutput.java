package in.l4g.hebeon.api.model;

import in.l4g.hebeon.commons.model.BaseModel;

public class InstitutionMemberOutput extends BaseModel
{
	private Integer userId;
	private String email;
	private String category;
	private String name;

	/**
	 * Constructor method
	 */
	public InstitutionMemberOutput()
	{
	}

	/**
	 * Constructor method
	 */
	public InstitutionMemberOutput(Integer userId, String email, String category, String name)
	{
		this.userId = userId;
		this.email = email;
		this.category = category;
		this.name = name;
	}

	/**
	 * Getter method for userId.
	 * 
	 * @return
	 */
	public Integer getUserId()
	{
		return userId;
	}

	/**
	 * Setter method for userId.
	 * 
	 * @param userId
	 */
	public void setUserId(Integer userId)
	{
		this.userId = userId;
	}

	/**
	 * Getter method for email.
	 * 
	 * @return
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * Setter method for email.
	 * 
	 * @param email
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	/**
	 * Getter method for mobile.
	 * 
	 * @return
	 */
	public String getCategory()
	{
		return category;
	}

	public void setCategory(String category)
	{
		this.category = category;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}
