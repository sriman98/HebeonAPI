package in.l4g.hebeon.api.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.l4g.hebeon.commons.model.BaseModel;
import in.l4g.skills.metadata.model.Category;
import in.l4g.skills.metadata.model.CategoryValue;

public class CategoryOutput extends BaseModel {
Map<String,Category> keys;
Map<String,List<Object>> values;


public CategoryOutput() {
	super();
	keys=new HashMap();
	values=new HashMap();
}


public CategoryOutput(Map<String, Category> keys, Map<String, List<Object>> values) {
	super();
	this.keys = keys;
	this.values = values;
}


public Map<String, Category> getKeys() {
	return keys;
}


public void setKeys(Map<String, Category> keys) {
	this.keys = keys;
}


public Map<String, List<Object>> getValues() {
	return values;
}


public void setValues(Map<String, List<Object>> values) {
	this.values = values;
}



}
