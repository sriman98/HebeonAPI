package in.l4g.hebeon;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import in.l4g.skills.edu.dao.InstitutionDao;
import in.l4g.skills.edu.dao.InstitutionMemberDao;
import in.l4g.skills.metadata.dao.CategoryDao;
import in.l4g.skills.metadata.dao.CategoryValueDao;

@Configuration
public class ApplicationConfig 
{
	@Bean
	public DataSource postgresDataSource()
	{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://localhost:5432/skillsdb");
		dataSource.setUsername("skills");
		dataSource.setPassword("skills");

		return dataSource;
	}

	@Bean
	public SqlSessionFactory postgresSqlSessionFactory() throws Exception
	{
		String configLocation = "sqlMapConfig.xml";
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(postgresDataSource());
		sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(configLocation));

		return sqlSessionFactoryBean.getObject();
	}

	@Bean
	public SqlSessionTemplate postgresSqlSessionTemplate() throws Exception
	{
		SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(postgresSqlSessionFactory());

		return sqlSessionTemplate;
	}

	@Bean
	public InstitutionDao institutionDao() throws Exception
	{
		InstitutionDao institutionDao = new InstitutionDao();
		institutionDao.setSqlSessionFactory(postgresSqlSessionFactory());
		institutionDao.setSqlSessionTemplate(postgresSqlSessionTemplate());

		return institutionDao;
	}

	@Bean
	public CategoryDao categoryDao() throws Exception
	{
		CategoryDao dao = new CategoryDao();
		dao.setSqlSessionFactory(postgresSqlSessionFactory());
		dao.setSqlSessionTemplate(postgresSqlSessionTemplate());

		return dao;
	}

	@Bean
	public CategoryValueDao categoryValueDao() throws Exception
	{
		CategoryValueDao dao = new CategoryValueDao();
		dao.setSqlSessionFactory(postgresSqlSessionFactory());
		dao.setSqlSessionTemplate(postgresSqlSessionTemplate());

		return dao;
	}

	@Bean
	public InstitutionMemberDao institutionMemberDao() throws Exception
	{
		InstitutionMemberDao dao = new InstitutionMemberDao();
		dao.setSqlSessionFactory(postgresSqlSessionFactory());
		dao.setSqlSessionTemplate(postgresSqlSessionTemplate());

		return dao;
	}


}
