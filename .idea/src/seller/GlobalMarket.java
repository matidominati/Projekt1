package seller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GlobalMarket {
    private List<Market> markets = new ArrayList<>();

    public List<List<Product>> getAllProucts() {
        return markets.stream()
                .flatMap(market -> market.getAllProductList().stream())
                .collect(Collectors.toList());
    }


    public void sellAllProducts(ProductsType productsType) {
        markets.forEach(market -> market.sellAllProducts(productsType));
    }


}
