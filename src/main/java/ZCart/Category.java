package ZCart;

public class Category{
	private int categoryId;
	private String categoryName;
	
	public void setCategoryName(String categoryName) {
		this.categoryName=categoryName;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId=categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public int getCategoryId() {
		return categoryId;
	}
}