package com.formation.entity;

public class LigneDeCommande {
		
	private int id;
	private String category;
	private String theme;
	private String product;
	private int createYear;
	private String createMonth;
	private double price;
	private int quantity;
	
	//******************************* G & S
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public int getCreateYear() {
		return createYear;
	}
	public void setCreateYear(int createYear) {
		this.createYear = createYear;
	}
	public String getCreateMonth() {
		return createMonth;
	}
	public void setCreateMonth(String createMonth) {
		this.createMonth = createMonth;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	//****************************** CONSTRUCTORS
	public LigneDeCommande() {
		// TODO Auto-generated constructor stub
	}
	public LigneDeCommande(int id, String category, String theme, String product, int createYear, String createMonth,
			double price, int quantity) {
		this.id = id;
		this.category = category;
		this.theme = theme;
		this.product = product;
		this.createYear = createYear;
		this.createMonth = createMonth;
		this.price = price;
		this.quantity = quantity;
	}
	//********************************* TOSTRING
	@Override
	public String toString() {
		return "LigneDeCommande [id=" + id + ", " + (category != null ? "category=" + category + ", " : "")
				+ (theme != null ? "theme=" + theme + ", " : "") + (product != null ? "product=" + product + ", " : "")
				+ "createYear=" + createYear + ", createMonth=" + createMonth + ", price=" + price + ", quantity="
				+ quantity + "]";
	}
	
	
	
}
