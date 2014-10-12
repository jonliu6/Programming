package com.freecode.xproject.model;

import java.io.File;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity(name="RestaurantDetailEntity")
@Table(name="xrestaurantdetail")
public class RestaurantDetail {
	@Id
	@Column(name="RestaurantId", nullable=false)
	private String theRestaurantId;
	
	@Column(name="FileType")
	private String theFileType;
	
	@Lob
	@Column(name="MediaFile")
	private byte[] theFile;

	public String getRestaurantId() {
		return theRestaurantId;
	}

	public void setRestaurantId(String aRestaurantId) {
		theRestaurantId = aRestaurantId;
	}

	public String getFileType() {
		return theFileType;
	}

	public void setFileType(String aFileType) {
		theFileType = aFileType;
	}

	public byte[] getFile() {
		return theFile;
	}

	public void setFile(byte[] aFile) {
		theFile = aFile;
	}

}
