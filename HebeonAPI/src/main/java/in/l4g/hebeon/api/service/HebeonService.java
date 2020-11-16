package in.l4g.hebeon.api.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.l4g.hebeon.api.model.CategoryOutput;
import in.l4g.hebeon.api.model.InstitutionMemberOutput;
import in.l4g.hebeon.api.model.InstitutionOutput;



import in.l4g.skills.edu.dao.InstitutionDao;
import in.l4g.skills.edu.model.Institution;
import in.l4g.skills.edu.dao.InstitutionMemberDao;
import in.l4g.skills.edu.model.InstitutionMember;
import in.l4g.skills.metadata.dao.CategoryDao;
import in.l4g.skills.metadata.dao.CategoryValueDao;
import in.l4g.skills.metadata.model.Category;
@Service
public class HebeonService {
	

	Logger logger = LoggerFactory.getLogger(HebeonService.class);
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private CategoryValueDao categoryValueDao;

	@Autowired
	private InstitutionMemberDao institutionMemberDao;

	@Autowired
	private InstitutionDao institutionDao;

	public void setInstitutionDao(InstitutionDao institutionDao)
	{
		this.institutionDao = institutionDao;
	}

	public List<InstitutionOutput> getAll()
	{
		List<Institution> institutions=institutionDao.getAll();
		List<InstitutionOutput> outputs=new ArrayList<InstitutionOutput>();
		  for(int i = 0; i < institutions.size(); i++)
	        {
	        	Institution institution=institutions.get(i);
	        	outputs.add(new InstitutionOutput(institution.getId(),institution.getName()));
	        }
	        return outputs;
	}
	
	
	public CategoryOutput getById(java.lang.Integer institutionId)
	{
		List<Category> categories= categoryDao.getByInstitutionId(institutionId);
		CategoryOutput output =new CategoryOutput();
		
		for(int i=0;i<categories.size();i++)
		{
			Category category=categories.get(i);
		    output.getKeys().put(category.getId().toString(), category);
		    output.getValues().put(category.getId().toString(), categoryValueDao.getById(institutionId, category.getId()) );
		}
		
		return output;
	}
	
	public List<InstitutionMemberOutput> getByCategory(Integer institutionId,String category)
	{
		List<InstitutionMember> institutionMembers =institutionMemberDao.getByCategory(institutionId,category);
		List<InstitutionMemberOutput> outputMembers=new ArrayList<InstitutionMemberOutput>();
        for(int i = 0; i < institutionMembers.size(); i++)
        {
        	InstitutionMember member=institutionMembers.get(i);
        	outputMembers.add(new InstitutionMemberOutput(member.getUserId(),member.getEmail(),member.getCategory(),member.getName()));
        }
        return outputMembers;
	}

}
