package global.scit.cosmetic.vo;

public class Product {
	private String productid;
	private String productname;
	private int productprice;
	private int producttype;
	private int productsolution;

	public Product() {
		super();

	}

	public Product(String productid, String productname, int productprice, int producttype, int productsolution) {
		super();
		this.productid = productid;
		this.productname = productname;
		this.productprice = productprice;
		this.producttype = producttype;
		this.productsolution = productsolution;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getProductname(String productid) {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public int getProductprice() {
		return productprice;
	}
	public void setProductprice(int productprice) {
		this.productprice = productprice;
	}
	public int getProducttype() {
		return producttype;
	}
	public void setProducttype(int producttype) {
		this.producttype = producttype;
	}
	public int getProductsolution() {
		return productsolution;
	}
	public void setProductsolution(int productsolution) {
		this.productsolution = productsolution;
	}
	@Override
	public String toString() {
		String result = "productid : " + productid + ", productname : " + productname + ", productprice : " + productprice;
		result += ", producttype : ";
		if(producttype==1){
			result += "세럼";
		}else if(producttype==2){
			result += "크림";
		}else if(producttype==3){
			result += "마스크";
		}
		result += ", productsolution : ";
		if(productsolution ==1){
			result += "미백";
		}else if(productsolution ==2){
			result += "노화";
		}else if(productsolution ==3){
			result += "여드름";
		}
		return result;
	}
}