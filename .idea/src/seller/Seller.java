package seller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Seller {
    private String name;
    private String surname;
    private String pesel;
    private double salary;
    private int haveProduct;
    private int sellProduct;
    private List<Product> productsToSell = new ArrayList<>();

    public Seller(String name, String surname, String PESEL, double salary, ProductsType productsType, int haveProduct, int sellProduct) {
        this.name = name;
        this.surname = surname;
        this.pesel = PESEL;
        this.salary = salary;
        this.haveProduct = haveProduct;
        this.sellProduct = sellProduct;

    }

    public boolean sellsProduct(ProductsType productsType) {
        return productsToSell.stream()
                .anyMatch(product -> productsType.equals(product.getProductsType()));
    }

    @Override
    public String toString() {
        return "Seller{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", PESEL='" + pesel + '\'' +
                ", salary=" + salary +
                ", haveProduct=" + haveProduct +
                ", sellProduct=" + sellProduct +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seller seller = (Seller) o;
        return Double.compare(seller.salary, salary) == 0 && haveProduct == seller.haveProduct && sellProduct == seller.sellProduct && Objects.equals(name, seller.name) && Objects.equals(surname, seller.surname) && Objects.equals(pesel, seller.pesel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, pesel, salary, haveProduct, sellProduct);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getPESEL() {
        return pesel;
    }

    public void setPESEL(String PESEL) {
        this.pesel = PESEL;
    }

    public int getHaveProduct() {
        return haveProduct;
    }

    public void setHaveProduct(int haveProduct) {
        this.haveProduct = haveProduct;
    }

    public int getSellProduct() {
        return sellProduct;
    }

    public List<Product> listOfProductToSell() {
        return productsToSell;
    }

    public void setProductsToSell(List<Product> productsToSell) {
        this.productsToSell = productsToSell;
    }

    public void setSellProduct(int sellProduct) {
        this.sellProduct = sellProduct;
    }

    public List<Product> getProductsToSell() {
        return productsToSell;
    }
}
