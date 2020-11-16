package in.l4g.hebeon.commons.dao;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
abstract public class BaseDAO
{
	// protected Logger logger = LoggerFactory.getLogger(this.getClass());
	protected static final String TABLE_NAME_PARAM = "tableName";

	@Autowired
	protected DataSource dataSource;

	@Autowired
	protected JdbcTemplate jdbcTemplate;

	@Autowired
	protected SqlSessionFactory sqlSessionFactory;

	
	protected SqlSessionTemplate sqlSessionTemplate;

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory)
	{
		this.sqlSessionFactory = sqlSessionFactory;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate)
	{
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	 @PostConstruct
	 public void init()
	 {
	 sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
	 }
	//
	// public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory)
	// {
	// this.sqlSessionFactory = sqlSessionFactory;
	// sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
	// }

	@PreDestroy
	public void destroy()
	{
		//sqlSessionTemplate.close();
		//sqlSessionTemplate = null;
	}

	protected void add(Object object, String tableName, String sqlMap)
	{
		final Map<String, Object> params = new HashMap<String, Object>();
		params.put(TABLE_NAME_PARAM, tableName);
		params.put("p", object);

		int result = sqlSessionTemplate.insert(sqlMap, params);
	}

	protected void deleteById(String tableName, Integer id)
	{
		final Map<String, Object> params = new HashMap<String, Object>();
		params.put(TABLE_NAME_PARAM, tableName);
		params.put("id", id);

		sqlSessionTemplate.delete("Common.DeleteById", params);
	}

	protected void deleteByColumnValue(String tableName, String columnName, String columnValue)
	{
		final Map<String, Object> params = new HashMap<String, Object>();
		params.put(TABLE_NAME_PARAM, tableName);
		params.put("columnName", columnName);
		params.put("columnValue", columnValue);

		sqlSessionTemplate.delete("Common.DeleteColumnValue", params);
	}
}
