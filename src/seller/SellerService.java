package seller;

import java.util.*;
import java.util.stream.Collectors;


public class SellerService {

    private Set<Seller> sellers = new HashSet<>();

    public void addSeller(Seller seller) {
        if (checkIfSellerExistByPESEL(seller.getPESEL())) { // TODO zmienic nazwe -> checkPesel nam nic za bardzo nie mowi co robi metoda // done
            System.out.println("Taki sprzedawca już istnieje");
        } else {
            sellers.add(seller);
            System.out.println("Sprzedawca dodany.");
        }
    }

    public void showSeller(String PESEL) { //TODO to co w wieksozsci // done
        getSellerbyPESEL(PESEL)
                .ifPresentOrElse(seller -> {
                            System.out.println("Informacje o sprzedawcy:");
                            System.out.println("Imię: " + seller.getName());
                            System.out.println("Nazwisko: " + seller.getSurname());
                            System.out.println("PESEL: " + seller.getPESEL());
                            System.out.println("Pensja: " + seller.getSalary());
                            System.out.println("Ilość posiadanych produktów: " + seller.getHaveProduct());
                            System.out.println("Ilość sprzedanych produktów: " + seller.getSellProduct());
                        },
                        () -> System.out.println("W bazie nie ma takiego sprzedawcy."));
    }

    public Optional<Seller> getSellerByName(String name, String surname) {
        return sellers.stream()
                .filter(seller -> name.equals(seller.getName()) && surname.equals(seller.getSurname()))
                .findAny();

    }


    public Optional<Seller> getSellerbyPESEL(String PESEL) { //TODO reuzywaj tego kodu zamiast go powtarzac, sprawdz czy z innymi fragmentami kodu nie jest podobnie
        return sellers.stream()
                .filter(seller -> PESEL.equals(seller.getPESEL()))
                .findAny();
    }

    public List<Product> getSeller() {
        return sellers.stream()
                .flatMap(seller -> seller.getProductsToSell().stream())
                .collect(Collectors.toList());
    }


    public void deleteSeller(String PESEL) { // TODO czy to na pewno powinna byc metoda voidowa?!
        getSellerbyPESEL(PESEL)
                .ifPresentOrElse(seller -> {
                            sellers.remove(seller);
                            System.out.println("Sprzedawca usunięty");
                        },
                        () -> System.out.println("W bazie nie odnaleziono takiego sprzedawcy")
                );
    }



    public List<Seller> getSellers(ProductsType productsType) { //TODO da sie zrobic to ladniej, za pomoca jednego streama! // done
        return sellers.stream()
                .filter(seller -> seller.sellsProduct(productsType))
                .collect(Collectors.toList());

    }

    public Optional<Seller> getSellersOptional(ProductsType productsType) {  // nie usuwalem getSellers
        return sellers.stream()
                .filter(seller -> seller.sellsProduct(productsType))
                .findAny();

    }

    public void addProduct(Product product, Seller seller) {

        seller.listOfProductToSell().add(product);
        System.out.println("Produkt " + product.getName() + " został dodany do listy produktów");
    }

    public void addProductList(List<Product> products, Seller seller) { //TODO pytanie czy my chcemy napidasc ta lista inna liste juz istniejeaca, czy do istniejacej listy dodac elementy z tej liosty
        seller.setProductsToSell(products);
        System.out.println("Lista produktów " +seller.getName() + " " + seller.getSurname() + " została zaktualizowana");
    }

//    public void sell(Product product, Seller seller) { //TODO to mozna na 1 streamie // done
//        boolean productFound = seller.getProductsToSell().stream().anyMatch(prod -> prod.equals(product));
//        if (productFound) {
//            seller.getProductsToSell().removeIf(prod -> prod.equals(product));
//            System.out.println("Produkt " + product.getName() + " został usunięty z listy produktów " + seller.getName() + " " + seller.getSurname());
//        } else {
//            System.out.println("Ten sprzedawca nie posiada tego produktu");
//        }
//    }

    public void sell(Product product, Seller seller) {
        if (seller.getProductsToSell().removeIf(prod -> prod.equals(product))) {
            System.out.println("Produkt " + product.getName() + " został usunięty z listy produktów " + seller.getName() + " " + seller.getSurname());
        } else {
            System.out.println("Ten sprzedawca nie posiada tego produktu");
        }
    }


    //    public double getPriceOfProductForSeller(String PESEL, ProductsType productsType) { //TODO przekombinowane!!
//        return sellers.stream()
//                .filter(seller -> PESEL.equals(seller.getPESEL()))
//                .findAny() // mamy tego goscia po poeseliu
//                .map(seller -> seller.listOfProductToSell().stream()
//                        .filter(product -> productsType.equals(product.getProductsType()))
//                        .findAny()
//                        .map(product -> {
//                            seller.listOfProductToSell().remove(product);
//                            return product.getPrice();
//                        })
//                        .orElseThrow(() -> new RuntimeException("Dla sprzedawcy o pesel " + PESEL + " nie ma produktu o typie: " + productsType)))
//                .orElseThrow(() -> new RuntimeException("Nie ma sprzedawcy z takim PESEL."));
//
//    }
//
    public double getPriceOfProductForSeller(String pesel, String productName) {
        return getSellerbyPESEL(pesel)
                .flatMap(seller -> seller.getProductsToSell().stream()
                        .filter(product -> productName.equals(product.getName()))
                        .findFirst()
                        .map(productToRemove -> {
                            sell(productToRemove, seller);
                            return productToRemove.getPrice();
                        }))
                .orElseThrow(() -> new RuntimeException("Brak sprzedawcy lub produktu w bazie systemu"));
    }

    private boolean checkIfSellerExistByPESEL(String PESEL) {
        return sellers.stream()
                .anyMatch(seller -> PESEL.equals(seller.getPESEL()));
    }

    public List<Seller> getAllSellers(){
        return new ArrayList<>(sellers);
    }
}
