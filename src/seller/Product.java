package seller;

public class Product {
    private String name;
    private ProductsType productsType;
    private double price;

    public Product(String name, ProductsType productsType, double price) {
        this.name = name;
        this.productsType = productsType;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductsType getProductsType() {
        return productsType;
    }

    public void setProductsType(ProductsType productsType) {
        this.productsType = productsType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

