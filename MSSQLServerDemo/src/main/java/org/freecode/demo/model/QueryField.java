package org.freecode.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity(name = "QueryField")
@Table(name = "QueryField")
public class QueryField {

	@EmbeddedId
	private QueryFieldKey queryFieldKey; 
	
	@Column(name="IsSearchField")
	private Boolean isSearchField;
	
	@Column(name="IsColumnName")
	private Boolean isColumnName;

	public QueryFieldKey getQueryFieldKey() {
		return queryFieldKey;
	}

	public void setQueryFieldKey(QueryFieldKey queryFieldKey) {
		this.queryFieldKey = queryFieldKey;
	}

	public Boolean getIsSearchField() {
		return isSearchField;
	}

	public void setIsSearchField(Boolean isSearchField) {
		this.isSearchField = isSearchField;
	}

	public Boolean getIsColumnName() {
		return isColumnName;
	}

	public void setIsColumnName(Boolean isColumnName) {
		this.isColumnName = isColumnName;
	}

	@Override
	public String toString() {
		return "QueryField [queryFieldKey=" + queryFieldKey + ", isSearchField=" + isSearchField + ", isColumnName="
				+ isColumnName + "]";
	}
	
	
}
