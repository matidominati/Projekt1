package seller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


public class Market {
    private SellerService sellerService;
    private BigDecimal earnings;
    private String name;

    public Market(SellerService sellerService, BigDecimal earnings) {
        this.sellerService = sellerService;
        this.earnings = earnings;
    }

    public void sellProduct(String sellerPesel, Product product) {
        double price = sellerService.getPriceOfProductForSeller(sellerPesel, product.getName());
        earnings = earnings.add(BigDecimal.valueOf(price));
    }

    public void sellAllVegetables() {
        sellAllProducts(ProductsType.VEGETABLE);
    }

    public void sellAllFruits() {
        sellAllProducts(ProductsType.FRUIT);
    }

    public void sellAllToys() {
        sellAllProducts(ProductsType.TOYS);
    }

    public void sellAllCoins() {
        sellAllProducts(ProductsType.COINS);
    }

    public void sellAllProducts(ProductsType productsType) {
        List<Seller> sellers = sellerService.getSellers(productsType);
        BigDecimal totalEarned = sellers.stream()
                .flatMap(seller -> seller.getProductsToSell().stream())
                .map(product -> BigDecimal.valueOf(product.getPrice()))
                .reduce(BigDecimal.ZERO, (bigDecimal, next) -> bigDecimal.add(next));

        sellers.forEach(seller -> seller.getProductsToSell().clear());

        earnings = earnings.add(totalEarned);
    }

    public BigDecimal stocktaking() {
        return sellerService.getSeller().stream()
                .map(product -> BigDecimal.valueOf(product.getPrice()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal stocktakingWithArguments(ProductsType productsType) {
        return sellerService.getSellers(productsType).stream()
                .flatMap(seller -> seller.getProductsToSell().stream())
                .map(product -> BigDecimal.valueOf(product.getPrice()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal stocktakingFruits() {
        return stocktakingWithArguments(ProductsType.FRUIT);
    }

    public void printAllNamesOfSellers() {
        sellerService.getAllSellers().stream()
                .map(seller -> seller.getName())
                .forEach(name -> System.out.println(name));
    }

    public void printNamesOfSellersWithoutNullProducts() {
        sellerService.getAllSellers().stream()
                .filter(seller -> seller.getProductsToSell().size() > 0)
                .map(seller -> seller.getName())
                .forEach(name -> System.out.println(name));
    }

    public void printNamesOfSellersWithSomeProducts(int some) {
        sellerService.getAllSellers().stream()
                .filter(seller -> seller.getProductsToSell().size() > some)
                .map(seller -> seller.getName())
                .forEach(System.out::println);
    }

    public List<List<Product>> getAllProductList() {
        return sellerService.getAllSellers().stream()
                .map(seller -> seller.getProductsToSell())
                .collect(Collectors.toList());
    }

    public List<Seller> getAllSellersWithoutNullProducts() {
        return sellerService.getAllSellers().stream()
                .filter(seller -> seller.getProductsToSell().size() > 0)
                .collect(Collectors.toList());
    }

    //TODO uwsplnic
    public List<Seller> getAllSellersWithOnlyVegetables() {
        return getAllSellersWith(ProductsType.VEGETABLE);
    }

    public List<Seller> getAllSellersWithOnlyFruits() {
        return getAllSellersWith(ProductsType.FRUIT);
    }

    public List<Seller> getAllSellersWith(ProductsType productsType) {
        return sellerService.getAllSellers().stream()
                .filter(seller -> seller.getProductsToSell().size() > 0)
                .filter(seller -> seller.getProductsToSell().stream()
                        .allMatch(product -> product.getProductsType().equals(productsType)))
                .collect(Collectors.toList());
    }

    public List<List<Product>> getNProductList(int n, ProductsType productsType) {
        return sellerService.getAllSellers().stream()
                .filter(seller -> seller.getProductsToSell().size() > n)
                .map(seller -> seller.getProductsToSell())
                .filter(products -> products.stream()
                        .anyMatch(product -> product.getProductsType().equals(productsType)))
                .collect(Collectors.toList());
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public SellerService getSellerService() {
        return sellerService;
    }

    public void setSellerService(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    public BigDecimal getEarnings() {
        return earnings;
    }

    public void setEarnings(BigDecimal earnings) {
        this.earnings = earnings;
    }


}


