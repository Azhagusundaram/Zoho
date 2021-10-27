package ZCart;

public class Product {
    private int productId;
    private int categoryId;
    private int brandId;
    private String model;
    private double price;
    private int stock=0;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int category) {
        this.categoryId = category;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brand) {
        this.brandId = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock+= stock;
    }

    public String toString(){
        return productId+"\t"+brandId+"\t"+model+"\t"+price;
    }

}
