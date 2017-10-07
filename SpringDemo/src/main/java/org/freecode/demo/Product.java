package org.freecode.demo;

import java.util.Map;

public class Product {
	private String productId;
	private String productName;
	private String manufacturer;
	private Part part;
	private Map<String, Integer> productComponents;
	
	public Product()
	{
		
	}
	
	public void init() {
		System.out.println("Initiating Part...");
	}
	
	public void destroy() {
		System.out.println("Part destroyed!");
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
	
	public Map<String, Integer> getProductComponents() {
		return productComponents;
	}

	public void setProductComponents(Map<String, Integer> productComponents) {
		this.productComponents = productComponents;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName="
				+ productName + ", manufacturer=" + manufacturer + ", part="
				+ part + ", productComponents=" + productComponents + "]";
	}
    
}
