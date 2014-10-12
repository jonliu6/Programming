package org.freecode.demo;

public class Product {
	private String productId;
	private String productName;
	private String manufacturer;
	private Part part;
	
	public Product()
	{
		
	}
	
	public Product(String productId, String productName) {
		this.productId = productId;
		this.productName = productName;
	}


	public String getDefaultLabel()
	{
		return "test product.";
	}
	
	
	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	

	public void setPart(Part part) {
		this.part = part;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName="
				+ productName + ", manufacturer=" + manufacturer + ", part="
				+ part + "]";
	}
    
}
