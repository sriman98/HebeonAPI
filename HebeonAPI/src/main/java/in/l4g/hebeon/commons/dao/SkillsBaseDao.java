package in.l4g.hebeon.commons.dao;


import java.util.HashMap;
import java.util.Map;
import in.l4g.hebeon.commons.dao.BaseDAO;

/**
 *
 * @author Prakash K
 * @date 2020-04-19
 *
 */
abstract public class SkillsBaseDao extends BaseDAO
{
	protected Map<String, String> partitionTableNames;

	abstract public String getTableName();

	/**
	 * Default implementation
	 * 
	 * @return
	 */
	public String getMasterTable()
	{
		return null;
	}

	/**
	 * Default implementation
	 * 
	 * @return
	 */
	public String getUniqueColumn()
	{
		return null;
	}

	/**
	 * Default implementation
	 * 
	 * @return
	 */
	public String getPartitionIdColumn()
	{
		return null;
	}

	public String getIndexColumn1()
	{
		return null;
	}

	public String getIndexColumn2()
	{
		return null;
	}

	public String getIndexColumn3()
	{
		return null;
	}

	protected String getPartitionTableNameFromDatabase(int partitionIdValue)
	{
		System.out.println("=====>getPartitionTableNameFromDatabase(): Called");
		Map<String, Object> params = new HashMap<>();
		params.put("masterTableName", getMasterTable());
		params.put("uniqueColumn", getUniqueColumn());
		params.put("partitionIdColumn", getPartitionIdColumn());
		params.put("partitionIdValue", partitionIdValue);
		params.put("indexColumn1", getIndexColumn1());
		params.put("indexColumn2", getIndexColumn2());
		params.put("indexColumn3", getIndexColumn3());

		return sqlSessionTemplate
				.selectOne("in.l4g.skills.commons.model.CommonQueries.GetTableName", params);
	}

	public String getPartitionTableName(int institutionId)
	{
		if (partitionTableNames == null)
		{
			partitionTableNames = new HashMap<>();
		}

		String tableName = partitionTableNames.get("" + institutionId);
		if (tableName == null)
		{
			tableName = getPartitionTableNameFromDatabase(institutionId);
			partitionTableNames.put("" + institutionId, tableName);
		}

		return tableName;
	}
}
