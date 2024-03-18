package org.freecode.demo.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class QueryFieldKey implements Serializable {

	@Column(name="PageName", nullable = false)
	private String pageName;
	
	@Column(name="FieldName", nullable = false)
	private String fieldName;
	
	public QueryFieldKey() {}
	
	public QueryFieldKey(String pageName, String fieldName) {
		this.pageName = pageName;
		this.fieldName = fieldName;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	@Override
	public String toString() {
		return "QueryFieldKey [pageName=" + pageName + ", fieldName=" + fieldName + "]";
	}
	
}
