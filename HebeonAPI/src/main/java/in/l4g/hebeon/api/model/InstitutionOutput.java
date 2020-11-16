package in.l4g.hebeon.api.model;


import in.l4g.hebeon.commons.model.BaseModel;

public class InstitutionOutput extends BaseModel{
	private java.lang.Integer id;
	private java.lang.String name;
	 
	public InstitutionOutput()
	{
	}

	/**
	 * Constructor method
	 */
	public InstitutionOutput(java.lang.Integer id, java.lang.String name)
	{
	    this.id = id;
	    this.name = name;
	}
	
	public java.lang.Integer getId()
	{
		return id;
	}
	
	public void setId(java.lang.Integer id)
	{
		this.id = id;
	}
	
	public java.lang.String getName()
	{
		return name;
	}
	
	public void setName(java.lang.String name)
	{
		this.name = name;
	}
}
